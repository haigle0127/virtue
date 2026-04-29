package cn.haigle.virtue.common.web.config;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * 自动前缀 WebMvc 配置
 * <p>注册自定义的 {@link AutoPrefixRequestMappingHandlerMapping}</p>
 */
@Configuration
public class AutoPrefixWebMvcConfig implements WebMvcRegistrations {

    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return new AutoPrefixRequestMappingHandlerMapping();
    }
}
