package cn.haigle.virtue.controller.admin.login.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 验证码 Response VO
 * @author haigle
 * @date 2019/6/21 8:48
 */
@Data
@Schema(description = "验证码 Response VO")
public class CaptchaRespVO {

    @Schema(description = "验证码标识", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "验证码标识")
    private String mark;

    @Schema(description = "验证码图片Base64", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "验证码")
    private String img;

}
