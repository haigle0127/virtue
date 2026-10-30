package cn.haigle.virtue.service.dict;

import cn.haigle.virtue.common.pojo.PageResult;
import cn.haigle.virtue.controller.admin.dict.vo.DictTypePageReqVO;
import cn.haigle.virtue.controller.admin.dict.vo.DictTypeRespVO;
import cn.haigle.virtue.controller.admin.dict.vo.DictTypeSaveReqVO;

import java.util.List;

/**
 * 字典类型 Service 接口
 */
public interface DictTypeService {

    /**
     * 获取字典类型分页
     */
    PageResult<DictTypeRespVO> page(DictTypePageReqVO reqVO);

    /**
     * 获取字典类型详情
     */
    DictTypeRespVO detail(Long id);

    /**
     * 新增字典类型
     */
    void add(DictTypeSaveReqVO reqVO);

    /**
     * 修改字典类型
     */
    void update(DictTypeSaveReqVO reqVO);

    /**
     * 删除字典类型
     */
    void delete(Long id);

    /**
     * 获取字典类型精简列表
     */
    List<DictTypeRespVO> simpleList();
}
