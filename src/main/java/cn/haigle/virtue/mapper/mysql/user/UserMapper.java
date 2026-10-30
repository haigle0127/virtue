package cn.haigle.virtue.mapper.mysql.user;

import cn.haigle.virtue.common.mybatis.mapper.BaseMapperX;
import cn.haigle.virtue.common.mybatis.query.LambdaQueryWrapperX;
import cn.haigle.virtue.common.pojo.PageResult;
import cn.haigle.virtue.controller.admin.user.vo.UserPageReqVO;
import cn.haigle.virtue.mapper.dataobject.user.SysUserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户Mapper
 * @author haigle
 * @date 2019-06-09 14:21
 */
@Mapper
public interface UserMapper extends BaseMapperX<SysUserDO> {

    default PageResult<SysUserDO> selectPage(UserPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SysUserDO>()
                .likeIfPresent(SysUserDO::getUsername, reqVO.getUsername())
                .likeIfPresent(SysUserDO::getPhone, reqVO.getMobile())
                .eqIfPresent(SysUserDO::getState, reqVO.getStatus()));
    }

    /**
     * 按用户名 / 邮箱 / 手机号查询用户
     */
    default SysUserDO selectByAccount(String account) {
        return selectOne(new LambdaQueryWrapperX<SysUserDO>()
                .and(wrapper -> wrapper
                        .eq(SysUserDO::getUsername, account)
                        .or()
                        .eq(SysUserDO::getEmail, account)
                        .or()
                        .eq(SysUserDO::getPhone, account))
                .last("LIMIT 1"));
    }

}
