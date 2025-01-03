package cn.haigle.virtue.mapper.mysql.user;

import cn.haigle.virtue.common.mybatis.mapper.BaseMapperX;
import cn.haigle.virtue.mapper.dataobject.user.SysUserRoleDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户-角色Mapper
 * @author haigle
 * @date 2019-06-09 14:21
 */
@Mapper
public interface UserRoleMapper extends BaseMapperX<SysUserRoleDO> {

    void deleteByUserId(@Param("userId") Long userId);

    void deleteByRoleId(@Param("roleId") Long roleId);

    default void insert(List<Long> roleIds, Long userId) {
        List<SysUserRoleDO> sysUserRoleDOList = new ArrayList<>();
        roleIds.forEach(roleId -> {
            SysUserRoleDO sysUserRoleDO = new SysUserRoleDO();
            sysUserRoleDO.setRoleId(roleId);
            sysUserRoleDO.setUserId(userId);
            sysUserRoleDOList.add(sysUserRoleDO);
        });
        insertBatch(sysUserRoleDOList);
    }

}
