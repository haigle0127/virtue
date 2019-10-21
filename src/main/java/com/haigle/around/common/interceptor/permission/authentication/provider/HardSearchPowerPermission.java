package com.haigle.around.common.interceptor.permission.authentication.provider;

import com.haigle.around.admin.dao.AdminUserDao;
import com.haigle.around.common.interceptor.permission.authentication.PowerPermission;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 对于权限的mysql实现
 * @author haigle
 * @date 2019/9/11 10:47
 */
public class HardSearchPowerPermission implements PowerPermission {

    @Resource
    private AdminUserDao adminUserDao;

    @Override
    public void setPermission(Long uid, List<String> permissions) {

    }

    @Override
    public List<String> getPermission(Long uid) {
        return adminUserDao.getMenuList(uid);
    }

    @Override
    public void removePermission(Long uid) {

    }

    @Override
    public void removeAll() {

    }
}
