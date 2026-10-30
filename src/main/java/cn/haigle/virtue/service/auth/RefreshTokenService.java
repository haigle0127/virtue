package cn.haigle.virtue.service.auth;

import cn.haigle.virtue.common.exception.CodeConstants;
import cn.haigle.virtue.common.exception.ServiceException;
import cn.haigle.virtue.common.security.config.SecurityProperties;
import cn.haigle.virtue.mapper.dataobject.auth.SysRefreshTokenDO;
import cn.haigle.virtue.mapper.mysql.auth.RefreshTokenMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.HexFormat;
import java.util.UUID;

/**
 * 刷新令牌服务：签发、轮换、重放检测、吊销
 * <p>刷新令牌是不透明随机串，数据库只保存 SHA-256 摘要；每次刷新都会换新令牌</p>
 *
 * @author haigle
 */
@Slf4j
@Service
public class RefreshTokenService {

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    private static final int TOKEN_BYTES = 32;

    @Resource
    private RefreshTokenMapper refreshTokenMapper;

    @Resource
    private SecurityProperties securityProperties;
    /**
     * 登录时签发一个新的登录会话
     */
    public IssuedRefreshToken create(Long userId, String loginType) {
        return issue(userId, UUID.randomUUID().toString(), loginType);
    }

    /**
     * 校验刷新令牌，只读不修改
     */
    public RefreshSession resolve(String refreshToken) {
        SysRefreshTokenDO token = refreshTokenMapper.selectByTokenHash(hash(refreshToken));
        if (token == null) {
            throw new ServiceException(CodeConstants.TOKEN_ERROR);
        }
        if (Boolean.TRUE.equals(token.getUsed())) {
            // 已轮换的令牌再次出现，说明令牌泄露，吊销整个会话
            refreshTokenMapper.deleteBySessionId(token.getSessionId());
            log.warn("刷新令牌被重复使用，已吊销会话：userId={}, sessionId={}", token.getUserId(), token.getSessionId());
            throw new ServiceException(CodeConstants.TOKEN_ERROR);
        }
        if (token.getExpiresTime().isBefore(LocalDateTime.now())) {
            refreshTokenMapper.deleteById(token.getId());
            throw new ServiceException(CodeConstants.TOKEN_EXPIRED);
        }
        return new RefreshSession(token.getUserId(), token.getSessionId(), token.getLoginType());
    }

    /**
     * 轮换令牌：原子标记旧令牌已用，再签发同会话的新令牌
     */
    public IssuedRefreshToken rotate(RefreshSession session, String usedToken) {
        int updated = refreshTokenMapper.markUsed(hash(usedToken));
        if (updated == 0) {
            // 并发刷新或重放，吊销会话让客户端重新登录
            refreshTokenMapper.deleteBySessionId(session.sessionId());
            throw new ServiceException(CodeConstants.TOKEN_ERROR);
        }
        return issue(session.userId(), session.sessionId(), session.loginType());
    }

    /**
     * 吊销单个登录会话
     */
    public void revokeSession(String sessionId) {
        if (StringUtils.hasText(sessionId)) {
            refreshTokenMapper.deleteBySessionId(sessionId);
        }
    }

    /**
     * 吊销用户全部会话，用于改密码、停用、删除用户
     */
    public void revokeAll(Long userId) {
        if (userId != null) {
            refreshTokenMapper.deleteByUserId(userId);
        }
    }

    /**
     * 每天凌晨清理过期令牌
     */
    @Scheduled(cron = "0 0 4 * * ?")
    public void cleanExpired() {
        int count = refreshTokenMapper.deleteExpired(LocalDateTime.now());
        if (count > 0) {
            log.info("清理过期刷新令牌 {} 条", count);
        }
    }

    private IssuedRefreshToken issue(Long userId, String sessionId, String loginType) {
        String token = generateToken();
        LocalDateTime expiresTime = LocalDateTime.now().plus(securityProperties.getJwt().getRefreshTokenTtl());

        SysRefreshTokenDO tokenDO = new SysRefreshTokenDO();
        tokenDO.setSessionId(sessionId);
        tokenDO.setUserId(userId);
        tokenDO.setLoginType(loginType);
        tokenDO.setTokenHash(hash(token));
        tokenDO.setExpiresTime(expiresTime);
        tokenDO.setUsed(false);
        tokenDO.setCreateTime(LocalDateTime.now());
        refreshTokenMapper.insert(tokenDO);

        return new IssuedRefreshToken(token, sessionId, expiresTime);
    }

    private String generateToken() {
        byte[] bytes = new byte[TOKEN_BYTES];
        SECURE_RANDOM.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    private String hash(String token) {
        try {
            byte[] digest = MessageDigest.getInstance("SHA-256").digest(token.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 不可用", e);
        }
    }

    /**
     * 签发结果
     */
    public record IssuedRefreshToken(String token, String sessionId, LocalDateTime expiresTime) {
    }

    /**
     * 已校验的刷新令牌所属会话
     */
    public record RefreshSession(Long userId, String sessionId, String loginType) {
    }

}
