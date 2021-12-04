package cn.haigle.virtue.system.entity.bo;

import cn.haigle.virtue.common.entity.EditableUserEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 角色数据
 * @author haigle
 * @date 2019-08-04 10:40
 */
@Table(name = "sys_role")
@Entity
@Getter
@Setter
public class SysRoleBo extends EditableUserEntity {

    /** 角色名称 */
    @Column
    private String name;

    /** 角色排序 */
    @Column
    private Integer sort;

    /** 角色权限字符 */
    @Column
    private Integer rolePower;

    /** 角色简介 */
    @Column
    private String description;

}
