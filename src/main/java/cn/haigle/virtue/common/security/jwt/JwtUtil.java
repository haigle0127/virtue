package cn.haigle.virtue.common.security.jwt;

import cn.haigle.virtue.common.interceptor.exception.ServiceException;
import cn.haigle.virtue.common.interceptor.exception.enums.CodeConstants;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.util.Date;

/**
 * JWT 工具类
 * @author haigle
 * @date 2026/4/29 15:10
 */
@Slf4j
public class JwtUtil {

    private static final String SECRET = "CHANGE_ME_JWT_SECRET";
    private static final long EXPIRATION = 86400 * 1000; // 24小时
    private static final String LOGIN_TYPE_CLAIM = "loginType";

    /**
     * 生成 JWT Token
     *
     * @param userId 用户ID
     * @param loginType 登录类型
     * @return token 字符串
     */
    public static String generateToken(Long userId, String loginType) {
        try {
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(String.valueOf(userId))
                    .claim(LOGIN_TYPE_CLAIM, loginType)
                    .expirationTime(new Date(System.currentTimeMillis() + EXPIRATION))
                    .issueTime(new Date())
                    .build();

            SignedJWT signedJWT = new SignedJWT(
                    new JWSHeader(JWSAlgorithm.HS256),
                    claimsSet
            );

            signedJWT.sign(new MACSigner(SECRET.getBytes()));
            return signedJWT.serialize();
        } catch (JOSEException e) {
            log.error("JWT生成失败", e);
            throw new ServiceException("Token生成失败");
        }
    }

    /**
     * 解析 JWT Token，获取用户ID
     *
     * @param token token 字符串
     * @return 用户ID
     */
    public static Long parseToken(String token) {
        return parseTokenInfo(token).getUserId();
    }

    /**
     * 解析 JWT Token，获取登录类型
     *
     * @param token token 字符串
     * @return 登录类型
     */
    public static String parseLoginType(String token) {
        return parseTokenInfo(token).getLoginType();
    }

    private static TokenInfo parseTokenInfo(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(SECRET.getBytes());

            if (!signedJWT.verify(verifier)) {
                throw new ServiceException(CodeConstants.TOKEN_ERROR);
            }

            JWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();
            Date expirationTime = claimsSet.getExpirationTime();
            if (expirationTime != null && expirationTime.before(new Date())) {
                throw new ServiceException(CodeConstants.TOKEN_EXPIRED);
            }

            Long userId = Long.valueOf(claimsSet.getSubject());
            String loginType = (String) claimsSet.getClaim(LOGIN_TYPE_CLAIM);
            return new TokenInfo(userId, loginType);
        } catch (ParseException | JOSEException e) {
            log.error("JWT解析失败", e);
            throw new ServiceException(CodeConstants.TOKEN_ERROR);
        }
    }

    public static class TokenInfo {
        private final Long userId;
        private final String loginType;

        public TokenInfo(Long userId, String loginType) {
            this.userId = userId;
            this.loginType = loginType;
        }

        public Long getUserId() {
            return userId;
        }

        public String getLoginType() {
            return loginType;
        }
    }
}
