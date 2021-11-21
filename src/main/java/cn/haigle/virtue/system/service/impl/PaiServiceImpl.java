package cn.haigle.virtue.system.service.impl;

import cn.haigle.virtue.system.dao.PaiDao;
import cn.haigle.virtue.system.entity.bo.PaiBo;
import cn.haigle.virtue.system.service.PaiService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 验证码实现
 * @author haigle
 * @date 2019/6/21 10:07
 */
@Service("paiService")
public class PaiServiceImpl implements PaiService {

    @Resource(name = "paiDao")
    private PaiDao paiDao;

    @Override
    public void save(String type, String label) {
        paiDao.save(type, label);
    }

    @Override
    public void delete(String type) {
        paiDao.delete(type);
    }

    @Override
    public PaiBo getPaiPo(String type) {
        return paiDao.getPaiPo(type);
    }
}
