package cn.haigle.virtue.service.dict;

import cn.haigle.virtue.common.pojo.PageResult;
import cn.haigle.virtue.controller.admin.dict.vo.DictTypePageReqVO;
import cn.haigle.virtue.controller.admin.dict.vo.DictTypeRespVO;
import cn.haigle.virtue.controller.admin.dict.vo.DictTypeSaveReqVO;
import cn.haigle.virtue.mapper.dataobject.dict.SysDictTypeDO;
import cn.haigle.virtue.mapper.mysql.dict.DictTypeMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典类型 Service 实现
 *
 * @author haigle
 */
@Service
public class DictTypeServiceImpl implements DictTypeService {

    @Resource
    private DictTypeMapper dictTypeMapper;

    @Override
    public PageResult<DictTypeRespVO> page(DictTypePageReqVO reqVO) {
        PageResult<SysDictTypeDO> pageResult = dictTypeMapper.selectPage(reqVO);
        List<DictTypeRespVO> list = pageResult.getList().stream().map(dictType -> {
            DictTypeRespVO vo = new DictTypeRespVO();
            BeanUtils.copyProperties(dictType, vo);
            return vo;
        }).toList();
        return new PageResult<>(list, pageResult.getTotal());
    }

    @Override
    public DictTypeRespVO detail(Long id) {
        SysDictTypeDO dictTypeDO = dictTypeMapper.selectById(id);
        if (dictTypeDO == null) {
            return null;
        }
        DictTypeRespVO vo = new DictTypeRespVO();
        BeanUtils.copyProperties(dictTypeDO, vo);
        return vo;
    }

    @Override
    public void add(DictTypeSaveReqVO reqVO) {
        SysDictTypeDO dictTypeDO = new SysDictTypeDO();
        BeanUtils.copyProperties(reqVO, dictTypeDO);
        dictTypeMapper.insert(dictTypeDO);
    }

    @Override
    public void update(DictTypeSaveReqVO reqVO) {
        SysDictTypeDO dictTypeDO = new SysDictTypeDO();
        BeanUtils.copyProperties(reqVO, dictTypeDO);
        dictTypeMapper.updateById(dictTypeDO);
    }

    @Override
    public void delete(Long id) {
        dictTypeMapper.deleteById(id);
    }

    @Override
    public List<DictTypeRespVO> simpleList() {
        return dictTypeMapper.selectList().stream().map(dictType -> {
            DictTypeRespVO vo = new DictTypeRespVO();
            BeanUtils.copyProperties(dictType, vo);
            return vo;
        }).toList();
    }

}
