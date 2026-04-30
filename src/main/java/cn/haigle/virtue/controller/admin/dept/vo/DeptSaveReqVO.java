package cn.haigle.virtue.controller.admin.dept.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 管理后台 - 部门保存 Request VO
 * @author haigle
 * @date 2024/11/16 12:53
 */
@Data
@Schema(description = "部门保存 Request VO")
public class DeptSaveReqVO {

    @Schema(description = "编号ID")
    private Long id;

    @Schema(description = "父ID")
    private Long parentId;

    @Schema(description = "祖级列表")
    private String ancestors;

    @Schema(description = "权限名")
    private String name;

    @Schema(description = "负责人")
    private String leader;

    @Schema(description = "联系电话")
    private String phone;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "菜单状态 0正常 1停用", example = "0")
    private String state;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "权限说明")
    private String description;

}
