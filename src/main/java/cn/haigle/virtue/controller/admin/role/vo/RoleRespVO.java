package cn.haigle.virtue.controller.admin.role.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "管理后台 - 角色详情 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleRespVO {

    @Schema(description = "编号ID")
    private Long id;

    @Schema(description = "角色名")
    private String name;

    @Schema(description = "权限标识")
    private String mark;

    /**
     * 数据范围 1全部数据权限 2自定数据权限 3本部门数据权限 4本部门及以下数据权限
     * {@link cn.haigle.virtue.common.constants.RoleDataScopeEnum}
     */
    @Schema(description = "数据范围 1全部数据权限 2自定数据权限 3本部门数据权限 4本部门及以下数据权限")
    private String dataScope;

    @Schema(description = "菜单树选择项关联显示 0不关联 1关联")
    private Boolean menuCheckStrictly;

    @Schema(description = "部门树选择项关联显示 0不关联 1关联")
    private Boolean deptCheckStrictly;

    @Schema(description = "角色说明")
    private String description;

    /**
     * 角色状态 0正常 1停用
     * {@link cn.haigle.virtue.common.constants.StateEnum}
     */
    @Schema(description = "角色状态 0正常 1停用")
    private String state;

    @Schema(description = "排序")
    private String sort;

    @Schema(description = "创建时间")
    private String createTime;

}