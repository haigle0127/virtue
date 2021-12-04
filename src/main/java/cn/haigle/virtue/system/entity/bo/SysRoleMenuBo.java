package cn.haigle.virtue.system.entity.bo;

import cn.haigle.virtue.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * 角色菜单关联数据
 * @author haigle
 * @date 2019-08-04 10:40
 */
@Table(name = "sys_role_menu")
@Entity
@Getter
@Setter
public class SysRoleMenuBo extends BaseEntity {

    /** 角色ID */
    @Column
    private Long roleId;

    /** 菜单ID */
    @Column
    private Long menuId;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @OrderBy("id desc")
    @JoinTable(name = "sys_role_menu",
            joinColumns = {@JoinColumn(name = "menu_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<SysRoleMenuBo> menus;


}
