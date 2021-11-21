package cn.haigle.virtue.system.dao.cache.bean;

import cn.haigle.virtue.system.entity.po.UserPermissionDo;
import cn.haigle.virtue.config.redis.RedisConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
public class UserPermissionBean {

    @Bean("userPermissionCacheRedisTemplate")
    public RedisTemplate<String, UserPermissionDo> userPermissionCacheRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, UserPermissionDo> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
        Jackson2JsonRedisSerializer<UserPermissionDo> serializer = new Jackson2JsonRedisSerializer<>(UserPermissionDo.class);

        return RedisConfig.configTemplate(template, serializer);
    }

}
