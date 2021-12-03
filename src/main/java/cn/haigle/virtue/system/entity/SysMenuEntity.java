package cn.haigle.virtue.system.entity;

import cn.haigle.virtue.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 菜单
 * @author haigle
 * @date 2021/11/14 21:18
 */
@Table(name = "sys_menu")
@Entity
@Getter
@Setter
public class SysMenuEntity extends BaseEntity {

    /** 父级ID */
    @Column
    private Long parentId;

    /** 菜单名称 */
    @Column
    private String name;

    /** 图标 */
    @Column
    private String icon;

    /** 路由地址 */
    @Column
    private String path;

    /** 重定向 */
    @Column
    private String redirect;

    /** 组件路径 */
    @Column
    private String component;

    /** 菜单类型（MENU目录 BUTTON按钮） */
    @Column
    private String menuType;

    /** 权限标识 */
    @Column
    private String power;

}
