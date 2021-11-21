package cn.haigle.virtue.system.service.impl;

import cn.haigle.virtue.system.dao.RoleDao;
import cn.haigle.virtue.system.entity.ao.RoleAo;
import cn.haigle.virtue.system.entity.vo.RoleVo;
import cn.haigle.virtue.system.service.RoleService;
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
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Resource(name = "roleDao")
    private RoleDao roleDao;

    @Override
    public Page<RoleVo> list(NameQuery adminSearchNameQuery) {
        Page<RoleVo> page = Page.<RoleVo>builder()
                .page(adminSearchNameQuery.getPage())
                .pageSize(adminSearchNameQuery.getPageSize())
                .build();
        page.setRows(roleDao.list(page, adminSearchNameQuery.getName()));
        page.setTotal(roleDao.getTotal(page, adminSearchNameQuery.getName()));
        return page;
    }

    @Commit
    @Override
    public void save(RoleAo roleAo, Long uid) {
        Long roleId = SnowFlake.getInstance();
        roleAo.setId(roleId);
        roleDao.save(roleAo, uid);

        if(!roleAo.getMenuList().isEmpty()) {
            roleDao.saveRoleMenu(roleId, roleAo.getMenuList());
        }
    }

    @Commit
    @Override
    public void update(RoleAo adminRoleDto, Long uid) {
        roleDao.update(adminRoleDto, uid);
        roleDao.deleteRoleMenu(adminRoleDto.getId());

        if(!adminRoleDto.getMenuList().isEmpty()) {
            roleDao.saveRoleMenu(adminRoleDto.getId(), adminRoleDto.getMenuList());
        }
    }

    @Override
    public void delete(Long id) { roleDao.delete(id);}
}
