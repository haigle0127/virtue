package cn.haigle.virtue.mapper.mysql.dict;

import cn.haigle.virtue.common.mybatis.mapper.BaseMapperX;
import cn.haigle.virtue.common.mybatis.query.LambdaQueryWrapperX;
import cn.haigle.virtue.common.pojo.PageResult;
import cn.haigle.virtue.controller.admin.dict.vo.DictTypePageReqVO;
import cn.haigle.virtue.mapper.dataobject.dict.SysDictTypeDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DictTypeMapper extends BaseMapperX<SysDictTypeDO> {

    default PageResult<SysDictTypeDO> selectPage(DictTypePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SysDictTypeDO>()
                .likeIfPresent(SysDictTypeDO::getName, reqVO.getName())
                .likeIfPresent(SysDictTypeDO::getType, reqVO.getType())
                .eqIfPresent(SysDictTypeDO::getStatus, reqVO.getStatus()));
    }
}
