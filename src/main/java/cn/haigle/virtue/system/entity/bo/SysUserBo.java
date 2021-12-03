package cn.haigle.virtue.system.entity.bo;

import cn.haigle.virtue.common.entity.BaseEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户信息
 * @author haigle
 * @date 2021/12/3 22:51
 */
@Table(name = "sys_user")
@Entity
@Getter
@Setter
public class SysUserBo extends BaseEntity {

    /** 用户名称 */
    @Column
    private String username;

    /** 邮箱 */
    @Column
    private String email;

    /** 电话 */
    @Column
    private String phone;

    /** 密码 */
    @Column
    @Column(updatable = false)
    private String password;

    /** 盐 */
    @Column
    @Column(updatable = false)
    private String salt;

}
