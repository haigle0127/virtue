package cn.haigle.virtue.system.dao;

import cn.haigle.virtue.system.dao.provider.AdminRoleDaoProvider;
import cn.haigle.virtue.system.entity.ao.AdminRoleAo;
import cn.haigle.virtue.system.entity.vo.AdminRoleVo;
import cn.haigle.virtue.common.base.page.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色管理DAO
 * @author haigle
 * @date 2019-08-04 00:02
 */
@Mapper
@Repository
public interface AdminRoleDao {

    /**
     * 查询角色列表
     * @param page Page<RoleVo> 分页
     * @param name 角色名
     * @return List<RoleVo> 角色列表
     * @author haigle
     * @date 2019-08-04 00:05
     */
    @SelectProvider(type = AdminRoleDaoProvider.class, method = "list")
    List<AdminRoleVo> list(Page<AdminRoleVo> page, String name);

    /**
     * 获取角色总数
     * @param page Page<RoleVo> 分页
     * @param name 角色名
     * @return int 总数
     * @author haigle
     * @date 2019-08-04 00:57
     */
    @SelectProvider(type = AdminRoleDaoProvider.class, method = "getTotal")
    int getTotal(Page<AdminRoleVo> page, String name);

    /**
     * 新增角色
     * @param m AdminRoleDto
     * @param uid 操作人ID
     * @author haigle
     * @date 2019-08-04 21:22
     */
    @SelectProvider(type = AdminRoleDaoProvider.class, method = "save")
    void save(AdminRoleAo m, Long uid);

    /**
     * 更新角色
     * @param m RoleDto
     * @param uid 操作人ID
     * @author haigle
     * @date 2019-08-04 21:22
     */
    @SelectProvider(type = AdminRoleDaoProvider.class, method = "update")
    void update(AdminRoleAo m, Long uid);

    /**
     * 删除角色
     * @param id 角色ID
     * @author haigle
     * @date 2019-08-04 22:24
     */
    @Delete("DELETE FROM sys_role WHERE id=#{id}")
    void delete(Long id);

    /**
     * 删除角色
     * @param roleId 角色ID
     * @author haigle
     * @date 2019-08-04 21:22
     */
    @Delete("DELETE FROM sys_role_menu WHERE role_id=#{roleId}")
    void deleteRoleMenu(Long roleId);

    /**
     * 保存角色权限关联
     * @param roleId Long 角色ID列表
     * @param menuList List<Long> 权限ID列表
     * @author haigle
     * @date 2019-08-04 21:36
     */
    @SelectProvider(type = AdminRoleDaoProvider.class, method = "saveRoleMenu")
    void saveRoleMenu(Long roleId, List<Long> menuList);

}
