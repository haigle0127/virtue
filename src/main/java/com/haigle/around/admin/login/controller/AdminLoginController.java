package com.haigle.around.admin.login.controller;

import com.haigle.around.admin.login.entity.bo.AdminPaiBO;
import com.haigle.around.admin.login.entity.query.AdminLoginQuery;
import com.haigle.around.admin.login.entity.ao.AdminRegisterAO;
import com.haigle.around.admin.login.entity.bo.AdminUserLoginBO;
import com.haigle.around.admin.login.service.AdminLoginService;
import com.haigle.around.admin.login.service.AdminPaiService;
import com.haigle.around.common.base.validator.LoginByEmail;
import com.haigle.around.common.base.validator.Save;
import com.haigle.around.common.interceptor.model.ApiResultDataI18n;
import com.haigle.around.common.interceptor.model.ApiResultI18n;
import com.haigle.around.admin.user.service.AdminUserService;
import com.haigle.around.common.interceptor.model.BaseI18n;
import com.haigle.around.common.interceptor.permission.authentication.PowerPermission;
import com.haigle.around.common.util.DesUtils;
import com.haigle.around.common.util.JwtUtils;
import com.haigle.around.config.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

import static com.haigle.around.common.util.AccountValidatorUtils.REGEX_EMAIL;

/**
 * 登录注册
 * @author haigle
 * @date 2019/6/3 14:18
 */
@Api(tags = "登录注册")
@RestController
@RequestMapping(Constant.API)
public class AdminLoginController extends BaseI18n {

    @Resource(name = "adminLoginService")
    private AdminLoginService adminLoginService;

    @Resource(name = "adminUserService")
    private AdminUserService adminUserService;

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
    public ApiResultI18n login(@Validated(LoginByEmail.class) @RequestBody AdminLoginQuery adminLoginQuery) {

        AdminUserLoginBO adminUserLoginBO = adminLoginService.login(adminLoginQuery);
        if(adminUserLoginBO != null) {
            String inputPassword = adminLoginQuery.getPassword();
            String userPassword = DesUtils.decrypt(adminUserLoginBO.getPassword(), adminUserLoginBO.getSalt());
            if(inputPassword.equals(userPassword)) {
                return new ApiResultDataI18n<>(true, JwtUtils.sign(adminUserLoginBO.getId().toString()));
            }
            return apiResultI18n.setMessage(10120,"password.error", false);
        }
        return apiResultI18n.setMessage(10121, "account.error", false);

    }

    /**
     * 获取用户信息
     * @param token 登录凭证
     * @author haigle
     * @date 2019/6/3 17:28
     */
    @ApiOperation("用户信息")
    @GetMapping("/user/info")
    public ApiResultI18n info(@NotNull(message = "exception.not_token") @RequestHeader(Constant.TOKEN) String token) {
        return new ApiResultDataI18n<>(true, adminUserService.info(JwtUtils.getSubject(token)));
    }

    /**
     * 用户注册
     * @author haigle
     * @date 2019/3/6 13:53
     */
    @ApiOperation("用户注册")
    @PostMapping("/register")
    public ApiResultI18n register(@Validated(Save.class) AdminRegisterAO adminRegisterAO) {

        //检验邮箱是否存在
        if (adminLoginService.emailIsExist(adminRegisterAO.getEmail())) {
            return apiResultI18n.setMessage("email.is_exist", false);
        }
        AdminPaiBO adminPaiPo = adminPaiService.getPaiPo(adminRegisterAO.getEmail());
        if(adminPaiPo == null) {
            return apiResultI18n.setMessage("captcha.not_sent", false);

        }
        if(adminPaiPo.getLabel().equals(adminRegisterAO.getCaptcha())) {
            adminPaiService.delete(adminRegisterAO.getEmail());
            return new ApiResultDataI18n<>(true, adminLoginService.save(adminRegisterAO));
        }
        return apiResultI18n.setMessage("captcha.error", false);
    }

    /**
     * 获取邮箱验证码
     * @author haigle
     * @date 2019/3/6 13:53
     */
    @ApiOperation("邮箱验证码")
    @PostMapping("/sendEmailCode")
    public ApiResultI18n sendEmailCode(@NotNull(message = "email.not_blank") @RequestParam("email") String email) {

        if(!email.matches(REGEX_EMAIL)) {
            return apiResultI18n.setMessage("email.format.error", false);
        }

        //检验邮箱是否存在
        if (adminLoginService.emailIsExist(email)) {
            return apiResultI18n.setMessage("email.is_exist", false);
        }
        adminLoginService.sendEmailCode(email);
        return apiResultI18n.setMessage("captcha.success.sent", false);
    }

    @ApiOperation("退出登录")
    @PostMapping("/logout")
    public ApiResultI18n logout(@RequestHeader(Constant.TOKEN) String token) {
        powerPermission.removePermission((JwtUtils.getSubject(token)));
        return apiResultI18n.setMessage("logout.success", true);
    }

}
