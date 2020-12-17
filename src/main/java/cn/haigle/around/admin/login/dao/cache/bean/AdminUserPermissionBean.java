package cn.haigle.around.admin.login.dao.cache.bean;

import cn.haigle.around.admin.login.entity.po.AdminUserPermissionDO;
import cn.haigle.around.config.redis.RedisConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
public class AdminUserPermissionBean {

    @Bean("userPermissionCacheRedisTemplate")
    public RedisTemplate<String, AdminUserPermissionDO> userPermissionCacheRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, AdminUserPermissionDO> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
        Jackson2JsonRedisSerializer<AdminUserPermissionDO> serializer = new Jackson2JsonRedisSerializer<>(AdminUserPermissionDO.class);

        return RedisConfig.configTemplate(template, serializer);
    }

}
