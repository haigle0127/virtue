package cn.haigle.virtue.controller.admin.dept.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 管理后台 - 部门列表查询 Request VO
 * @author haigle
 * @date 2024/11/16 12:49
 */
@Data
@Schema(description = "部门列表查询 Request VO")
public class DeptReqVO {

    @Schema(description = "部门名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "部门名称")
    private String name;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "状态")
    private Integer state;

}
