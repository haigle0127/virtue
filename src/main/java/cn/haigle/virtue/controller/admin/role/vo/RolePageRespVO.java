package cn.haigle.virtue.controller.admin.role.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色分页 Response VO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolePageRespVO {

    /** 编号ID */
    private Long id;

    /** 角色名 */
    private String name;

    /** 权限标识 */
    private String mark;

    /**
     * 数据范围 1全部数据权限 2自定数据权限 3本部门数据权限 4本部门及以下数据权限
     * {@link cn.haigle.virtue.common.constants.RoleDataScopeEnum}
     */
    private String dataScope;

    /** 菜单树选择项关联显示 0不关联 1关联 */
    private Boolean menuCheckStrictly;

    /** 部门树选择项关联显示 0不关联 1关联 */
    private Boolean deptCheckStrictly;

    /** 角色说明 */
    private String description;

    /**
     * 角色状态 0正常 1停用
     * {@link cn.haigle.virtue.common.constants.StateEnum}
     */
    private String state;

    /** 排序 */
    private String sort;

    /** 创建时间 */
    private String createTime;

}