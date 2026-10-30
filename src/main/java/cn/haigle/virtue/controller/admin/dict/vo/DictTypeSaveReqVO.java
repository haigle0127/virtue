package cn.haigle.virtue.controller.admin.dict.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 管理后台 - 字典类型保存 Request VO
 *
 * @author haigle
 */
@Data
@Schema(description = "字典类型保存 Request VO")
public class DictTypeSaveReqVO {

    @Schema(description = "编号ID")
    private Long id;

    @Schema(description = "字典名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "用户性别")
    @NotBlank(message = "字典名称不能为空")
    private String name;

    @Schema(description = "字典类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "system_user_sex")
    @NotBlank(message = "字典类型不能为空")
    private String type;

    @Schema(description = "状态（0正常 1停用）", example = "0")
    private String status;

    @Schema(description = "备注", example = "用户性别列表")
    private String remark;
}
