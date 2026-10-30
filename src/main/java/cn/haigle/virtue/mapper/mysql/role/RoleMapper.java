package cn.haigle.virtue.mapper.mysql.role;

import cn.haigle.virtue.common.mybatis.mapper.BaseMapperX;
import cn.haigle.virtue.common.mybatis.query.LambdaQueryWrapperX;
import cn.haigle.virtue.common.pojo.PageResult;
import cn.haigle.virtue.controller.admin.role.vo.RolePageReqVO;
import cn.haigle.virtue.controller.admin.user.vo.UserRespVO;
import cn.haigle.virtue.controller.admin.user.vo.UserRoleRespVO;
import cn.haigle.virtue.mapper.dataobject.role.SysRoleDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色Mapper
 *
 * @author haigle
 */
@Mapper
public interface RoleMapper extends BaseMapperX<SysRoleDO> {

    default PageResult<SysRoleDO> selectPage(RolePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SysRoleDO>()
                .likeIfPresent(SysRoleDO::getName, reqVO.getName())
                .likeIfPresent(SysRoleDO::getMark, reqVO.getCode())
                .eqIfPresent(SysRoleDO::getState,
                        reqVO.getStatus() != null ? reqVO.getStatus().toString() : null));
    }

    List<String> selectRoleMarkByUserId(Long userId);

}
