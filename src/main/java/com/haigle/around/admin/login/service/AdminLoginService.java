package com.haigle.around.admin.login.service;

import com.haigle.around.admin.login.entity.query.AdminLoginQuery;
import com.haigle.around.admin.login.entity.ao.AdminRegisterAO;
import com.haigle.around.admin.login.entity.bo.AdminUserLoginBO;

/**
 * 登录、注册功能
 * @author haigle
 * @date 2019/3/6 9:38
 */
public interface AdminLoginService {

    /**
     * 登录服务
     * @param adminLoginQuery AdminLoginQuery
     * @return UserLoginPo
     * @author haigle
     * @date 2019/3/6 9:58
     */
    AdminUserLoginBO login(AdminLoginQuery adminLoginQuery);

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
     * @param adminRegisterAO AdminRegisterAO
     * @return token
     * @author haigle
     * @date 2019/3/6 13:59
     */
    String save(AdminRegisterAO adminRegisterAO);

}
