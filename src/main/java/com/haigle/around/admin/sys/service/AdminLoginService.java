package com.haigle.around.admin.sys.service;

import com.haigle.around.admin.sys.entity.ao.AdminLoginAo;
import com.haigle.around.admin.sys.entity.ao.AdminRegisterAo;
import com.haigle.around.admin.sys.entity.po.AdminUserLoginPo;

/**
 * 登录、注册功能
 * @author haigle
 * @date 2019/3/6 9:38
 */
public interface AdminLoginService {

    /**
     * 登录服务
     * @param adminLoginAo LoginDto
     * @return UserLoginPo
     * @author haigle
     * @date 2019/3/6 9:58
     */
    AdminUserLoginPo login(AdminLoginAo adminLoginAo);

    /**
     * 邮箱是否已被使用
     * @param email String
     * @return UserEntity
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
     * @param adminRegisterAo RegisterDto
     * @return token
     * @author haigle
     * @date 2019/3/6 13:59
     */
    String save(AdminRegisterAo adminRegisterAo);

}
