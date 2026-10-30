package cn.haigle.virtue.mapper.mysql.dict;

import cn.haigle.virtue.common.mybatis.mapper.BaseMapperX;
import cn.haigle.virtue.common.mybatis.query.LambdaQueryWrapperX;
import cn.haigle.virtue.common.pojo.PageResult;
import cn.haigle.virtue.controller.admin.dict.vo.DictDataPageReqVO;
import cn.haigle.virtue.mapper.dataobject.dict.SysDictDataDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DictDataMapper extends BaseMapperX<SysDictDataDO> {

    default PageResult<SysDictDataDO> selectPage(DictDataPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SysDictDataDO>()
                .eqIfPresent(SysDictDataDO::getDictType, reqVO.getDictType())
                .likeIfPresent(SysDictDataDO::getLabel, reqVO.getLabel())
                .eqIfPresent(SysDictDataDO::getStatus, reqVO.getStatus())
                .orderByAsc(SysDictDataDO::getSort));
    }

    default List<SysDictDataDO> selectListByDictType(String dictType) {
        return selectList(new LambdaQueryWrapperX<SysDictDataDO>()
                .eq(SysDictDataDO::getDictType, dictType)
                .eq(SysDictDataDO::getStatus, "0")
                .orderByAsc(SysDictDataDO::getSort));
    }
}
