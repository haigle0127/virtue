package com.haigle.around.admin.role.service.impl;

import com.haigle.around.admin.role.dao.AdminRoleDao;
import com.haigle.around.common.entity.query.AdminSearchNameQuery;
import com.haigle.around.admin.role.service.AdminRoleService;
import com.haigle.around.common.base.page.Page;
import com.haigle.around.common.interceptor.annotation.Commit;
import com.haigle.around.common.util.SnowFlake;
import com.haigle.around.admin.role.entity.ao.AdminRoleAO;
import com.haigle.around.admin.role.entity.vo.AdminRoleVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 角色接口实现
 * @author haigle
 * @date 2019/7/25 14:56
 */
@Service("adminRoleService")
public class AdminRoleServiceImpl implements AdminRoleService {

    @Resource(name = "adminRoleDao")
    private AdminRoleDao adminRoleDao;

    @Override
    public Page<AdminRoleVO> list(AdminSearchNameQuery adminSearchNameQuery) {
        Page<AdminRoleVO> page = Page.<AdminRoleVO>builder()
                .page(adminSearchNameQuery.getPage())
                .pageSize(adminSearchNameQuery.getPageSize())
                .build();
        page.setRows(adminRoleDao.list(page, adminSearchNameQuery.getName()));
        page.setTotal(adminRoleDao.getTotal(page, adminSearchNameQuery.getName()));
        return page;
    }

    @Commit
    @Override
    public void save(AdminRoleAO adminRoleAo, Long uid) {
        Long roleId = SnowFlake.getInstance();
        adminRoleAo.setId(roleId);
        adminRoleDao.save(adminRoleAo, uid);

        if(!adminRoleAo.getMenuList().isEmpty()) {
            adminRoleDao.saveRoleMenu(roleId, adminRoleAo.getMenuList());
        }
    }

    @Commit
    @Override
    public void update(AdminRoleAO adminRoleDto, Long uid) {
        adminRoleDao.update(adminRoleDto, uid);
        adminRoleDao.deleteRoleMenu(adminRoleDto.getId());

        if(!adminRoleDto.getMenuList().isEmpty()) {
            adminRoleDao.saveRoleMenu(adminRoleDto.getId(), adminRoleDto.getMenuList());
        }
    }

    @Override
    public void delete(Long id) {
        adminRoleDao.delete(id);
    }
}