package com.haigle.around.config.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.haigle.around.common.interceptor.PermissionInterceptor;
import com.haigle.around.common.interceptor.model.ApiResultI18n;
import com.haigle.around.config.Constant;
import com.haigle.around.config.i18n.interceptor.CustomLocaleChangeInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;

/**
 * web相关配置（前后分离需要加注解@EnableWebMvc）
 * @author haigle
 * @date 2018/5/31 15:04
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    /**
     * 跨域
     * .allowedOrigins("*", "http://101.200.156.242:8081", "http://127.0.0.1:8080", "http://localhost:8080", "http://localhost:8088", "http://localhost")
     * @author haigle
     * @date 2018/5/31 15:04
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("Lang", "content-type", Constant.TOKEN, "Access-Control-Allow-Origin", "Content-Type,X-Requested-With", "accept", "Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers")
                .maxAge(3600);
    }

    /**
     * 解决yml配置文件配置静态文件不好使问题
     * @author haigle
     * @date 2019/1/28 9:55
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    /**
     * 解决Long、BigInteger类型id在前端丢失精度问题
     * @author haigle
     * @date 2018/5/31 15:04
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();

        simpleModule.addSerializer(BigInteger.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);
        jackson2HttpMessageConverter.setObjectMapper(objectMapper);
        converters.add(jackson2HttpMessageConverter);
        converters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
    }

    @Bean
    public PermissionInterceptor permissionInterceptor(){
        return new PermissionInterceptor();
    }

    @Bean
    public CustomLocaleChangeInterceptor customLocaleChangeInterceptor(){
        return new CustomLocaleChangeInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        /*
         * 国际化
         */
        registry.addInterceptor(new CustomLocaleChangeInterceptor()).addPathPatterns(Constant.API+"/**");

        /*
         * 权限
         */
        registry.addInterceptor(permissionInterceptor()).addPathPatterns(Constant.ADMIN+"/**").addPathPatterns(Constant.API+"/**");
    }

    /**
     * i18n国际化
     * @author haigle
     * @date 2019/7/29 14:15
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.CHINA);
        return slr;
    }

    @Bean
    public ApiResultI18n apiResultI18n(){
        return new ApiResultI18n();
    }

}
