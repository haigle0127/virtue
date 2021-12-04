package cn.haigle.virtue.system.repository;

import cn.haigle.virtue.common.base.repository.BaseRepository;
import cn.haigle.virtue.system.entity.bo.SysRoleMenuBo;
import org.springframework.stereotype.Service;

/**
 * 角色菜单JPA
 * @author haigle
 * @date 2021/12/3 21:54
 */
@Service("roleMenuRepository")
public interface RoleMenuRepository extends BaseRepository<SysRoleMenuBo> {



}
