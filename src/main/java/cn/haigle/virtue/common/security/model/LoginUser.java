package cn.haigle.virtue.common.security.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

/**
 * 登录用户，作为 Spring Security 的 UserDetails
 *
 * @author haigle
 */
@Data
public class LoginUser implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 账号
     */
    private String username;

    /**
     * BCrypt 密码
     */
    private String password;

    /**
     * 登录类型 admin/app/pc
     */
    private String loginType;

    /**
     * 角色权限，格式 ROLE_{mark}
     */
    private Collection<? extends GrantedAuthority> authorities;

    /**
     * 账号是否可用
     */
    private boolean enabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}
