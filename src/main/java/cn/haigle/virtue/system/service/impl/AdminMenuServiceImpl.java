package cn.haigle.virtue.system.service.impl;

import cn.haigle.virtue.system.dao.AdminMenuDao;
import cn.haigle.virtue.system.entity.ao.AdminMenuAo;
import cn.haigle.virtue.system.entity.bo.AdminTreeBo;
import cn.haigle.virtue.system.entity.vo.AdminMenuVo;
import cn.haigle.virtue.system.entity.vo.AdminTreeVo;
import cn.haigle.virtue.system.entity.vo.Menu;
import cn.haigle.virtue.system.entity.vo.MenuType;
import cn.haigle.virtue.system.exception.ParentIDExistException;
import cn.haigle.virtue.system.exception.ParentNotException;
import cn.haigle.virtue.system.exception.PowerExistException;
import cn.haigle.virtue.system.service.AdminMenuService;
import cn.haigle.virtue.common.annotation.transaction.Commit;
import cn.haigle.virtue.common.util.SnowFlake;
import cn.haigle.virtue.common.util.tree.TreeUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单接口实现
 * @author haigle
 * @date 2019/8/23 10:13
 */
@Service("adminMenuService")
public class AdminMenuServiceImpl implements AdminMenuService {

    @Resource(name = "adminMenuDao")
    private AdminMenuDao adminMenuDao;

    @Override
    public List<AdminMenuVo> list(Long id) {
        return adminMenuDao.list(id);
    }

    @Override
    public List<Menu> menuTree(Long userId) {
//        List<Menu> menuList = adminMenuDao.findAll();
        List<Menu> menuList = adminMenuDao.findByMenuType(MenuType.MENU.name());
//        return TreeUtils.build(menuList.stream().filter(item -> !item.getType().equals(MenuType.ACTION)).collect(Collectors.toList()));
        return TreeUtils.build(menuList);
    }

    @Override
    public List<AdminTreeVo> menuAllTree() {
        List<AdminTreeBo> list = adminMenuDao.adminMenuAllPoList();
        List<AdminTreeVo> result = new ArrayList<>();

        for(AdminTreeBo menu : list){
            if(menu.getParentId().equals(0L)) {
                AdminTreeVo adminMenuTreeVo = AdminTreeVo.builder()
                        .id(menu.getId())
                        .name(menu.getName())
                        .hasChildren(menu.isHasChildren())
                        .build();
                result.add(adminMenuTreeVo);
            }
        }

        for (AdminTreeVo parent : result) {
            recursiveTree(parent, list);
        }

        return result;
    }

    /**
     * 递归，建立子树形结构
     * @author haigle
     * @date 2019/9/2 16:26
     */
    private static void recursiveTree(AdminTreeVo parent, List<AdminTreeBo> list) {
        List<AdminTreeVo> adminTreeVoList = new ArrayList<>();
        for (AdminTreeBo menu : list) {
            if(parent.getId().equals(menu.getParentId())) {
                AdminTreeVo adminMenuTreeVo = AdminTreeVo.builder()
                        .id(menu.getId())
                        .name(menu.getName())
                        .hasChildren(menu.isHasChildren())
                        .build();
                recursiveTree(adminMenuTreeVo, list);
                adminTreeVoList.add(adminMenuTreeVo);
            }
        }
        parent.setChildren(adminTreeVoList);
    }

    @Commit
    @Override
    public void save(AdminMenuAo adminMenuAo, Long uid) {
        validatorSaveMenu(adminMenuAo);
        Long menuId = SnowFlake.getInstance();
        adminMenuAo.setId(menuId);
        if(adminMenuAo.getParentId() != 0L) {
            if(!adminMenuDao.menuIsHasChildren(adminMenuAo.getParentId())) {
                adminMenuDao.updateHasChildren(adminMenuAo.getParentId(), 1);
            }

        }
        adminMenuDao.deleteRoleMenu(adminMenuAo.getParentId());
        adminMenuDao.save(adminMenuAo, uid);
    }

    @Override
    public void update(AdminMenuAo adminMenuAo, Long uid) {
        validatorUpdateMenu(adminMenuAo);
        adminMenuDao.update(adminMenuAo, uid);
    }

    @Commit
    @Override
    public void delete(Long id) {
        if(adminMenuDao.getIsChildren(id) != null) {
            throw new ParentNotException();
        }
        Long parentId = adminMenuDao.menuIsParentId(id);
        if(adminMenuDao.menuChildrenCount(parentId) < 2) {
            adminMenuDao.updateHasChildren(parentId, 0);
        }
        adminMenuDao.delete(id);
        adminMenuDao.deleteRoleMenu(id);
    }

    @Override
    public List<Long> getRoleMenuList(Long id) {
        return adminMenuDao.getRoleMenuList(id);
    }

    private void validatorSaveMenu(AdminMenuAo adminMenuAo) {
        if(validatorIsNotMenu(adminMenuAo.getParentId())) {
            throw new ParentIDExistException();
        }
        if(adminMenuDao.getIsPower(adminMenuAo.getPower()) != null) {
            throw new PowerExistException();
        }
    }

    private void validatorUpdateMenu(AdminMenuAo adminMenuAo) {
        if(validatorIsNotMenu(adminMenuAo.getParentId())) {
            throw new ParentIDExistException();
        }
        if(adminMenuDao.getIsPowerNotId(adminMenuAo.getPower(), adminMenuAo.getId()) != null) {
            throw new PowerExistException();
        }
    }

    private boolean validatorIsNotMenu(Long power) {
        if(power != 0L) {
            return adminMenuDao.getIsMenu(power) == null;
        }
        return false;
    }

}
