package cn.haigle.virtue.controller.admin.login;

import cn.haigle.virtue.common.pojo.ApiResult;
import cn.haigle.virtue.common.web.Constant;
import cn.haigle.virtue.controller.admin.login.vo.LoginReqVO;
import cn.haigle.virtue.service.login.LoginService;
import cn.haigle.virtue.service.menu.MenuService;
import cn.haigle.virtue.service.user.UserService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 登录注册
 * @author haigle
 * @date 2019/6/3 14:18
 */
@RestController
@RequestMapping(Constant.API)
public class LoginController {

    @Resource
    private LoginService loginService;

    @Resource
    private UserService userService;

    @Resource
    private MenuService menuService;

    /**
     * 用户登录
     * <p>根据用户名、密码和验证码进行登录验证，成功后返回token</p>
     *
     * @param reqVO 登录请求参数，包含用户名、密码、验证码等信息
     * @return 登录成功后的token字符串
     * @author haigle
     * @date 2019/6/3 14:19
     */
    @PostMapping("/login")
    public ApiResult<String> login(@Validated @RequestBody LoginReqVO reqVO) {
        return ApiResult.ok(loginService.login(reqVO));
    }

    /**
     * 退出登录
     * <p>客户端清除token即可，服务端JWT无状态无需处理</p>
     *
     * @return 操作结果，成功返回true
     * @author haigle
     * @date 2024/12/10 14:00
     */
    @PostMapping("/logout")
    public ApiResult<Boolean> logout() {
        return ApiResult.ok();
    }

}
