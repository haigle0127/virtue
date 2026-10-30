package cn.haigle.virtue.common.security.jwt;

import cn.haigle.virtue.common.security.config.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtIssuerValidator;
import org.springframework.security.oauth2.jwt.JwtTypeValidator;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;

/**
 * JWT 编解码配置
 * <p>只有访问令牌是 JWT，刷新令牌是不透明随机串，由 {@code RefreshTokenService} 管理</p>
 *
 * @author haigle
 */
@Slf4j
@Configuration
public class JwtConfig {

    public static final String CLAIM_USERNAME = "username";
    public static final String CLAIM_LOGIN_TYPE = "loginType";
    public static final String CLAIM_AUTHORITIES = "authorities";
    public static final String CLAIM_SESSION_ID = "sid";

    /**
     * RFC 9068 规定的访问令牌类型
     */
    public static final String TOKEN_TYPE_ACCESS = "at+jwt";

    @Bean
    public SecretKey jwtSecretKey(SecurityProperties properties) {
        return new SecretKeySpec(properties.getJwt().getSecret().getBytes(StandardCharsets.UTF_8), "HmacSHA256");
    }

    @Bean
    public JwtEncoder jwtEncoder(SecretKey jwtSecretKey) {
        return NimbusJwtEncoder.withSecretKey(jwtSecretKey).build();
    }

    /**
     * 访问令牌解码器：校验签名、签发者、有效期和 typ=at+jwt
     */
    @Bean
    public JwtDecoder accessTokenDecoder(SecurityProperties properties, SecretKey jwtSecretKey) {
        NimbusJwtDecoder decoder = NimbusJwtDecoder.withSecretKey(jwtSecretKey)
                .macAlgorithm(MacAlgorithm.HS256)
                .build();
        // Spring Security 默认只接受 typ=JWT，这里显式声明访问令牌的 at+jwt 类型
        decoder.setJwtValidator(JwtValidators.createDefaultWithValidators(
                new JwtIssuerValidator(properties.getJwt().getIssuer()),
                new JwtTypeValidator(TOKEN_TYPE_ACCESS)));
        if (properties.isTestLoginEnabled()) {
            log.warn("测试登录已开启：Authorization: Bearer {} 将以用户 {}（超级管理员）身份访问，切勿用于生产环境",
                    TestLoginJwtDecoder.TEST_TOKEN, TestLoginJwtDecoder.TEST_USER_ID);
            return new TestLoginJwtDecoder(decoder);
        }
        return decoder;
    }

    /**
     * 把 JWT 的 authorities 声明转换成 Spring Security 权限
     */
    @Bean
    public Converter<Jwt, JwtAuthenticationToken> jwtAuthenticationConverter() {
        return jwt -> {
            List<String> roles = jwt.getClaimAsStringList(CLAIM_AUTHORITIES);
            Collection<GrantedAuthority> authorities = roles == null ? List.of()
                    : roles.stream().map(SimpleGrantedAuthority::new).map(GrantedAuthority.class::cast).toList();
            String username = jwt.getClaimAsString(CLAIM_USERNAME);
            return new JwtAuthenticationToken(jwt, authorities, username != null ? username : jwt.getSubject());
        };
    }

}
