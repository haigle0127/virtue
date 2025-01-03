package cn.haigle.virtue.mapper.dataobject.role;

import cn.haigle.virtue.common.base.BaseDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色数据
 * @author haigle
 * @date 2019-08-04 10:40
 */
@TableName("sys_role")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRoleDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 权限标识
     */
    private String mark;

    /**
     * 数据范围 1全部数据权限 2自定数据权限 3本部门数据权限 4本部门及以下数据权限
     * {@link cn.haigle.virtue.common.constants.RoleDataScopeEnum}
     */
    private String dataScope;

    /**
     * 菜单树选择项关联显示 0不关联 1关联
     */
    private Boolean menuCheckStrictly;

    /**
     * 部门树选择项关联显示 0不关联 1关联
     */
    private Boolean deptCheckStrictly;

    /**
     * 角色说明
     */
    private String description;

    /**
     * 角色状态 0正常 1停用
     * {@link cn.haigle.virtue.common.constants.StateEnum}
     */
    private String state;

    /**
     * 排序
     */
    private String sort;

}
