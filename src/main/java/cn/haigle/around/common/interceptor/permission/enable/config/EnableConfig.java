package cn.haigle.around.common.interceptor.permission.enable.config;

import cn.haigle.around.common.interceptor.permission.authentication.PowerPermission;
import cn.haigle.around.common.interceptor.permission.authentication.provider.RedisPowerPermission;
import cn.haigle.around.common.interceptor.permission.enable.properties.EnableProperties;
import cn.haigle.around.common.interceptor.permission.authentication.provider.HardSearchPowerPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 权限配置
 * @author haigle
 * @date 2019/9/11 16:15
 */
@Configuration
public class EnableConfig {

    private final static String HARD_SEARCH = "hardsearch";
    private final static String REDIS = "redis";

    @Autowired
    private EnableProperties enableProperties;

    @Bean
    public PowerPermission powerPermission() {
        if(HARD_SEARCH.equals(enableProperties.getDatacache())) {
            return new HardSearchPowerPermission();
        }
        if(REDIS.equals(enableProperties.getDatacache())) {
            return new RedisPowerPermission();
        }
        return new HardSearchPowerPermission();
    }

}
