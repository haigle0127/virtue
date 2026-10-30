package cn.haigle.virtue.controller.admin.dict.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 管理后台 - 字典数据保存 Request VO
 *
 * @author haigle
 */
@Data
@Schema(description = "字典数据保存 Request VO")
public class DictDataSaveReqVO {

    @Schema(description = "编号ID")
    private Long id;

    @Schema(description = "排序", example = "1")
    private Integer sort;

    @Schema(description = "字典标签", requiredMode = Schema.RequiredMode.REQUIRED, example = "男")
    @NotBlank(message = "字典标签不能为空")
    private String label;

    @Schema(description = "字典值", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    @NotBlank(message = "字典值不能为空")
    private String value;

    @Schema(description = "字典类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "system_user_sex")
    @NotBlank(message = "字典类型不能为空")
    private String dictType;

    @Schema(description = "状态（0正常 1停用）", example = "0")
    private String status;

    @Schema(description = "颜色类型", example = "primary")
    private String colorType;

    @Schema(description = "样式类名")
    private String cssClass;

    @Schema(description = "备注")
    private String remark;
}
