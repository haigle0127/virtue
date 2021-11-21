package cn.haigle.virtue.system.service.impl;

import cn.haigle.virtue.system.dao.LoginDao;
import cn.haigle.virtue.system.dao.cache.UserPermissionCacheDao;
import cn.haigle.virtue.system.entity.po.UserPermissionDo;
import cn.haigle.virtue.system.service.UserPermissionCacheService;
import cn.haigle.virtue.common.annotation.transaction.ReadOnly;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * redis用户权限缓存实现
 * @author haigle
 * @date 2020/11/29 19:05
 */
@Service("userPermissionCacheService")
public class UserPermissionCacheRedisServiceImpl implements UserPermissionCacheService {

    private final UserPermissionCacheDao userPermissionCacheDao;

    private final LoginDao loginDao;

    public UserPermissionCacheRedisServiceImpl(UserPermissionCacheDao userPermissionCacheDao,
                                               LoginDao loginDao) {
        this.userPermissionCacheDao = userPermissionCacheDao;
        this.loginDao = loginDao;
    }

    @Override
    public void set(Long uid, List<String> permissions) {
        userPermissionCacheDao.save(uid, permissions);
    }

    @ReadOnly
    @Override
    public List<String> get(Long uid) {
        UserPermissionDo userPermissionDo = userPermissionCacheDao.findById(uid);
        if(userPermissionDo == null || userPermissionDo.getPermissions().size() == 0) {
            List<String> permissions = loginDao.findPermissionsById(uid);
            if (permissions != null) {
                userPermissionCacheDao.save(uid, permissions);
                return permissions;
            }
        }
        return userPermissionDo.getPermissions();
    }

    @Override
    public void remove(Long uid) {
        userPermissionCacheDao.remove(uid);
    }

    @Override
    public void removeAll() {
        userPermissionCacheDao.removeAll();
    }

    @Override
    public void removeAll(Long... ids) {
        userPermissionCacheDao.removeAll(ids);
    }
}
