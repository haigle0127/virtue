package cn.haigle.virtue.common.interceptor.permission.config;

import cn.haigle.virtue.common.interceptor.permission.authentication.PowerPermission;
import cn.haigle.virtue.common.interceptor.permission.authentication.provider.RedisPowerPermission;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 权限配置
 * @author haigle
 * @date 2019/9/11 16:15
 */
@Configuration
public class PowerPermissionConfig {

    @Bean
    public PowerPermission powerPermission() {
        return new RedisPowerPermission();
    }

}
