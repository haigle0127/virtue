package cn.haigle.virtue.system.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.haigle.virtue.system.entity.ao.LoginAo;
import cn.haigle.virtue.system.entity.ao.RegisterAo;
import cn.haigle.virtue.system.entity.bo.PaiBo;
import cn.haigle.virtue.system.entity.vo.UserAndRolesVo;
import cn.haigle.virtue.system.entity.vo.LoginUserInfoVo;
import cn.haigle.virtue.system.entity.vo.Menu;
import cn.haigle.virtue.system.service.LoginService;
import cn.haigle.virtue.system.service.MenuService;
import cn.haigle.virtue.system.service.PaiService;
import cn.haigle.virtue.common.base.validator.LoginByEmail;
import cn.haigle.virtue.common.base.validator.Save;
import cn.haigle.virtue.common.interceptor.model.ApiResult;
import cn.haigle.virtue.config.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

import java.util.List;

import static cn.haigle.virtue.common.util.AccountValidatorUtils.REGEX_EMAIL;

/**
 * 登录注册
 * @author haigle
 * @date 2019/6/3 14:18
 */
@Api(tags = "登录注册")
@RestController
@RequestMapping(Constant.API)
public class LoginController {

    @Resource(name = "loginService")
    private LoginService loginService;

    @Resource(name = "paiService")
    private PaiService paiService;

    @Resource(name = "menuService")
    private MenuService menuService;

    /**
     * 登录
     * @author haigle
     * @date 2019/6/3 14:19
     */
    @ApiOperation("登录")
    @PostMapping("/login")
    public ApiResult<LoginUserInfoVo> login(@Validated(LoginByEmail.class) @RequestBody LoginAo ao) {
        return ApiResult.ok("登录成功", loginService.login(ao));
    }

    /**
     * 获取用户信息
     * @author haigle
     * @date 2019/6/3 17:28
     */
    @ApiOperation("用户信息")
    @GetMapping("/user/info")
    public ApiResult<UserAndRolesVo> info() {
        return ApiResult.ok(loginService.getUserAndRoles(StpUtil.getLoginIdAsLong()));
    }

    @ApiOperation(value = "权限标识")
    @GetMapping("/user/permission")
    public ApiResult<List<String>> permission() {
        return ApiResult.ok(loginService.getPermission(StpUtil.getLoginIdAsLong()));
    }

    @ApiOperation(value = "菜单")
    @GetMapping("/user/menu")
    public ApiResult<List<Menu>> menu() {
        return ApiResult.ok(menuService.menuTree(StpUtil.getLoginIdAsLong()));
    }

    /**
     * 用户注册
     * @author haigle
     * @date 2019/3/6 13:53
     */
    @ApiOperation("用户注册")
    @PostMapping("/register")
    public ApiResult<String> register(@Validated(Save.class) RegisterAo registerAo) {

        //检验邮箱是否存在
        if (loginService.emailIsExist(registerAo.getEmail())) {
            return ApiResult.fail("邮箱已注册");
        }
        PaiBo paiPo = paiService.getPaiPo(registerAo.getEmail());
        if(paiPo == null) {
            return ApiResult.fail("未获取验证码");
        }
        if(paiPo.getLabel().equals(registerAo.getCaptcha())) {
            paiService.delete(registerAo.getEmail());
            loginService.save(registerAo);
            return ApiResult.ok("注册成功");
        }
        return ApiResult.fail("验证码不正确");
    }

    /**
     * 获取邮箱验证码
     * @author haigle
     * @date 2019/3/6 13:53
     */
    @ApiOperation("邮箱验证码")
    @PostMapping("/sendEmailCode")
    public ApiResult<String> sendEmailCode(@NotNull(message = "邮箱格式不正确") @RequestParam("email") String email) {

        if(!email.matches(REGEX_EMAIL)) {
            return ApiResult.fail("邮箱格式不正确");
        }

        //检验邮箱是否存在
        if (loginService.emailIsExist(email)) {
            return ApiResult.fail("邮箱已注册");
        }
        loginService.sendEmailCode(email);
        return ApiResult.ok();
    }

    @ApiOperation("退出登录")
    @PostMapping("/logout")
    public ApiResult<String> logout() {
        StpUtil.logout();
        return ApiResult.ok();
    }

}
