package cn.haigle.virtue.system.dao.cache.impl;

import cn.haigle.virtue.system.dao.cache.UserPowerCacheDao;
import cn.haigle.virtue.system.entity.po.UserPowerDo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author haigle
 * @date 2020/11/29 19:09
 */
@Service("adminUserPowerCacheDao")
public class UserPowerCacheRedisDaoImpl implements UserPowerCacheDao {

    private static final String KEY = "user: power: %s";

    /**
     * 权限缓存过期时间为2小时
     */
    private static final int PERMISSION_CACHE_HOUR = 2;

    private final RedisTemplate<String, UserPowerDo> userPowerCacheRedisTemplate;

    public UserPowerCacheRedisDaoImpl(RedisTemplate<String, UserPowerDo> userPowerCacheRedisTemplate) {
        this.userPowerCacheRedisTemplate = userPowerCacheRedisTemplate;
    }

    @Override
    public UserPowerDo findById(Long uid) {
        return userPowerCacheRedisTemplate.opsForValue().get(String.format(KEY, uid));
    }

    @Override
    public void save(UserPowerDo userPowerDo) {
        userPowerCacheRedisTemplate.opsForValue()
                .set(String.format(KEY, userPowerDo.getId()),
                        userPowerDo,
                        Duration.ofHours(PERMISSION_CACHE_HOUR));
    }

    @Override
    public void remove(Long uid) {
        userPowerCacheRedisTemplate.delete(String.format(KEY, uid));
    }

    @Override
    public void removeAll() {
        Set<String> keys = userPowerCacheRedisTemplate.keys(String.format(KEY, "*"));
        if (!CollectionUtils.isEmpty(keys)) {
            userPowerCacheRedisTemplate.delete(keys);
        }
    }

    @Override
    public void removeAll(Long... ids) {
        Set<String> keys = new HashSet<>();
        for (Long id : ids) {
            keys.add(KEY + id);
        }
        userPowerCacheRedisTemplate.delete(keys);
    }

    @Override
    public List<String> getPowerKey(final Long id) {
        return findById(id).getPowerKeyList();
    }
}
