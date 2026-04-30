package cn.haigle.virtue.controller.admin.login.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 登录参数 Request VO
 * @author haigle
 * @date 2019/6/21 8:48
 */
@Data
@Schema(description = "登录参数 Request VO")
public class LoginReqVO {

    @Schema(description = "账号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "请输入账号")
    private String account;

    @Schema(description = "密码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "请输入密码")
    private String password;

}
