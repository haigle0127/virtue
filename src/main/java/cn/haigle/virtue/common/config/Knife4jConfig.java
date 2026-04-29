package cn.haigle.virtue.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Knife4j 接口文档配置
 * @author haigle
 * @date 2026/4/29 15:10
 */
@Configuration
public class Knife4jConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Virtue Pro 接口文档")
                        .version("3.0.0")
                        .description("基于 Spring Boot + JWT 的后端接口文档")
                        .contact(new Contact()
                                .name("haigle")
                                .url("https://gitee.com/haigle/virtue"))
                        .license(new License()
                                .name("MIT")
                                .url("https://gitee.com/haigle/virtue/blob/master/LICENSE")));
    }
}
