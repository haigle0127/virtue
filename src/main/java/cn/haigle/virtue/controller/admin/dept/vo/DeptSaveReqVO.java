package cn.haigle.virtue.controller.admin.dept.vo;

import lombok.Data;

/**
 * 管理后台 - 部门保存 Request VO
 * @author haigle
 * @date 2024/11/16 12:53
 */
@Data
public class DeptSaveReqVO {

    /** 编号ID */
    private Long id;

    /** 父ID */
    private Long parentId;

    /** 祖级列表 */
    private String ancestors;

    /** 权限名 */
    private String name;

    /** 负责人 */
    private String leader;

    /** 联系电话 */
    private String phone;

    /** 邮箱 */
    private String email;

    /** 菜单状态 0正常 1停用 */
    private String state;

    /** 排序 */
    private Integer sort;

    /** 权限说明 */
    private String description;

}
