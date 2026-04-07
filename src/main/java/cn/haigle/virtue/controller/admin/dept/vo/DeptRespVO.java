package cn.haigle.virtue.controller.admin.dept.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理后台 - 部门 Response VO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeptRespVO {

    /** 编号ID */
    private Long id;

    /** 父ID */
    private Long parentId;

    /** 祖级列表 */
    private String ancestors;

    /** 部门名称 */
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

    /** 创建时间 */
    private String createTime;

    /** 子菜单 */
    private List<DeptRespVO> children = new ArrayList<>();

}
