package cn.haigle.virtue.system.service.impl;

import cn.haigle.virtue.system.dao.AdminPaiDao;
import cn.haigle.virtue.system.entity.bo.AdminPaiBo;
import cn.haigle.virtue.system.service.AdminPaiService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 验证码实现
 * @author haigle
 * @date 2019/6/21 10:07
 */
@Service("adminPaiService")
public class AdminPaiServiceImpl implements AdminPaiService {

    @Resource(name = "adminPaiDao")
    private AdminPaiDao adminPaiDao;

    @Override
    public void save(String type, String label) {
        adminPaiDao.save(type, label);
    }

    @Override
    public void delete(String type) {
        adminPaiDao.delete(type);
    }

    @Override
    public AdminPaiBo getPaiPo(String type) {
        return adminPaiDao.getPaiPo(type);
    }
}
