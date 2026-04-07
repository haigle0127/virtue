package cn.haigle.virtue.controller.admin.login.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 验证码 Response VO
 * @author haigle
 * @date 2019/6/21 8:48
 */
@Data
public class CaptchaRespVO {

    @NotBlank(message = "验证码标识")
    private String mark;

    @NotBlank(message = "验证码")
    private String img;

}
