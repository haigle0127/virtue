package cn.haigle.virtue.admin.login.service.impl;

import cn.haigle.virtue.admin.login.dao.AdminLoginDao;
import cn.haigle.virtue.admin.login.dao.cache.AdminUserPermissionCacheDao;
import cn.haigle.virtue.admin.login.entity.po.AdminUserPermissionDO;
import cn.haigle.virtue.admin.login.service.AdminUserPermissionCacheService;
import cn.haigle.virtue.common.annotation.transaction.ReadOnly;
import org.springframework.stereotype.Service;

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
        AdminUserPermissionDO adminUserPermissionDO = adminUserPermissionCacheDao.findById(uid);
        if(adminUserPermissionDO == null || adminUserPermissionDO.getPermissions().size() == 0) {
            Set<String> permissions = adminLoginDao.findPermissionsById(uid);
            if (permissions != null) {
                adminUserPermissionCacheDao.save(uid, permissions);
                return permissions;
            }
        }
        return adminUserPermissionDO.getPermissions();
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
