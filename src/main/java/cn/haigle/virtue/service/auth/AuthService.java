package cn.haigle.virtue.service.auth;

import cn.haigle.virtue.controller.admin.auth.vo.AuthLoginRespVO;
import cn.haigle.virtue.controller.admin.auth.vo.LoginReqVO;
import cn.haigle.virtue.controller.admin.user.vo.UserInfoVO;

/**
 * 认证服务
 *
 * @author haigle
 */
public interface AuthService {

    /**
     * 登录，返回访问令牌和刷新令牌
     */
    AuthLoginRespVO login(LoginReqVO reqVO, String loginType);

    /**
     * 用刷新令牌换取新的访问令牌，刷新令牌同时轮换
     */
    AuthLoginRespVO refreshToken(String refreshToken);

    /**
     * 退出登录，吊销当前会话的刷新令牌
     */
    void logout();

    /**
     * 当前登录用户信息
     */
    UserInfoVO getCurrentUser();

}
