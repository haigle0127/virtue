package cn.haigle.virtue.admin.role.service.impl;

import cn.haigle.virtue.admin.role.dao.AdminRoleDao;
import cn.haigle.virtue.admin.role.entity.ao.AdminRoleAO;
import cn.haigle.virtue.admin.role.entity.vo.AdminRoleVO;
import cn.haigle.virtue.admin.role.service.AdminRoleService;
import cn.haigle.virtue.common.annotation.transaction.Commit;
import cn.haigle.virtue.common.base.page.Page;
import cn.haigle.virtue.common.entity.query.NameQuery;
import cn.haigle.virtue.common.util.SnowFlake;
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
    public Page<AdminRoleVO> list(NameQuery adminSearchNameQuery) {
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
    public void delete(Long id) { adminRoleDao.delete(id);}
}
