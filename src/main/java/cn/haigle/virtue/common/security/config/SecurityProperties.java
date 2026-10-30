package cn.haigle.virtue.common.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * 安全相关配置
 *
 * @author haigle
 */
@Data
@ConfigurationProperties(prefix = "virtue.security")
public class SecurityProperties {

    /**
     * JWT 配置
     */
    private Jwt jwt = new Jwt();

    /**
     * 无需登录即可访问的路径
     */
    private List<String> publicUrls = new ArrayList<>();

    /**
     * 测试登录开关：开启后 Authorization: Bearer test1 直接以用户1（超级管理员）身份访问
     */
    private boolean testLoginEnabled = false;

    @Data
    public static class Jwt {

        /**
         * 签名密钥，HS256 至少 32 字节
         */
        private String secret;

        /**
         * 签发者
         */
        private String issuer;

        /**
         * 访问令牌有效期
         */
        private Duration accessTokenTtl;

        /**
         * 刷新令牌有效期
         */
        private Duration refreshTokenTtl;

    }

}
