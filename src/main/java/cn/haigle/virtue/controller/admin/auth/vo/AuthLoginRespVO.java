package cn.haigle.virtue.controller.admin.auth.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 登录响应 VO
 *
 * @author haigle
 */
@Data
@Schema(description = "登录响应 VO")
public class AuthLoginRespVO {

    @Schema(description = "访问令牌")
    private String accessToken;

    @Schema(description = "刷新令牌")
    private String refreshToken;

    @Schema(description = "用户编号")
    private Long userId;

    @Schema(description = "访问令牌过期时间（毫秒时间戳）")
    private Long expiresTime;

}
