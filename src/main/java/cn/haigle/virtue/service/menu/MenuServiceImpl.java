package cn.haigle.virtue.service.menu;
import cn.haigle.virtue.common.constants.StateEnum;
import cn.haigle.virtue.controller.admin.menu.vo.*;
import cn.haigle.virtue.mapper.dataobject.menu.SysMenuDO;
import cn.haigle.virtue.mapper.dataobject.role.SysRoleDO;
import cn.haigle.virtue.mapper.mysql.menu.MenuMapper;
import cn.haigle.virtue.mapper.mysql.role.RoleMapper;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 菜单接口实现
 * @author haigle
 * @date 2019/8/23 10:13
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<MenuRespVO> list(MenuReqVO reqVO) {
        List<SysMenuDO> menuDOList = menuMapper.selectList(reqVO);
        return BeanUtil.copyToList(menuDOList, MenuRespVO.class);
    }

    @Override
    public MenuRespVO detail(Long menuId) {
        SysMenuDO menuDO = menuMapper.selectById(menuId);
        return BeanUtil.toBean(menuDO, MenuRespVO.class);
    }

    @Override
    public void add(MenuSaveReqVO reqVO) {
        SysMenuDO sysMenuDO = BeanUtil.toBean(reqVO, SysMenuDO.class);
        sysMenuDO.setCreateTime(LocalDateTime.now());
        sysMenuDO.setUpdateTime(LocalDateTime.now());
        menuMapper.insert(sysMenuDO);
    }

    @Override
    public void update(MenuSaveReqVO reqVO) {
        SysMenuDO sysMenuDO = BeanUtil.toBean(reqVO, SysMenuDO.class);
        sysMenuDO.setUpdateTime(LocalDateTime.now());
        menuMapper.updateById(sysMenuDO);
    }

    @Override
    public void delete(Long menuId) {
        menuMapper.deleteById(menuId);
    }

    /**
     * 根据用户ID查询菜单
     *
     * @param userId 用户名称
     * @return 菜单列表
     */
    @Override
    public List<MenuRespVO> selectTreeByUserId(Long userId) {
        List<MenuRespVO> menus = null;
        //TODO 这块要改造
//        if(SecurityUtils.isAdmin(userId)) {
        if(true) {
            menus = menuMapper.selectTreeAll();
        }else {
            menus = menuMapper.selectTreeByUserId(userId);
        }
        return getChildPower(menus, 0);
    }

    /**
     * 构建前端路由所需要的菜单
     *
     * @param menus 菜单列表
     * @return 路由列表
     */
    @Override
    public List<RouterVO> buildMenus(List<MenuRespVO> menus) {
        List<RouterVO> routers = new LinkedList<>();
        for(MenuRespVO menu : menus) {
            RouterVO router = new RouterVO();
            router.setHidden("1".equals(menu.getVisible()));
            router.setName(getRouteName(menu));
            router.setPath(getRouterPath(menu));
            router.setComponent(getComponent(menu));
            router.setParam(menu.getParam());
            router.setMeta(new MetaVO(menu.getName(), menu.getIcon(), "0".equals(menu.getCache()), menu.getPath()));
            List<MenuRespVO> cMenus = menu.getChildren();
            if (CollUtil.isNotEmpty(cMenus) && "CATALOG".equals(menu.getMenuType())) {
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");
                router.setChildren(buildMenus(cMenus));
            } else if (isMenuFrame(menu)) {
                router.setMeta(null);
                List<RouterVO> childrenList = new ArrayList<>();
                RouterVO children = new RouterVO();
                children.setPath(menu.getPath());
                children.setComponent(menu.getComponent());
                children.setName(getRouteName(menu.getRouteName(), menu.getPath()));
                children.setMeta(new MetaVO(menu.getName(), menu.getIcon(), "0".equals(menu.getCache()), menu.getPath()));
                children.setParam(menu.getParam());
                childrenList.add(children);
                router.setChildren(childrenList);
            } else if (menu.getParentId() == 0L && isInnerLink(menu)) {
                router.setMeta(new MetaVO(menu.getName(), menu.getIcon()));
                router.setPath("/");
                List<RouterVO> childrenList = new ArrayList<>();
                RouterVO children = new RouterVO();
                String routerPath = innerLinkReplaceEach(menu.getPath());
                children.setPath(routerPath);
                children.setComponent("InnerLink");
                children.setName(getRouteName(menu.getRouteName(), routerPath));
                children.setMeta(new MetaVO(menu.getName(), menu.getIcon(), menu.getPath()));
                childrenList.add(children);
                router.setChildren(childrenList);
            }
            routers.add(router);
        }
        return routers;
    }

    @Override
    public List<MenuTreeDTO> userMenuTree(Long userId) {
        //        List<MenuRespVO> menuList = menuMapper.selectMenuListByUserId(userId);
        List<MenuRespVO> menuList = menuMapper.selectMenuList();
        List<MenuRespVO> menuTrees = buildMenuTree(menuList);
        return menuTrees.stream().map(MenuTreeDTO::new).collect(Collectors.toList());
    }

    @Override
    public RoleMenuTreeRespVO roleMenuTree(Long roleId) {
        RoleMenuTreeRespVO respVO = new RoleMenuTreeRespVO();
        SysRoleDO sysRoleDO = roleMapper.selectById(roleId);
        List<Long> checkedKeys = menuMapper.selectMenuIdByRoleId(roleId, sysRoleDO.getMenuCheckStrictly());
        respVO.setCheckedKeys(checkedKeys);
//        List<MenuRespVO> menuList = menuMapper.selectMenuListByUserId(roleId);
        List<MenuRespVO> menuList = menuMapper.selectMenuList();
        List<MenuRespVO> menuTrees = buildMenuTree(menuList);
        List<MenuTreeDTO> menus = menuTrees.stream().map(MenuTreeDTO::new).collect(Collectors.toList());
        respVO.setMenus(menus);
        return respVO;
    }

    /**
     * 构建前端所需要树结构
     *
     * @param menus 菜单列表
     * @return 树结构列表
     */
    private List<MenuRespVO> buildMenuTree(List<MenuRespVO> menus) {
        List<MenuRespVO> returnList = new ArrayList<>();
        List<Long> tempList = menus.stream().map(MenuRespVO::getId).toList();
        for(MenuRespVO menu : menus) {
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if(!tempList.contains(menu.getParentId())) {
                recursionFn(menus, menu);
                returnList.add(menu);
            }
        }
        if(returnList.isEmpty()) {
            returnList = menus;
        }
        return returnList;
    }

    public List<MenuRespVO> getChildPower(List<MenuRespVO> list, int parentId) {
        List<MenuRespVO> returnList = new ArrayList<>();
        for(MenuRespVO t : list) {
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if(t.getParentId() == parentId) {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list 分类表
     * @param t 子节点
     */
    private void recursionFn(List<MenuRespVO> list, MenuRespVO t) {
        // 得到子节点列表
        List<MenuRespVO> childList = getChildList(list, t);
        t.setChildren(childList);
        for (MenuRespVO tChild : childList) {
            if (hasChild(list, tChild)) {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<MenuRespVO> getChildList(List<MenuRespVO> list, MenuRespVO t) {
        List<MenuRespVO> tlist = new ArrayList<>();
        for(MenuRespVO n : list) {
            if(n.getParentId().longValue() == t.getId().longValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<MenuRespVO> list, MenuRespVO t) {
        return !getChildList(list, t).isEmpty();
    }

    /**
     * 获取路由名称
     *
     * @param menu 菜单信息
     * @return 路由名称
     */
    public String getRouteName(MenuRespVO menu) {
        // 非外链并且是一级目录（类型为目录）
        if(isMenuFrame(menu)) {
            return "";
        }
        return getRouteName(menu.getRouteName(), menu.getPath());
    }

    /**
     * 获取路由名称，如没有配置路由名称则取路由地址
     *
     * @param name 路由名称
     * @param path 路由地址
     * @return 路由名称（驼峰格式）
     */
    public String getRouteName(String name, String path) {
        return StrUtil.isNotEmpty(name) ? name : path;
    }

    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return 路由地址
     */
    public String getRouterPath(MenuRespVO menu) {
        String routerPath = menu.getPath();
        // 内链打开外网方式
        if (menu.getParentId().intValue() != 0 && isInnerLink(menu)) {
            routerPath = innerLinkReplaceEach(routerPath);
        }
        // 非外链并且是一级目录（类型为目录）
        if (0L == menu.getParentId() && "CATALOG".equals(menu.getMenuType())
                && StateEnum.ZERO.value().equals(menu.getIframe())) {
            routerPath = "/" + menu.getPath();
        } else if (isMenuFrame(menu)) { // 非外链并且是一级目录（类型为菜单）
            routerPath = "/";
        }
        return routerPath;
    }

    /**
     * 获取组件信息
     *
     * @param menu 菜单信息
     * @return 组件信息
     */
    public String getComponent(MenuRespVO menu) {
        String component = "Layout";
        if(StrUtil.isNotEmpty(menu.getComponent()) && !isMenuFrame(menu)) {
            component = menu.getComponent();
        }else if(StrUtil.isEmpty(menu.getComponent()) && menu.getParentId() != 0L && isInnerLink(menu)) {
            component = "InnerLink"; //内联组件
        }
        else if (StrUtil.isEmpty(menu.getComponent()) && isParentView(menu)) {
            component = "ParentView";
        }
        return component;
    }

    /**
     * 是否为菜单内部跳转
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isMenuFrame(MenuRespVO menu) {
        return menu.getParentId() == 0L && "CATALOG".equals(menu.getMenuType())
                && StateEnum.ONE.value().equals(menu.getIframe());
    }

    /**
     * 是否为内链组件
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isInnerLink(MenuRespVO menu) {
        return StateEnum.ZERO.value().equals(menu.getIframe()) && menu.getPath().startsWith("http");
    }

    /**
     * 是否为parent_view组件
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isParentView(MenuRespVO menu) {
        return menu.getParentId() != 0L && "CATALOG".equals(menu.getMenuType());
    }

    /**
     * 内链域名特殊字符替换
     *
     * @return 替换后的内链域名
     */
    public String innerLinkReplaceEach(String path) {
        return path.replace("http", "")
                .replace("https", "")
                .replace("www", "")
                .replace(".", "/")
                .replace(":", "/");
    }
}
