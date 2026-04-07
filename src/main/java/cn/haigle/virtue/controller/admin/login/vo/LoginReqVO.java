package cn.haigle.virtue.controller.admin.login.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 登录参数 Request VO
 * @author haigle
 * @date 2019/6/21 8:48
 */
@Data
public class LoginReqVO {

    @NotBlank(message = "请输入账号")
    private String account;

    @NotBlank(message = "请输入密码")
    private String password;

}
