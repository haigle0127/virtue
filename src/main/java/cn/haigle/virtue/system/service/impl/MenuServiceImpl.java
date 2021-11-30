package cn.haigle.virtue.system.service.impl;

import cn.haigle.virtue.system.dao.MenuDao;
import cn.haigle.virtue.system.entity.ao.MenuAo;
import cn.haigle.virtue.system.entity.bo.TreeBo;
import cn.haigle.virtue.system.entity.vo.Meta;
import cn.haigle.virtue.system.entity.vo.TreeVo;
import cn.haigle.virtue.system.entity.vo.Menu;
import cn.haigle.virtue.system.entity.vo.MenuType;
import cn.haigle.virtue.system.exception.ParentIDExistException;
import cn.haigle.virtue.system.exception.ParentNotException;
import cn.haigle.virtue.system.exception.PowerExistException;
import cn.haigle.virtue.system.service.MenuService;
import cn.haigle.virtue.common.annotation.transaction.Commit;
import cn.haigle.virtue.common.util.SnowFlake;
import cn.haigle.virtue.common.util.tree.TreeUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * 菜单接口实现
 * @author haigle
 * @date 2019/8/23 10:13
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Resource(name = "menuDao")
    private MenuDao menuDao;

    @Override
    public List<Menu> list() {
        List<Menu> menuList = menuDao.findAll();
        return TreeUtils.build(menuList);
    }

    @Override
    public List<Menu> menuTree(Long userId) {
        List<Menu> menuList = menuDao.findByMenuType(MenuType.MENU.name());
        menuList.forEach(menu -> {
            Meta meta = new Meta();
            meta.setTitle(menu.getName()).setIcon(menu.getIcon());
            menu.setMeta(meta);
        });
//        return TreeUtils.build(menuList.stream().filter(item -> !item.getType().equals(MenuType.ACTION)).collect(Collectors.toList()));
        return TreeUtils.build(menuList);
    }

    @Override
    public List<TreeVo> menuAllTree() {
        List<TreeBo> list = menuDao.adminMenuAllPoList();
        List<TreeVo> result = new ArrayList<>();

        for(TreeBo menu : list){
            if(menu.getParentId().equals(0L)) {
                TreeVo adminMenuTreeVo = TreeVo.builder()
                        .id(menu.getId())
                        .name(menu.getName())
                        .hasChildren(menu.isHasChildren())
                        .build();
                result.add(adminMenuTreeVo);
            }
        }

        for (TreeVo parent : result) {
            recursiveTree(parent, list);
        }

        return result;
    }

    /**
     * 递归，建立子树形结构
     * @author haigle
     * @date 2019/9/2 16:26
     */
    private static void recursiveTree(TreeVo parent, List<TreeBo> list) {
        List<TreeVo> treeVoList = new ArrayList<>();
        for (TreeBo menu : list) {
            if(parent.getId().equals(menu.getParentId())) {
                TreeVo adminMenuTreeVo = TreeVo.builder()
                        .id(menu.getId())
                        .name(menu.getName())
                        .hasChildren(menu.isHasChildren())
                        .build();
                recursiveTree(adminMenuTreeVo, list);
                treeVoList.add(adminMenuTreeVo);
            }
        }
        parent.setChildren(treeVoList);
    }

    @Commit
    @Override
    public void save(MenuAo menuAo, Long uid) {
        validatorSaveMenu(menuAo);
        Long menuId = SnowFlake.getInstance();
        menuAo.setId(menuId);
        if(menuAo.getParentId() != 0L) {
            if(!menuDao.menuIsHasChildren(menuAo.getParentId())) {
                menuDao.updateHasChildren(menuAo.getParentId(), 1);
            }

        }
        menuDao.deleteRoleMenu(menuAo.getParentId());
        menuDao.save(menuAo, uid);
    }

    @Override
    public void update(MenuAo menuAo, Long uid) {
        validatorUpdateMenu(menuAo);
        menuDao.update(menuAo, uid);
    }

    @Commit
    @Override
    public void delete(Long id) {
        if(menuDao.getIsChildren(id) != null) {
            throw new ParentNotException();
        }
        Long parentId = menuDao.menuIsParentId(id);
        if(menuDao.menuChildrenCount(parentId) < 2) {
            menuDao.updateHasChildren(parentId, 0);
        }
        menuDao.delete(id);
        menuDao.deleteRoleMenu(id);
    }

    @Override
    public List<Long> getRoleMenuList(Long id) {
        return menuDao.getRoleMenuList(id);
    }

    private void validatorSaveMenu(MenuAo menuAo) {
        if(validatorIsNotMenu(menuAo.getParentId())) {
            throw new ParentIDExistException();
        }
        if(menuDao.getIsPower(menuAo.getPower()) != null) {
            throw new PowerExistException();
        }
    }

    private void validatorUpdateMenu(MenuAo menuAo) {
        if(validatorIsNotMenu(menuAo.getParentId())) {
            throw new ParentIDExistException();
        }
        if(menuDao.getIsPowerNotId(menuAo.getPower(), menuAo.getId()) != null) {
            throw new PowerExistException();
        }
    }

    private boolean validatorIsNotMenu(Long power) {
        if(power != 0L) {
            return menuDao.getIsMenu(power) == null;
        }
        return false;
    }

}
