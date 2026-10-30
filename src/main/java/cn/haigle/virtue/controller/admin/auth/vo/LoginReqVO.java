package cn.haigle.virtue.controller.admin.auth.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 登录请求 VO
 *
 * @author haigle
 */
@Data
@Schema(description = "登录请求 VO")
public class LoginReqVO {

    @Schema(description = "账号，支持用户名 / 邮箱 / 手机号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "请输入账号")
    private String username;

    @Schema(description = "密码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "请输入密码")
    private String password;

}
