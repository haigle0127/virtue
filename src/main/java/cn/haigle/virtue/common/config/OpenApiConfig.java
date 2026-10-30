package cn.haigle.virtue.common.config;

import cn.haigle.virtue.common.security.config.SecurityProperties;
import cn.haigle.virtue.common.security.jwt.TestLoginJwtDecoder;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.GlobalOperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * OpenAPI 接口文档配置
 * @author haigle
 * @date 2026/4/29 15:10
 */
@Configuration
public class OpenApiConfig {

    /**
     * 安全方案名称，需与接口上的 SecurityRequirement 引用一致
     */
    private static final String SECURITY_SCHEME_NAME = "Authorization";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("virtue 接口文档")
                        .version("3.0.0")
                        .description("基于 Spring Boot + Spring Security JWT 的后端接口文档")
                        .contact(new Contact()
                                .name("haigle")
                                .url("https://gitee.com/haigle/virtue"))
                        .license(new License()
                                .name("MIT")
                                .url("https://gitee.com/haigle/virtue/blob/master/LICENSE")))
                .components(new Components().addSecuritySchemes(SECURITY_SCHEME_NAME,
                        new SecurityScheme()
                                .name(SECURITY_SCHEME_NAME)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .in(SecurityScheme.In.HEADER)
                                .description("登录接口返回的 accessToken，只填 token 本身，不用加 Bearer 前缀")));
    }

    /**
     * 给标注了 {@link PreAuthorize} 的接口加锁
     * <p>默认声明 security，Swagger 点 Authorize 填一次 token 即可；本地开启测试登录时改为预填 Authorization 请求头</p>
     * 注意：配置了 springdoc.group-configs 时必须用 GlobalOperationCustomizer，普通 OperationCustomizer 不会生效
     */
    @Bean
    public GlobalOperationCustomizer requireLoginOperationCustomizer(SecurityProperties properties) {
        return (operation, handlerMethod) -> {
            boolean requireLogin = handlerMethod.hasMethodAnnotation(PreAuthorize.class)
                    || AnnotatedElementUtils.hasAnnotation(handlerMethod.getBeanType(), PreAuthorize.class);
            if (!requireLogin) {
                return operation;
            }
            if (properties.isTestLoginEnabled()) {
                // 本地开启测试登录：只保留预填的 Authorization 头，避免文档再生成一个空的 security 认证行
                operation.addParametersItem(new Parameter()
                        .name(SECURITY_SCHEME_NAME)
                        .in("header")
                        .required(true)
                        .description("测试登录令牌，本地默认值可直接使用")
                        .schema(new StringSchema()._default("Bearer " + TestLoginJwtDecoder.TEST_TOKEN)));
            } else {
                operation.addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME));
            }
            return operation;
        };
    }
}
