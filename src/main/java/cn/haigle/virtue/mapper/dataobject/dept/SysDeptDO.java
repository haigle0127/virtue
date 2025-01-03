package cn.haigle.virtue.mapper.dataobject.dept;

import cn.haigle.virtue.common.base.BaseDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 部门
 * @author haigle
 * @date 2024/11/16 11:29
 */
@TableName("sys_dept")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysDeptDO extends BaseDO {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 父ID
     */
    private Long parentId;
    /**
     * 祖级列表
     */
    private String ancestors;
    /**
     * 部门名称
     */
    private String name;
    /**
     * 负责人
     */
    private String leader;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 菜单状态 0正常 1停用
     * {@link cn.haigle.virtue.common.constants.StateEnum}
     */
    private String state;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 说明
     */
    private String description;

}
