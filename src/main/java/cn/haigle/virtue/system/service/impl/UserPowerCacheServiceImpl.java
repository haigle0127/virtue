package cn.haigle.virtue.system.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.haigle.virtue.system.dao.LoginDao;
import cn.haigle.virtue.system.dao.cache.UserPowerCacheDao;
import cn.haigle.virtue.system.entity.bo.SysRoleBo;
import cn.haigle.virtue.system.entity.po.UserPowerDo;
import cn.haigle.virtue.system.entity.vo.Menu;
import cn.haigle.virtue.system.repository.RoleRepository;
import cn.haigle.virtue.system.service.UserPowerCacheService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * redis用户权限缓存实现
 * @author haigle
 * @date 2020/11/29 19:05
 */
@Service("userPowerCacheService")
public class UserPowerCacheServiceImpl implements UserPowerCacheService {

    private final UserPowerCacheDao userPowerCacheDao;

    private final LoginDao loginDao;

    private final RoleRepository roleRepository;

    public UserPowerCacheServiceImpl(UserPowerCacheDao userPowerCacheDao,
                                     LoginDao loginDao) {
        this.userPowerCacheDao = userPowerCacheDao;
        this.loginDao = loginDao;
    }

    @Override
    public void set(final Long uid, final List<Menu> powers, final List<String> powerKeyList) {
        userPowerCacheDao.save(new UserPowerDo(uid, powers, powerKeyList));
    }

    @Override
    public List<Menu> saveOrGet(final Long uid) {
        return Optional.ofNullable(userPowerCacheDao.findById(uid))
                .map(UserPowerDo::getPowerList)
                .orElseGet(() -> save(uid).getPowerList());
    }

    @Override
    public Optional<UserPowerDo> get(final Long uid) {
        return Optional.ofNullable(userPowerCacheDao.findById(uid));
    }

    @Override
    public void remove(final Long uid) {
        userPowerCacheDao.remove(uid);
    }

    @Override
    public void removeAll() {
        userPowerCacheDao.removeAll();
    }

    @Override
    public void removeAll(final Long... ids) {
        userPowerCacheDao.removeAll(ids);
    }

    @Override
    public List<String> getPowerList(final Object loginId, final String loginKey) {
        return userPowerCacheDao.getPowerKey(StpUtil.getLoginIdAsLong());
    }

    @Override
    public void refresh(final Long uid) {
        save(uid);
    }

    private UserPowerDo save(final Long uid) {
        Set<String> permissions = new HashSet<>();
        List<SysRoleBo> roleList = adminUserDao.findById(uid).map(AdminUserEntity::getRoles).orElse(new ArrayList<>());

        roleList.stream().map(RoleEntity::getPermissions).forEach(permissions::addAll);

        final List<Menu> menuList = permissionService.findByIds(permissions);
        final UserPowerDo userPowerDo = new UserPowerDo(
                uid,
                TreeUtils.build(menuList.stream().filter(item -> !item.getType().equals(MenuType.ACTION)).collect(Collectors.toList())),
                menuList.stream().filter(item -> item.getKey() != null && !item.getKey().isEmpty()).map(Menu::getKey).flatMap(Collection::stream).distinct().collect(Collectors.toList())
        );
        userPowerCacheDao.save(userPowerDo);
        return userPowerDo;
    }

    @Override
    public List<String> getRoleList(final Object loginId, final String loginKey) {
        return new ArrayList<>(0);
    }
}
