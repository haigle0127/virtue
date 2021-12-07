package cn.haigle.virtue.system.repository;

import cn.haigle.virtue.common.base.repository.BaseRepository;
import cn.haigle.virtue.system.entity.bo.SysRoleBo;
import cn.haigle.virtue.system.entity.bo.SysUserBo;
import org.springframework.data.jpa.repository.Query;
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

    /**
     * 查询用户角色名称
     * @param uid 用户ID
     * @return List<String>
     * @author haigle
     * @date 2021/12/7 21:52
     */
    @Query("select srb.name from SysRoleBo srb " +
            "left join SysUserRoleBo surb on surb.roleId = srb.id " +
            "where surb.userId = ?1")
    List<String> selectRoleName(Long uid);

}
