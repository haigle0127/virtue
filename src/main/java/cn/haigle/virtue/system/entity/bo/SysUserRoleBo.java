package cn.haigle.virtue.system.entity.bo;

import cn.haigle.virtue.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * 用户信息
 * @author haigle
 * @date 2021/12/3 22:51
 */
@Table(name = "sys_user_role")
@Entity
@Getter
@Setter
public class SysUserRoleBo extends BaseEntity {

    /** 用户ID */
    @Column
    private Long userId;

    /** 角色ID */
    @Column
    private Long roleId;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @OrderBy("id desc")
    @JoinTable(name = "sys_user_role",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private List<SysRoleBo> roles;


}
