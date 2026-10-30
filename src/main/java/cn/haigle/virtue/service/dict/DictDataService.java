package cn.haigle.virtue.service.dict;

import cn.haigle.virtue.common.pojo.PageResult;
import cn.haigle.virtue.controller.admin.dict.vo.DictDataPageReqVO;
import cn.haigle.virtue.controller.admin.dict.vo.DictDataRespVO;
import cn.haigle.virtue.controller.admin.dict.vo.DictDataSaveReqVO;

import java.util.List;

/**
 * 字典数据 Service 接口
 */
public interface DictDataService {

    /**
     * 获取字典数据分页
     */
    PageResult<DictDataRespVO> page(DictDataPageReqVO reqVO);

    /**
     * 获取字典数据详情
     */
    DictDataRespVO detail(Long id);

    /**
     * 根据字典类型获取字典数据列表
     */
    List<DictDataRespVO> listByDictType(String dictType);

    /**
     * 新增字典数据
     */
    void add(DictDataSaveReqVO reqVO);

    /**
     * 修改字典数据
     */
    void update(DictDataSaveReqVO reqVO);

    /**
     * 删除字典数据
     */
    void delete(Long id);
}
