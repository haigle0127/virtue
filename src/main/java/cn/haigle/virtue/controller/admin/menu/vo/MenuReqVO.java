package cn.haigle.virtue.controller.admin.menu.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 管理后台 - 菜单列表查询 Request VO
 * @author haigle
 * @date 2024/11/13 下午1:34
 */
@Data
@Schema(description = "菜单列表查询 Request VO")
public class MenuReqVO {

    @Schema(description = "菜单名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "菜单名称")
    private String name;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "状态")
    private Integer state;

}
