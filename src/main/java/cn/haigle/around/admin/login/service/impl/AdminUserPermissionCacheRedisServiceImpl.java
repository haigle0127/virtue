package cn.haigle.around.admin.login.service.impl;

import cn.haigle.around.admin.login.dao.AdminLoginDao;
import cn.haigle.around.admin.login.dao.cache.AdminUserPermissionCacheDao;
import cn.haigle.around.admin.login.entity.po.AdminUserPermissionDO;
import cn.haigle.around.admin.login.service.AdminUserPermissionCacheService;
import cn.haigle.around.common.annotation.transaction.ReadOnly;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

/**
 * redis用户权限缓存实现
 * @author haigle
 * @date 2020/11/29 19:05
 */
@Service("adminUserPermissionCacheService")
public class AdminUserPermissionCacheRedisServiceImpl implements AdminUserPermissionCacheService {

    private final AdminUserPermissionCacheDao adminUserPermissionCacheDao;

    private final AdminLoginDao adminLoginDao;

    public AdminUserPermissionCacheRedisServiceImpl(AdminUserPermissionCacheDao adminUserPermissionCacheDao,
                                                    AdminLoginDao adminLoginDao) {
        this.adminUserPermissionCacheDao = adminUserPermissionCacheDao;
        this.adminLoginDao = adminLoginDao;
    }

    @Override
    public void set(Long uid, Set<String> permissions) {
        adminUserPermissionCacheDao.save(uid, permissions);
    }

    @ReadOnly
    @Override
    public Set<String> get(Long uid) {
        return Optional.ofNullable(adminUserPermissionCacheDao.findById(uid))
                .map(AdminUserPermissionDO::getPermissions)
                .orElseGet(() -> {
                    Set<String> permissions = adminLoginDao.findRolesById(uid);
                    if (permissions != null) {
                        adminUserPermissionCacheDao.save(uid, permissions);
                    }
                    return permissions;
                });
    }

    @Override
    public void remove(Long uid) {
        adminUserPermissionCacheDao.remove(uid);
    }

    @Override
    public void removeAll() {
        adminUserPermissionCacheDao.removeAll();
    }

    @Override
    public void removeAll(Long... ids) {
        adminUserPermissionCacheDao.removeAll(ids);
    }
}
