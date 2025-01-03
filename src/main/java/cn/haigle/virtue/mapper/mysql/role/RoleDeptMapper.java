package cn.haigle.virtue.mapper.mysql.role;

import cn.haigle.virtue.common.mybatis.mapper.BaseMapperX;
import cn.haigle.virtue.mapper.dataobject.role.SysRoleDeptDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 角色-部门Mapper
 * @author haigle
 * @date 2019-08-04 00:02
 */
@Mapper
public interface RoleDeptMapper extends BaseMapperX<SysRoleDeptDO> {

    void deleteByRoleId(@Param("roleId") Long roleId);

    void deleteByDeptId(@Param("deptId") Long menuId);

}
