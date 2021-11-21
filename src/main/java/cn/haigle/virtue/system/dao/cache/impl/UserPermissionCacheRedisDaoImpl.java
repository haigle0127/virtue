package cn.haigle.virtue.system.dao.cache.impl;

import cn.haigle.virtue.system.dao.cache.UserPermissionCacheDao;
import cn.haigle.virtue.system.entity.po.UserPermissionDo;
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
@Service("adminUserPermissionCacheDao")
public class UserPermissionCacheRedisDaoImpl implements UserPermissionCacheDao {

    private static final String KEY = "user:permission:";

    /**
     * 权限缓存过期时间为2小时
     */
    private static final int PERMISSION_CACHE_HOUR = 2;

    private final RedisTemplate<String, UserPermissionDo> userPermissionCacheRedisTemplate;

    public UserPermissionCacheRedisDaoImpl(RedisTemplate<String, UserPermissionDo> userPermissionCacheRedisTemplate) {
        this.userPermissionCacheRedisTemplate = userPermissionCacheRedisTemplate;
    }

    @Override
    public UserPermissionDo findById(Long uid) {
        return userPermissionCacheRedisTemplate.opsForValue().get(KEY + uid);
    }

    @Override
    public void save(Long uid, List<String> permissions) {
        userPermissionCacheRedisTemplate.opsForValue()
                .set(KEY + uid,
                        new UserPermissionDo()
                                .setId(uid)
                                .setPermissions(permissions),
                        Duration.ofHours(PERMISSION_CACHE_HOUR)
                );
    }

    @Override
    public void remove(Long uid) {
        userPermissionCacheRedisTemplate.delete(KEY + uid);
    }

    @Override
    public void removeAll() {
        Set<String> keys = userPermissionCacheRedisTemplate.keys(KEY + "*");
        if (!CollectionUtils.isEmpty(keys)) {
            userPermissionCacheRedisTemplate.delete(keys);
        }
    }

    @Override
    public void removeAll(Long... ids) {
        Set<String> keys = new HashSet<>();
        for (Long id : ids) {
            keys.add(KEY + id);
        }
        userPermissionCacheRedisTemplate.delete(keys);
    }

//    public int[] twoSum(int[] nums, int target) {
//        int[] ba = new int[2];
//        for(int i=0;i<nums.length;i++) {
//            for(int j=0;j<nums.length;j++) {
//                if(i!=j) {
//                    int a = nums[i]+nums[j];
//                    if(target == a) {
//                        ba[0] = i;
//                        ba[1] = j;
//                    }
//                }
//            }
//        }
//    }
}
