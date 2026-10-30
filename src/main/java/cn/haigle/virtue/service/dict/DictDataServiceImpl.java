package cn.haigle.virtue.service.dict;

import cn.haigle.virtue.common.pojo.PageResult;
import cn.haigle.virtue.controller.admin.dict.vo.DictDataPageReqVO;
import cn.haigle.virtue.controller.admin.dict.vo.DictDataRespVO;
import cn.haigle.virtue.controller.admin.dict.vo.DictDataSaveReqVO;
import cn.haigle.virtue.mapper.dataobject.dict.SysDictDataDO;
import cn.haigle.virtue.mapper.mysql.dict.DictDataMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典数据 Service 实现
 *
 * @author haigle
 */
@Service
public class DictDataServiceImpl implements DictDataService {

    @Resource
    private DictDataMapper dictDataMapper;

    @Override
    public PageResult<DictDataRespVO> page(DictDataPageReqVO reqVO) {
        PageResult<SysDictDataDO> pageResult = dictDataMapper.selectPage(reqVO);
        List<DictDataRespVO> list = pageResult.getList().stream().map(dictData -> {
            DictDataRespVO vo = new DictDataRespVO();
            BeanUtils.copyProperties(dictData, vo);
            return vo;
        }).toList();
        return new PageResult<>(list, pageResult.getTotal());
    }

    @Override
    public DictDataRespVO detail(Long id) {
        SysDictDataDO dictDataDO = dictDataMapper.selectById(id);
        if (dictDataDO == null) {
            return null;
        }
        DictDataRespVO vo = new DictDataRespVO();
        BeanUtils.copyProperties(dictDataDO, vo);
        return vo;
    }

    @Override
    public List<DictDataRespVO> listByDictType(String dictType) {
        return dictDataMapper.selectListByDictType(dictType).stream().map(dictData -> {
            DictDataRespVO vo = new DictDataRespVO();
            BeanUtils.copyProperties(dictData, vo);
            return vo;
        }).toList();
    }

    @Override
    public void add(DictDataSaveReqVO reqVO) {
        SysDictDataDO dictDataDO = new SysDictDataDO();
        BeanUtils.copyProperties(reqVO, dictDataDO);
        dictDataMapper.insert(dictDataDO);
    }

    @Override
    public void update(DictDataSaveReqVO reqVO) {
        SysDictDataDO dictDataDO = new SysDictDataDO();
        BeanUtils.copyProperties(reqVO, dictDataDO);
        dictDataMapper.updateById(dictDataDO);
    }

    @Override
    public void delete(Long id) {
        dictDataMapper.deleteById(id);
    }

}
