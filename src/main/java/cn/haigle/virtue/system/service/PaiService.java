package cn.haigle.virtue.system.service;

import cn.haigle.virtue.system.entity.bo.PaiBo;

/**
 * 缓存啊
 * @author haigle
 * @date 2019/3/6 18:28
 */
public interface PaiService {

    /**
     * 保存缓存
     * @param type key
     * @param label value
     * @author haigle
     * @date 2019/3/6 18:30
     */
    void save(String type, String label);

    /**
     * 删除缓存
     * @param type key
     * @author haigle
     * @date 2019/3/6 18:32
     */
    void delete(String type);

    /**
     * 获取缓存
     * @param type key
     * @return PaiEntity 值
     * @author haigle
     * @date 2019/3/6 18:31
     */
    PaiBo getPaiPo(String type);


}
