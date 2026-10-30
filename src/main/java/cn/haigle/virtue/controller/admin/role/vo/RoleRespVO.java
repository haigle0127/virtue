package cn.haigle.virtue.controller.admin.role.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 管理后台 - 角色详情 Response VO
 *
 * @author haigle
 */
@Data
@Schema(description = "角色详情 Response VO")
public class RoleRespVO {

    @Schema(description = "编号ID")
    private Long id;

    @Schema(description = "角色名")
    private String name;

    @Schema(description = "权限标识")
    private String mark;

    @Schema(description = "角色标识（code 别名，同 mark）")
    private String code;

    @Schema(description = "角色说明")
    private String description;

    @Schema(description = "备注（remark 别名，同 description）")
    private String remark;

    @Schema(description = "角色状态 0正常 1停用", example = "0")
    private String state;

    @Schema(description = "状态（status，同 state）")
    private Integer status;

    @Schema(description = "排序")
    private String sort;

    @Schema(description = "创建时间")
    private String createTime;

}
