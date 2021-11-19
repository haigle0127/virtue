package cn.haigle.virtue.system.service;

import cn.haigle.virtue.system.entity.ao.AdminLoginAo;
import cn.haigle.virtue.system.entity.ao.AdminRegisterAo;
import cn.haigle.virtue.system.entity.vo.AdminUserAndRolesVo;
import cn.haigle.virtue.system.entity.vo.LoginUserInfoVo;

import java.util.List;

/**
 * 登录、注册功能
 * @author haigle
 * @date 2019/3/6 9:38
 */
public interface AdminLoginService {

    /**
     * 登录服务
     * @param ao AdminLoginAO
     * @return LoginUserInfoVo
     * @author haigle
     * @date 2019/3/6 9:58
     */
    LoginUserInfoVo login(AdminLoginAo ao);

    /**
     * 获取用户所拥有的权限列表
     * @param userId 用户ID
     * @return List<String> 用户所拥有的权限列表
     * @author haigle
     * @date 2021/11/18 20:15
     */
    List<String> getPermission(Long userId);

    /**
     * 用户信息
     * @param uid 用户ID
     * @return getAdminUserAndRoles 用户信息
     * @author haigle
     * @date 2019-06-09 14:06
     */
    AdminUserAndRolesVo getAdminUserAndRoles(Long uid);

    /**
     * 邮箱是否已被使用
     * @param email String
     * @return boolean
     * @author haigle
     * @date 2019/3/6 13:55
     */
    boolean emailIsExist(String email);

    /**
     * 发送邮箱验证码
     * @param email 邮箱
     * @author haigle
     * @date 2019/3/6 14:49
     */
    void sendEmailCode(String email);

    /**
     * 保存新用户
     * @param adminRegisterAo AdminRegisterAo
     * @author haigle
     * @date 2019/3/6 13:59
     */
    void save(AdminRegisterAo adminRegisterAo);

}
