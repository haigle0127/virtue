package cn.haigle.virtue.service.auth;

import cn.haigle.virtue.common.constants.StateEnum;
import cn.haigle.virtue.common.security.model.LoginUser;
import cn.haigle.virtue.mapper.dataobject.user.SysUserDO;
import cn.haigle.virtue.mapper.mysql.role.RoleMapper;
import cn.haigle.virtue.mapper.mysql.user.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 登录用户加载，账号支持用户名 / 邮箱 / 手机号
 *
 * @author haigle
 */
@Service
public class LoginUserDetailsService implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Override
    public LoginUser loadUserByUsername(String account) throws UsernameNotFoundException {
        SysUserDO user = userMapper.selectByAccount(account);
        if (user == null) {
            throw new UsernameNotFoundException("账号或密码错误");
        }
        return buildLoginUser(user);
    }

    /**
     * 刷新令牌时按用户ID重新加载，角色变更可以立即生效
     */
    public LoginUser loadUserById(Long userId) {
        SysUserDO user = userMapper.selectById(userId);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return buildLoginUser(user);
    }

    private LoginUser buildLoginUser(SysUserDO user) {
        List<GrantedAuthority> authorities = roleMapper.selectRoleMarkByUserId(user.getId()).stream()
                .map(mark -> new SimpleGrantedAuthority("ROLE_" + mark))
                .map(GrantedAuthority.class::cast)
                .toList();

        LoginUser loginUser = new LoginUser();
        loginUser.setId(user.getId());
        loginUser.setUsername(user.getUsername());
        loginUser.setPassword(user.getPassword());
        loginUser.setAuthorities(authorities);
        loginUser.setEnabled(StateEnum.ZERO.value().equals(user.getState()));
        return loginUser;
    }

}
