package cn.haigle.virtue.system.entity.query;

import cn.haigle.virtue.common.base.validator.Login;
import cn.haigle.virtue.common.base.validator.LoginByEmail;
import cn.haigle.virtue.common.base.validator.Save;
import cn.haigle.virtue.common.base.validator.Update;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * 登录参数
 * @author haigle
 * @date 2019/6/21 8:48
 */
@Data
public class LoginQuery {

    @NotBlank(message = "请输入账号", groups = {Update.class})
    private String account;

    @NotBlank(message = "请输入密码", groups = {Login.class, LoginByEmail.class, Save.class})
    @Min(value = 5, message = "密码必须大于等于六位", groups = {Save.class, LoginByEmail.class, Update.class})
    private String password;

    @NotBlank(message = "验证码不能为空", groups = {Save.class})
    private String captcha;

}
