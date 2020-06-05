package com.haigle.around.admin.rbac.service.impl;

import com.haigle.around.admin.rbac.dao.AdminRoleDao;
import com.haigle.around.admin.rbac.entity.Query.AdminSearchNameQuery;
import com.haigle.around.admin.rbac.service.AdminRoleService;
import com.haigle.around.common.base.page.Page;
import com.haigle.around.common.interceptor.annotation.Commit;
import com.haigle.around.common.util.SnowFlake;
import com.haigle.around.admin.rbac.entity.ao.AdminRoleAo;
import com.haigle.around.admin.rbac.entity.vo.AdminRoleVo;
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
    public Page<AdminRoleVo> list(AdminSearchNameQuery adminSearchNameQuery) {
        Page<AdminRoleVo> page = Page.<AdminRoleVo>builder()
                .page(adminSearchNameQuery.getPage())
                .pageSize(adminSearchNameQuery.getPageSize())
                .build();
        page.setRows(adminRoleDao.list(page, adminSearchNameQuery.getName()));
        page.setTotal(adminRoleDao.getTotal(page, adminSearchNameQuery.getName()));
        return page;
    }

    @Commit
    @Override
    public void save(AdminRoleAo adminRoleAo, Long uid) {
        Long roleId = SnowFlake.getInstance();
        adminRoleAo.setId(roleId);
        adminRoleDao.save(adminRoleAo, uid);

        if(!adminRoleAo.getMenuList().isEmpty()) {
            adminRoleDao.saveRoleMenu(roleId, adminRoleAo.getMenuList());
        }
    }

    @Commit
    @Override
    public void update(AdminRoleAo adminRoleDto, Long uid) {
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
