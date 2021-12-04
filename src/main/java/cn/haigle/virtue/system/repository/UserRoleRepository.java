package cn.haigle.virtue.system.repository;

import cn.haigle.virtue.common.base.repository.BaseRepository;
import cn.haigle.virtue.system.entity.bo.SysUserRoleBo;
import org.springframework.stereotype.Service;

/**
 * 用户角色JPA
 * @author haigle
 * @date 2021/12/3 21:54
 */
@Service("userRoleRepository")
public interface UserRoleRepository extends BaseRepository<SysUserRoleBo> {

}
