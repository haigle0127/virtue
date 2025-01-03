package cn.haigle.virtue.mapper.mysql.role;

import cn.haigle.virtue.common.mybatis.mapper.BaseMapperX;
import cn.haigle.virtue.mapper.dataobject.role.SysRoleMenuDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 角色-菜单Mapper
 * @author haigle
 * @date 2019-08-04 00:02
 */
@Mapper
public interface RoleMenuMapper extends BaseMapperX<SysRoleMenuDO> {

    void deleteByRoleId(@Param("roleId") Long roleId);

    void deleteByMenuId(@Param("menuId") Long menuId);

}
