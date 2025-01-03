package cn.haigle.virtue.controller.admin.login.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 登录参数
 * @author haigle
 * @date 2019/6/21 8:48
 */
@Schema(description = "验证码 Response VO")
@Data
public class CaptchaRespVO {

    @NotBlank(message = "验证码标识")
    private String mark;

    @NotBlank(message = "验证码")
    private String img;

}
