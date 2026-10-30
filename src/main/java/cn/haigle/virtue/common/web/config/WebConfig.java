package cn.haigle.virtue.common.web.config;

import org.springframework.boot.jackson.autoconfigure.JsonMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.module.SimpleModule;
import tools.jackson.databind.ser.std.ToStringSerializer;

import java.math.BigInteger;

/**
 * web相关配置
 * @author haigle
 * @date 2018/5/31 15:04
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 跨域配置
     * @author haigle
     * @date 2018/5/31 15:04
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }

    /**
     * 解决yml配置文件配置静态文件不好使问题
     * @author haigle
     * @date 2019/1/28 9:55
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/resources/")
                .addResourceLocations("classpath:/resources/")
                .addResourceLocations("classpath:/static/");
    }

    /**
     * Long、BigInteger 类型 id 序列化为字符串，避免精度丢失
     * Spring Boot 4 默认使用 Jackson 3（tools.jackson），通过定制 JsonMapper 生效
     * @author haigle
     * @date 2018/5/31 15:04
     */
    @Bean
    public JsonMapperBuilderCustomizer bigNumberToStringCustomizer() {
        SimpleModule simpleModule = new SimpleModule("BigNumberToString");
        simpleModule.addSerializer(BigInteger.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        return builder -> builder
                // 忽略未知的JSON字段
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .addModule(simpleModule);
    }

}
