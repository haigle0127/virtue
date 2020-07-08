package com.haigle.around.admin.menu.service.impl;

import com.haigle.around.admin.menu.dao.AdminMenuDao;
import com.haigle.around.admin.menu.entity.ao.AdminMenuAO;
import com.haigle.around.admin.menu.entity.bo.AdminTreeBO;
import com.haigle.around.admin.menu.entity.vo.AdminTreeVO;
import com.haigle.around.admin.menu.entity.vo.AdminMenuVO;
import com.haigle.around.admin.menu.service.AdminMenuService;
import com.haigle.around.common.interceptor.annotation.Commit;
import com.haigle.around.common.interceptor.model.service.ServiceResult;
import com.haigle.around.common.util.SnowFlake;
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
    public ServiceResult save(AdminMenuAO adminMenuAO, Long uid) {
        ServiceResult serviceResult = validatorSaveMenu(adminMenuAO);
        if(!serviceResult.isSuccess()) {
            return serviceResult;
        }
        Long menuId = SnowFlake.getInstance();
        adminMenuAO.setId(menuId);
        if(adminMenuAO.getParentId() != 0L) {
            if(!adminMenuDao.menuIsHasChildren(adminMenuAO.getParentId())) {
                adminMenuDao.updateHasChildren(adminMenuAO.getParentId(), 1);
            }

        }
        adminMenuDao.deleteRoleMenu(adminMenuAO.getParentId());
        adminMenuDao.save(adminMenuAO, uid);
        return new ServiceResult("common.success", true);
    }

    @Override
    public ServiceResult update(AdminMenuAO adminMenuAO, Long uid) {
        ServiceResult serviceResult = validatorUpdateMenu(adminMenuAO);
        if(!serviceResult.isSuccess()) {
            return serviceResult;
        }
        adminMenuDao.update(adminMenuAO, uid);
        return new ServiceResult("common.success", true);
    }

    @Commit
    @Override
    public void delete(Long id) {
        Long parentId = adminMenuDao.menuIsParentId(id);
        if(adminMenuDao.menuChildrenCount(parentId) < 2) {
            adminMenuDao.updateHasChildren(parentId, 0);
        }
        adminMenuDao.delete(id);
        adminMenuDao.deleteRoleMenu(id);
    }

    @Override
    public boolean getIsChildren(Long parentId) {
        return adminMenuDao.getIsChildren(parentId) != null;
    }

    @Override
    public List<Long> getRoleMenuList(Long id) {
        return adminMenuDao.getRoleMenuList(id);
    }

    private ServiceResult validatorSaveMenu(AdminMenuAO adminMenuAO) {
        if(validatorIsNotMenu(adminMenuAO.getParentId())) {
            return new ServiceResult("menu.parent_id.error", false);
        }
        if(adminMenuDao.getIsPower(adminMenuAO.getPower()) != null) {
            return new ServiceResult("menu.power.exist", false);
        }
        return new ServiceResult("common.success", true);

    }

    private ServiceResult validatorUpdateMenu(AdminMenuAO adminMenuAO) {
        if(validatorIsNotMenu(adminMenuAO.getParentId())) {
            return new ServiceResult("menu.parent_id.error", false);
        }
        if(adminMenuDao.getIsPowerNotId(adminMenuAO.getPower(), adminMenuAO.getId()) != null) {
            return new ServiceResult("menu.power.exist", false);
        }
        return new ServiceResult("common.success", true);

    }

    private boolean validatorIsNotMenu(Long power) {
        if(power != 0L) {
            return adminMenuDao.getIsMenu(power) == null;
        }
        return false;
    }

}
