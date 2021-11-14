package cn.haigle.virtue.system.service.impl;

import cn.haigle.virtue.system.dao.AdminLoginDao;
import cn.haigle.virtue.system.dao.cache.AdminUserPermissionCacheDao;
import cn.haigle.virtue.system.entity.po.AdminUserPermissionDo;
import cn.haigle.virtue.system.service.AdminUserPermissionCacheService;
import cn.haigle.virtue.common.annotation.transaction.ReadOnly;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void set(Long uid, List<String> permissions) {
        adminUserPermissionCacheDao.save(uid, permissions);
    }

    @ReadOnly
    @Override
    public List<String> get(Long uid) {
        AdminUserPermissionDo adminUserPermissionDo = adminUserPermissionCacheDao.findById(uid);
        if(adminUserPermissionDo == null || adminUserPermissionDo.getPermissions().size() == 0) {
            List<String> permissions = adminLoginDao.findPermissionsById(uid);
            if (permissions != null) {
                adminUserPermissionCacheDao.save(uid, permissions);
                return permissions;
            }
        }
        return adminUserPermissionDo.getPermissions();
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
