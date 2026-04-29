package cn.haigle.virtue.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.properties.SpringDocConfigProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Knife4j 增强扩展兼容修复
 *
 * <p>解决 Knife4j 4.5.0 与 springdoc 2.8.x 的兼容性问题：
 * {@code SpringDocConfigProperties#getGroupConfigs()} 在 springdoc 2.4.0+ 返回类型由 List 变为 Set，
 * 导致原始 {@link com.github.xiaoymin.knife4j.spring.extension.Knife4jOpenApiCustomizer}
 * 调用时出现 {@link NoSuchMethodError}。</p>
 *
 * <p>参考 <a href="https://github.com/xiaoymin/knife4j/issues/913">knife4j/issues/913</a></p>
 * @author haigle
 * @date 2026/4/29 15:10
 */
@Configuration
@Primary
@Slf4j
public class Knife4jOpenApiCustomizer extends com.github.xiaoymin.knife4j.spring.extension.Knife4jOpenApiCustomizer {

    public Knife4jOpenApiCustomizer(
            com.github.xiaoymin.knife4j.spring.configuration.Knife4jProperties knife4jProperties,
            SpringDocConfigProperties properties) {
        super(knife4jProperties, properties);
    }

    @Override
    public void customise(OpenAPI openApi) {
        try {
            super.customise(openApi);
        } catch (NoSuchMethodError e) {
            // Knife4j 4.5.0 编译时 getGroupConfigs() 返回 List，springdoc 2.8.x 运行时返回 Set
            // 捕获该兼容性错误，保留 Knife4j 其他增强功能（排序功能会丢失）
            log.warn("Knife4j order extension skipped due to springdoc 2.8 compatibility: {}", e.getMessage());
        }
    }
}
