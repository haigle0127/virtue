package com.haigle.around.admin.service.impl;

import com.haigle.around.admin.dao.AdminMenuDao;
import com.haigle.around.admin.entity.ao.AdminMenuAo;
import com.haigle.around.admin.entity.po.AdminTreePo;
import com.haigle.around.admin.entity.vo.AdminTreeVo;
import com.haigle.around.admin.entity.vo.AdminMenuVo;
import com.haigle.around.admin.service.AdminMenuService;
import com.haigle.around.common.interceptor.annotation.Commit;
import com.haigle.around.common.interceptor.model.ServiceResult;
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
    public List<AdminMenuVo> list(Long id) {

        return adminMenuDao.list(id);
    }

    @Override
    public List<AdminTreeVo> menuAllTree() {
        List<AdminTreePo> list = adminMenuDao.adminMenuAllPoList();
        List<AdminTreeVo> result = new ArrayList<>();

        for(AdminTreePo menu : list){
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
    private static void recursiveTree(AdminTreeVo parent, List<AdminTreePo> list) {
        List<AdminTreeVo> adminTreeVoList = new ArrayList<>();
        for (AdminTreePo menu : list) {
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
    public ServiceResult save(AdminMenuAo adminMenuAo, Long uid) {
        ServiceResult serviceResult = validatorSaveMenu(adminMenuAo);
        if(!serviceResult.isSuccess()) {
            return serviceResult;
        }
        Long menuId = SnowFlake.getInstance();
        adminMenuAo.setId(menuId);
        if(adminMenuAo.getParentId() != 0L) {
            if(!adminMenuDao.menuIsHasChildren(adminMenuAo.getParentId())) {
                adminMenuDao.updateHasChildren(adminMenuAo.getParentId(), 1);
            }

        }
        adminMenuDao.deleteRoleMenu(adminMenuAo.getParentId());
        adminMenuDao.save(adminMenuAo, uid);
        return new ServiceResult("common.success", true);
    }

    @Override
    public ServiceResult update(AdminMenuAo adminMenuAo, Long uid) {
        ServiceResult serviceResult = validatorUpdateMenu(adminMenuAo);
        if(!serviceResult.isSuccess()) {
            return serviceResult;
        }
        adminMenuDao.update(adminMenuAo, uid);
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

    private ServiceResult validatorSaveMenu(AdminMenuAo adminMenuAo) {
        if(validatorIsNotMenu(adminMenuAo.getParentId())) {
            return new ServiceResult("menu.parent_id.error", false);
        }
        if(adminMenuDao.getIsPower(adminMenuAo.getPower()) != null) {
            return new ServiceResult("menu.power.exist", false);
        }
        return new ServiceResult("common.success", true);

    }

    private ServiceResult validatorUpdateMenu(AdminMenuAo adminMenuAo) {
        if(validatorIsNotMenu(adminMenuAo.getParentId())) {
            return new ServiceResult("menu.parent_id.error", false);
        }
        if(adminMenuDao.getIsPowerNotId(adminMenuAo.getPower(), adminMenuAo.getId()) != null) {
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
