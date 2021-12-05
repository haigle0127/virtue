package cn.haigle.virtue.system.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.haigle.virtue.common.util.tree.TreeUtils;
import cn.haigle.virtue.system.dao.LoginDao;
import cn.haigle.virtue.system.dao.cache.UserPowerCacheDao;
import cn.haigle.virtue.system.entity.bo.SysMenuBo;
import cn.haigle.virtue.system.entity.bo.SysRoleBo;
import cn.haigle.virtue.system.entity.po.UserPowerDo;
import cn.haigle.virtue.system.entity.vo.Menu;
import cn.haigle.virtue.system.entity.vo.MenuType;
import cn.haigle.virtue.system.entity.vo.Meta;
import cn.haigle.virtue.system.repository.MenuRepository;
import cn.haigle.virtue.system.repository.RoleRepository;
import cn.haigle.virtue.system.service.UserPowerCacheService;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.query.JpaQueryMethod;
import org.springframework.data.jpa.repository.query.JpaQueryMethodFactory;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.Method;
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

    @Resource(name = "menuRepository")
    private MenuRepository menuRepository;


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
        List<SysMenuBo> menuBoList = menuRepository.selectByUserId(uid);
        List<Menu> menuList = TreeUtils.build(menuBoList.stream()
                .filter(item -> MenuType.MENU.name().equals(item.getMenuType()))
                .map(item -> new Menu()
                        .setId(item.getId())
                        .setName(item.getName())
                        .setIcon(item.getIcon())
                        .setMeta(new Meta().setTitle(item.getName()).setIcon(item.getIcon()))
                        .setParentId(item.getParentId())
                        .setPath(item.getPath())
                        .setRedirect(item.getRedirect())
                        .setComponent(item.getComponent())
                        .setMenuType(item.getMenuType())
                        .setPower(item.getPower()))
                .collect(Collectors.toList()));
        List<String> permissions = menuBoList.stream()
                .filter(item -> item.getPower() != null && !item.getPower().isEmpty())
                .map(SysMenuBo::getPower)
                .distinct()
                .collect(Collectors.toList());
        UserPowerDo userPowerDo = new UserPowerDo(uid, menuList, permissions);
        userPowerCacheDao.save(userPowerDo);
        return userPowerDo;
    }

    @Override
    public List<String> getRoleList(final Object loginId, final String loginKey) {
        return new ArrayList<>(0);
    }
}
