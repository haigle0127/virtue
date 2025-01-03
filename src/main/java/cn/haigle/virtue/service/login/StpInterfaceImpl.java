package cn.haigle.virtue.service.login;

import cn.dev33.satoken.stp.StpInterface;
import cn.haigle.virtue.mapper.mysql.menu.MenuMapper;
import cn.haigle.virtue.mapper.mysql.role.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义权限加载接口实现类
 */
@Service    // 保证此类被 SpringBoot 扫描，完成 Sa-Token 的自定义权限验证扩展
public class StpInterfaceImpl implements StpInterface {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 返回一个账号所拥有的权限码集合 
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        long userId = Long.parseLong((String) loginId);
        if(userId == 1) {
            List<String> list = new ArrayList<>();
            list.add("*");
            return list;
        }
        return menuMapper.selectMenuPowerByUserId(userId);
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        long userId = Long.parseLong((String) loginId);
        if(userId == 1) {
            List<String> list = new ArrayList<>();
            list.add("*");
            return list;
        }
        return roleMapper.selectRoleMarkByUserId(userId);
    }

}
