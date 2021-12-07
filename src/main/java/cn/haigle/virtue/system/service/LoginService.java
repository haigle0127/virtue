package cn.haigle.virtue.system.service;

import cn.haigle.virtue.system.entity.ao.LoginAo;
import cn.haigle.virtue.system.entity.ao.RegisterAo;
import cn.haigle.virtue.system.entity.vo.UserInfoVo;
import cn.haigle.virtue.system.entity.vo.LoginUserInfoVo;

/**
 * 登录、注册功能
 * @author haigle
 * @date 2019/3/6 9:38
 */
public interface LoginService {

    /**
     * 登录服务
     * @param ao AdminLoginAO
     * @return LoginUserInfoVo
     * @author haigle
     * @date 2019/3/6 9:58
     */
    LoginUserInfoVo login(LoginAo ao);

    /**
     * 用户信息
     * @param uid 用户ID
     * @return UserInfoVo 用户信息
     * @author haigle
     * @date 2019-06-09 14:06
     */
    UserInfoVo userInfo(Long uid);

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
     * @param registerAo AdminRegisterAo
     * @author haigle
     * @date 2019/3/6 13:59
     */
    void save(RegisterAo registerAo);

}
