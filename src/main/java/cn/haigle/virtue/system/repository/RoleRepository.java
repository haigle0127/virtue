package cn.haigle.virtue.system.repository;

import cn.haigle.virtue.common.base.repository.BaseRepository;
import cn.haigle.virtue.system.entity.bo.SysRoleBo;
import cn.haigle.virtue.system.entity.bo.SysUserBo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 角色JPA
 * @author haigle
 * @date 2021/12/3 21:54
 */
@Service("roleRepository")
public interface RoleRepository extends BaseRepository<SysRoleBo> {


}
