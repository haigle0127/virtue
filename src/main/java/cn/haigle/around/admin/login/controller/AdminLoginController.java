package cn.haigle.around.admin.login.controller;

import cn.haigle.around.admin.login.entity.ao.AdminLoginAO;
import cn.haigle.around.admin.login.entity.ao.AdminRegisterAO;
import cn.haigle.around.admin.login.entity.bo.AdminPaiBO;
import cn.haigle.around.admin.login.service.AdminLoginService;
import cn.haigle.around.admin.login.service.AdminPaiService;
import cn.haigle.around.common.base.validator.LoginByEmail;
import cn.haigle.around.common.base.validator.Save;
import cn.haigle.around.common.interceptor.model.ApiResult;
import cn.haigle.around.common.interceptor.model.message.CodeStatus;
import cn.haigle.around.common.interceptor.permission.authentication.PowerPermission;
import cn.haigle.around.common.util.JwtUtils;
import cn.haigle.around.config.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

import static cn.haigle.around.common.util.AccountValidatorUtils.REGEX_EMAIL;

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
        return new ApiResult<>(adminLoginService.login(ao), CodeStatus.OK);
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
        return new ApiResult<>(adminLoginService.getAdminUserAndRoles(JwtUtils.getSubject(token)), CodeStatus.OK);
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
            return new ApiResult<>(CodeStatus.EMAIL_EXIST);
        }
        AdminPaiBO adminPaiPo = adminPaiService.getPaiPo(adminRegisterAO.getEmail());
        if(adminPaiPo == null) {
            return new ApiResult<>(CodeStatus.CAPTCHA_NOT_SENT);
        }
        if(adminPaiPo.getLabel().equals(adminRegisterAO.getCaptcha())) {
            adminPaiService.delete(adminRegisterAO.getEmail());
            return new ApiResult<>(adminLoginService.save(adminRegisterAO), CodeStatus.OK);
        }
        return new ApiResult<>(CodeStatus.CAPTCHA_ERROR);
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
            return new ApiResult<>(CodeStatus.EMAIL_FORMAT_ERROR);
        }

        //检验邮箱是否存在
        if (adminLoginService.emailIsExist(email)) {
            return new ApiResult<>(CodeStatus.EMAIL_EXIST);
        }
        adminLoginService.sendEmailCode(email);
        return new ApiResult<>(CodeStatus.OK);
    }

    @ApiOperation("退出登录")
    @PostMapping("/logout")
    public ApiResult logout(@RequestHeader(Constant.TOKEN) String token) {
        powerPermission.removePermission((JwtUtils.getSubject(token)));
        return new ApiResult<>(CodeStatus.OK);
    }

}
