package cn.haigle.virtue.service.login;

import cn.haigle.virtue.controller.admin.login.vo.LoginReqVO;
import java.util.List;

/**
 * 登录、注册功能
 * @author haigle
 * @date 2019/3/6 9:38
 */
public interface LoginService {

    /**
     * 登录服务
     * @param reqVO LoginReqVO
     * @param loginType 登录类型（根据请求路径自动判断：admin/app/pc）
     * @return token
     * @author haigle
     * @date 2019/3/6 9:58
     */
    String login(LoginReqVO reqVO, String loginType);

    /**
     * 获取用户所拥有的权限列表
     * @param userId 用户ID
     * @return List<String> 用户所拥有的权限列表
     * @author haigle
     * @date 2021/11/18 20:15
     */
    List<String> getPermission(Long userId);



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

}
