package cn.haigle.virtue.controller.admin.login;

import cn.dev33.satoken.stp.StpUtil;
import cn.haigle.virtue.common.pojo.ApiResult;
import cn.haigle.virtue.common.web.Constant;
import cn.haigle.virtue.controller.admin.login.vo.LoginReqVO;
import cn.haigle.virtue.service.login.LoginService;
import cn.haigle.virtue.service.menu.MenuService;
import cn.haigle.virtue.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 登录注册
 * @author haigle
 * @date 2019/6/3 14:18
 */
@Tag(name = "登录注册管理")
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
     * 登录
     * @author haigle
     * @date 2019/6/3 14:19
     */
    @Operation(summary = "登录")
    @PostMapping("/login")
    public ApiResult<String> login(@Validated @RequestBody LoginReqVO reqVO) {
        return ApiResult.ok(loginService.login(reqVO));
    }

    /**
     * 退出
     * @author haigle
     * @date 2024/12/10 14:00
     */
    @Operation(summary = "退出")
    @PostMapping("/logout")
    public ApiResult<Boolean> login() {
        StpUtil.logout();
        return ApiResult.ok();
    }

}
