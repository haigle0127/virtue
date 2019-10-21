package com.haigle.around.common.interceptor.permission.enable.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * yml power配置
 * @author haigle
 * @date 2019/9/11 16:49
 */
@Component
@ConfigurationProperties(value = "hiclassmate.power-enable")
@Getter
@Setter
public class EnableProperties {

    /**
     * 缓存数据库 hardsearch mysql， redis redis
     */
    private String datacache = "hardsearch";

    /**
     * 反射，扫描权限包
     */
    private String reflections = "com.haigle.around.admin.controller";

    public EnableProperties() {

    }
}
