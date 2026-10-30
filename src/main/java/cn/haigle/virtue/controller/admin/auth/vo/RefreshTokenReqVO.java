package cn.haigle.virtue.controller.admin.auth.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 刷新令牌请求 VO
 *
 * @author haigle
 */
@Data
@Schema(description = "刷新令牌请求 VO")
public class RefreshTokenReqVO {

    @NotBlank(message = "刷新令牌不能为空")
    @Schema(description = "刷新令牌", requiredMode = Schema.RequiredMode.REQUIRED)
    private String refreshToken;

}
