package com.haigle.around.config.durid;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * sql监控配置
 * @author haigle
 * @date 2018/11/28 9:48
 */
@Configuration
public class DruidConfiguration {
    
    @Bean
    public ServletRegistrationBean<StatViewServlet> druidServlet() {

        ServletRegistrationBean<StatViewServlet> servletRegistrationBean
                = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");

        /*
         * IP白名单
         */
        servletRegistrationBean.addInitParameter("allow", "192.168.31.42,127.0.0.1");

        /*
         * IP黑名单(共同存在时，deny优先于allow)
         */
        servletRegistrationBean.addInitParameter("deny", "192.168.1.100");

        /*
         * 控制台管理用户
         */
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "admin");

        /*
         * 是否能够重置数据 禁用HTML页面上的“Reset All”功能
         */
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<WebStatFilter> filterRegistrationBean() {
        FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<>(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
    
}
