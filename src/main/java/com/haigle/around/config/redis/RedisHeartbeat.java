package com.haigle.around.config.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 定时发送心跳，保证不超时
 * <p>
 * 因为redis建立连接后长时间不操作，会在下一次操作后超时或者与服务器断开然后重新连接，
 * 现在使用这种方式是没有找到解决办法的方案
 *
 * @author 阮雪峰
 * @date 2019/3/28 15:32
 */
@Slf4j
@Component
public class RedisHeartbeat {

    private final RedisTemplate<String, List<String>> redisTemplate;

    public RedisHeartbeat(RedisTemplate<String, List<String>> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Scheduled(cron = "0 0/3 * * * *")
    public void heartbeat() {
        redisTemplate.opsForValue().get("heartbeat");
    }
}
