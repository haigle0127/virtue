package cn.haigle.virtue.common.security.jwt;

import cn.haigle.virtue.common.security.config.SecurityProperties;
import cn.haigle.virtue.common.security.model.LoginUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

/**
 * 访问令牌签发
 *
 * @author haigle
 */
@Component
public class JwtTokenProvider {

    private final JwtEncoder jwtEncoder;

    private final SecurityProperties securityProperties;

    public JwtTokenProvider(JwtEncoder jwtEncoder, SecurityProperties securityProperties) {
        this.jwtEncoder = jwtEncoder;
        this.securityProperties = securityProperties;
    }

    /**
     * 签发访问令牌，携带用户、登录类型、角色和登录会话
     */
    public String createAccessToken(LoginUser loginUser, String sessionId) {
        Instant now = Instant.now();
        JwtClaimsSet.Builder builder = JwtClaimsSet.builder()
                .issuer(securityProperties.getJwt().getIssuer())
                .subject(String.valueOf(loginUser.getId()))
                .issuedAt(now)
                .expiresAt(now.plus(securityProperties.getJwt().getAccessTokenTtl()))
                .id(UUID.randomUUID().toString())
                .claim(JwtConfig.CLAIM_SESSION_ID, sessionId)
                .claim(JwtConfig.CLAIM_LOGIN_TYPE, loginUser.getLoginType())
                .claim(JwtConfig.CLAIM_AUTHORITIES, loginUser.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList());
        builder.claim(JwtConfig.CLAIM_USERNAME, loginUser.getUsername());

        JwsHeader.Builder headerBuilder = JwsHeader.with(MacAlgorithm.HS256);
        headerBuilder.type(JwtConfig.TOKEN_TYPE_ACCESS);
        return jwtEncoder.encode(JwtEncoderParameters.from(headerBuilder.build(), builder.build())).getTokenValue();
    }

    /**
     * 访问令牌有效期（秒）
     */
    public long getAccessTokenTtlSeconds() {
        return securityProperties.getJwt().getAccessTokenTtl().toSeconds();
    }

}
