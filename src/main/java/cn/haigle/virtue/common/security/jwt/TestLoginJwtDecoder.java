package cn.haigle.virtue.common.security.jwt;

import cn.haigle.virtue.common.enums.LoginTypeEnum;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * 测试登录解码器：把固定口令转换成固定用户的 JWT，方便本地调试接口
 * <p>只有 virtue.security.test-login-enabled=true 时才会被启用</p>
 *
 * @author haigle
 */
public class TestLoginJwtDecoder implements JwtDecoder {

    /**
     * 测试登录口令，请求头为 Authorization: Bearer test1
     */
    public static final String TEST_TOKEN = "test1";

    /**
     * 测试登录使用的用户，数据库中的超级管理员
     */
    public static final Long TEST_USER_ID = 1L;

    private static final String TEST_USERNAME = "admin";

    /**
     * 超级管理员角色标识，对应数据库 role_info.mark
     */
    private static final String TEST_ROLE_MARK = "admin";

    private static final String TEST_SESSION_ID = "test-login";

    private final JwtDecoder delegate;

    public TestLoginJwtDecoder(JwtDecoder delegate) {
        this.delegate = delegate;
    }

    @Override
    public Jwt decode(String token) {
        // 其他令牌仍然按正常 JWT 校验
        if (!TEST_TOKEN.equals(token)) {
            return delegate.decode(token);
        }

        Instant now = Instant.now();
        return Jwt.withTokenValue(token)
                .header("alg", "HS256")
                .subject(String.valueOf(TEST_USER_ID))
                .issuedAt(now)
                .expiresAt(now.plus(365, ChronoUnit.DAYS))
                .claim(JwtConfig.CLAIM_USERNAME, TEST_USERNAME)
                .claim(JwtConfig.CLAIM_LOGIN_TYPE, LoginTypeEnum.ADMIN.getCode())
                .claim(JwtConfig.CLAIM_AUTHORITIES, List.of("ROLE_" + TEST_ROLE_MARK))
                .claim(JwtConfig.CLAIM_SESSION_ID, TEST_SESSION_ID)
                .build();
    }

}
