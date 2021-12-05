package cn.haigle.virtue.system.repository;

import cn.haigle.virtue.common.base.repository.BaseRepository;
import cn.haigle.virtue.system.entity.bo.SysMenuBo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单JPA
 * @author haigle
 * @date 2021/12/3 21:54
 */
@Service("menuRepository")
public interface MenuRepository extends BaseRepository<SysMenuBo> {

    /**
     * 查询菜单
     * @param menuType 菜单类型
     * @return List<SysMenuEntity>
     * @author haigle
     * @date 2021/12/3 16:49
     */
    List<SysMenuBo> findByMenuType(String menuType);

    /**
     * 根据用户ID查询权限
     * @param uid 用户ID
     * @return List<SysMenuBo>
     * @author haigle
     * @date 2021/12/5 20:28
     */
    @Query("select SysMenuBo from SysMenuBo smb " +
            "left join SysRoleMenuBo srm on srm.menuId = smb.id " +
            "left join SysUserRoleBo sub on sub.roleId = srm.roleId " +
            "where sub.userId = ?1")
    List<SysMenuBo> selectByUserId(Long uid);

}
