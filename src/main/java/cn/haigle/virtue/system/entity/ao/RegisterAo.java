package cn.haigle.virtue.system.entity.ao;

import cn.haigle.virtue.common.base.validator.LoginByEmail;
import cn.haigle.virtue.common.base.validator.Save;
import cn.haigle.virtue.common.base.validator.SendEmailCode;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * 注册接口实体
 * @author haigle
 * @date 2019/6/21 9:44
 */
@Data
public class RegisterAo {

    /** 姓名 */
    private String username;

    /** 邮箱 */
    @NotBlank(message = "邮箱不能为空", groups = {Save.class})
    @Email(message = "邮箱格式不正确", groups = {Save.class, SendEmailCode.class})
    private String email;

    /** 电话 */
    private String phone;

    @NotBlank(message = "密码不能为空", groups = {LoginByEmail.class, Save.class})
    @Length(min = 5, message = "密码必须大于等于六位", groups = {Save.class, LoginByEmail.class})
    private String password;

    /** 验证码 */
    @NotBlank(message = "验证码不能为空", groups = {Save.class})
    private String captcha;


}
