package cn.haigle.virtue.admin.login.controller;

import cn.haigle.virtue.admin.login.entity.ao.AdminLoginAO;
import cn.haigle.virtue.admin.login.entity.ao.AdminRegisterAO;
import cn.haigle.virtue.admin.login.entity.bo.AdminPaiBO;
import cn.haigle.virtue.admin.login.service.AdminLoginService;
import cn.haigle.virtue.admin.login.service.AdminPaiService;
import cn.haigle.virtue.common.base.validator.LoginByEmail;
import cn.haigle.virtue.common.base.validator.Save;
import cn.haigle.virtue.common.interceptor.model.ApiResult;
import cn.haigle.virtue.common.interceptor.permission.authentication.PowerPermission;
import cn.haigle.virtue.common.util.JwtUtils;
import cn.haigle.virtue.config.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

import static cn.haigle.virtue.common.util.AccountValidatorUtils.REGEX_EMAIL;

/**
 * 登录注册
 * @author haigle
 * @date 2019/6/3 14:18
 */
@Api(tags = "登录注册")
@RestController
@RequestMapping(Constant.API)
public class AdminLoginController {

    @Resource(name = "adminLoginService")
    private AdminLoginService adminLoginService;

    @Resource(name = "adminPaiService")
    private AdminPaiService adminPaiService;

    @Resource(name = "powerPermission")
    private PowerPermission powerPermission;

    /**
     * 登录
     * @author haigle
     * @date 2019/6/3 14:19
     */
    @ApiOperation("登录")
    @PostMapping("/login")
    public ApiResult login(@Validated(LoginByEmail.class) @RequestBody AdminLoginAO ao) {
        return ApiResult.ok("登录成功", adminLoginService.login(ao));
    }

    /**
     * 获取用户信息
     * @param token 登录凭证
     * @author haigle
     * @date 2019/6/3 17:28
     */
    @ApiOperation("用户信息")
    @GetMapping("/user/info")
    public ApiResult info(@RequestHeader(Constant.TOKEN) String token) {
        return ApiResult.ok(adminLoginService.getAdminUserAndRoles(JwtUtils.getSubject(token)));
    }

    /**
     * 用户注册
     * @author haigle
     * @date 2019/3/6 13:53
     */
    @ApiOperation("用户注册")
    @PostMapping("/register")
    public ApiResult register(@Validated(Save.class) AdminRegisterAO adminRegisterAO) {

        //检验邮箱是否存在
        if (adminLoginService.emailIsExist(adminRegisterAO.getEmail())) {
            return ApiResult.fail("邮箱已注册");
        }
        AdminPaiBO adminPaiPo = adminPaiService.getPaiPo(adminRegisterAO.getEmail());
        if(adminPaiPo == null) {
            return ApiResult.fail("未获取验证码");
        }
        if(adminPaiPo.getLabel().equals(adminRegisterAO.getCaptcha())) {
            adminPaiService.delete(adminRegisterAO.getEmail());
            return ApiResult.ok(adminLoginService.save(adminRegisterAO));
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
    public ApiResult sendEmailCode(@NotNull(message = "邮箱格式不正确") @RequestParam("email") String email) {

        if(!email.matches(REGEX_EMAIL)) {
            return ApiResult.fail("邮箱格式不正确");
        }

        //检验邮箱是否存在
        if (adminLoginService.emailIsExist(email)) {
            return ApiResult.fail("邮箱已注册");
        }
        adminLoginService.sendEmailCode(email);
        return ApiResult.ok();
    }

    @ApiOperation("退出登录")
    @PostMapping("/logout")
    public ApiResult logout(@RequestHeader(Constant.TOKEN) String token) {
        powerPermission.removePermission((JwtUtils.getSubject(token)));
        return ApiResult.ok();
    }

}
