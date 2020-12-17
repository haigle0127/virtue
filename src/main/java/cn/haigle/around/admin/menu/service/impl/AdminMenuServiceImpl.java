package cn.haigle.around.admin.menu.service.impl;

import cn.haigle.around.admin.menu.dao.AdminMenuDao;
import cn.haigle.around.admin.menu.entity.ao.AdminMenuAO;
import cn.haigle.around.admin.menu.entity.bo.AdminTreeBO;
import cn.haigle.around.admin.menu.entity.vo.AdminMenuVO;
import cn.haigle.around.admin.menu.entity.vo.AdminTreeVO;
import cn.haigle.around.admin.menu.exception.ParentIDExistException;
import cn.haigle.around.admin.menu.exception.ParentNotException;
import cn.haigle.around.admin.menu.exception.PowerExistException;
import cn.haigle.around.admin.menu.service.AdminMenuService;
import cn.haigle.around.admin.user.exception.UserExistException;
import cn.haigle.around.common.annotation.transaction.Commit;
import cn.haigle.around.common.interceptor.model.service.ServiceResult;
import cn.haigle.around.common.util.SnowFlake;
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
    public List<AdminMenuVO> list(Long id) {

        return adminMenuDao.list(id);
    }

    @Override
    public List<AdminTreeVO> menuAllTree() {
        List<AdminTreeBO> list = adminMenuDao.adminMenuAllPoList();
        List<AdminTreeVO> result = new ArrayList<>();

        for(AdminTreeBO menu : list){
            if(menu.getParentId().equals(0L)) {
                AdminTreeVO adminMenuTreeVo = AdminTreeVO.builder()
                        .id(menu.getId())
                        .name(menu.getName())
                        .hasChildren(menu.isHasChildren())
                        .build();
                result.add(adminMenuTreeVo);
            }
        }

        for (AdminTreeVO parent : result) {
            recursiveTree(parent, list);
        }

        return result;
    }

    /**
     * 递归，建立子树形结构
     * @author haigle
     * @date 2019/9/2 16:26
     */
    private static void recursiveTree(AdminTreeVO parent, List<AdminTreeBO> list) {
        List<AdminTreeVO> adminTreeVoList = new ArrayList<>();
        for (AdminTreeBO menu : list) {
            if(parent.getId().equals(menu.getParentId())) {
                AdminTreeVO adminMenuTreeVo = AdminTreeVO.builder()
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
    public void save(AdminMenuAO adminMenuAO, Long uid) {
        validatorSaveMenu(adminMenuAO);
        Long menuId = SnowFlake.getInstance();
        adminMenuAO.setId(menuId);
        if(adminMenuAO.getParentId() != 0L) {
            if(!adminMenuDao.menuIsHasChildren(adminMenuAO.getParentId())) {
                adminMenuDao.updateHasChildren(adminMenuAO.getParentId(), 1);
            }

        }
        adminMenuDao.deleteRoleMenu(adminMenuAO.getParentId());
        adminMenuDao.save(adminMenuAO, uid);
    }

    @Override
    public void update(AdminMenuAO adminMenuAO, Long uid) {
        validatorUpdateMenu(adminMenuAO);
        adminMenuDao.update(adminMenuAO, uid);
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

    private void validatorSaveMenu(AdminMenuAO adminMenuAO) {
        if(validatorIsNotMenu(adminMenuAO.getParentId())) {
            throw new ParentIDExistException();
        }
        if(adminMenuDao.getIsPower(adminMenuAO.getPower()) != null) {
            throw new PowerExistException();
        }
    }

    private void validatorUpdateMenu(AdminMenuAO adminMenuAO) {
        if(validatorIsNotMenu(adminMenuAO.getParentId())) {
            throw new ParentIDExistException();
        }
        if(adminMenuDao.getIsPowerNotId(adminMenuAO.getPower(), adminMenuAO.getId()) != null) {
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
