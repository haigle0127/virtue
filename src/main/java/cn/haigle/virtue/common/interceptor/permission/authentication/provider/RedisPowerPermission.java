package cn.haigle.virtue.common.interceptor.permission.authentication.provider;

import cn.haigle.virtue.system.dao.AdminUserDao;
import cn.haigle.virtue.common.interceptor.permission.authentication.PowerPermission;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * 对于权限的Redis实现
 * @author haigle
 * @date 2019/9/11 10:47
 */
public class RedisPowerPermission implements PowerPermission {

    private static final String PREFIX_KEY = "user-";

    @Resource
    private RedisTemplate<String, List<String>> redisTemplate;

    @Resource
    private AdminUserDao adminUserDao;

    @Override
    public void setPermission(Long uid, List<String> permissions) {
        redisTemplate.opsForValue().set(PREFIX_KEY + uid, permissions);
    }

    @Override
    public List<String> getPermission(Long uid) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(PREFIX_KEY + uid))
                .orElseGet(() -> {
                    List<String> list = adminUserDao.getMenuList(uid);
                    redisTemplate.opsForValue().set(PREFIX_KEY + uid, list);
                    return list;
                });
    }

    @Override
    public void removePermission(Long uid) {
        redisTemplate.delete(PREFIX_KEY + uid);
    }

    @Override
    public void removeAll() {
        Set<String> keys = redisTemplate.keys(PREFIX_KEY);
        if (!CollectionUtils.isEmpty(keys)) {
            redisTemplate.delete(keys);
        }
    }
}
