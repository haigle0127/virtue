package cn.haigle.virtue.system.service.impl;

import cn.haigle.virtue.system.entity.bo.AdminTreeBo;
import cn.haigle.virtue.system.entity.vo.AdminTreeVo;
import cn.haigle.virtue.system.dao.AdminUserDao;
import cn.haigle.virtue.system.entity.ao.AdminUserAO;
import cn.haigle.virtue.system.entity.dto.AdminUserDto;
import cn.haigle.virtue.system.entity.vo.AdminUserVo;
import cn.haigle.virtue.system.exception.EmailExistException;
import cn.haigle.virtue.system.exception.PhoneExistException;
import cn.haigle.virtue.system.exception.UserExistException;
import cn.haigle.virtue.system.service.AdminUserService;
import cn.haigle.virtue.common.annotation.transaction.Commit;
import cn.haigle.virtue.common.base.page.Page;
import cn.haigle.virtue.common.entity.query.NameQuery;
import cn.haigle.virtue.common.util.DesUtils;
import cn.haigle.virtue.common.util.SnowFlake;
import cn.haigle.virtue.common.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static cn.haigle.virtue.config.Constant.PASSWORD;

/**
 * 用户接口实现
 * @author haigle
 * @date 2019-06-09 13:56
 */
@Service("adminUserService")
public class AdminUserServiceImpl implements AdminUserService {

    @Resource
    private AdminUserDao adminUserDao;

    @Override
    public AdminUserVo info(Long uid) {
        return adminUserDao.getUserWithRoleMenuListById(uid);
    }

    @Override
    public Page<AdminUserVo> list(NameQuery adminSearchNameQuery) {
        Page<AdminUserVo> page = Page.<AdminUserVo>builder()
                .page(adminSearchNameQuery.getPage())
                .pageSize(adminSearchNameQuery.getPageSize())
                .build();
        page.setRows(adminUserDao.list(page, adminSearchNameQuery.getName()));
        page.setTotal(adminUserDao.getTotal(page, adminSearchNameQuery.getName()));
        return page;
    }

    /**
     * 采用了同步锁
     * xxx_ 的前缀是为了防止字符串中上锁顺序不同而产生死锁
     *
     * 解决方案对比 sync
     * 优：业务中非绝对串行，减少锁碰撞几率
     * 缺点：
     *      String.intern() 会进入常量池，过多会引起GC压力。 jdk 1.7以后 据说解决了这个问题
     *      代码不简洁
     * @param adminUserAO 用户信息
     * @param uid 操作用户ID
     * @author YL
     * @return ServiceResult
     */
    @Commit
    @Override
    public void save(AdminUserAO adminUserAO, Long uid) {
        synchronized (("name_" + adminUserAO.getUsername()).intern()) {
            synchronized (("phone_" + adminUserAO.getPhone()).intern()) {
                if (StringUtils.isBlank(adminUserAO.getEmail())) {
                    toSave(adminUserAO, uid);
                } else {
                    synchronized (("email_" + adminUserAO.getEmail()).intern()) {
                        toSave(adminUserAO, uid);
                    }
                }
            }
        }
    }

    @Commit
    @Override
    public void update(AdminUserAO adminUserAO, Long uid) {

        validatorUpdateUser(adminUserAO);
        adminUserDao.update(adminUserAO, uid);
        adminUserDao.deleteUserRole(adminUserAO.getId());
        if(!adminUserAO.getRoleList().isEmpty()) {
            adminUserDao.saveUserRole(adminUserAO.getId(), adminUserAO.getRoleList());
        }

    }

    @Override
    public void delete(Long id) {
        adminUserDao.delete(id);
        adminUserDao.deleteUserRole(id);
    }

    @Override
    public List<AdminTreeVo> roleAllList() {
        List<AdminTreeVo> adminTreeVoList = new ArrayList<>();
        List<AdminTreeBo> adminTreePoList = adminUserDao.getRoleAllList();
        for (AdminTreeBo a :  adminTreePoList) {
            adminTreeVoList.add(AdminTreeVo.builder()
                    .id(a.getId())
                    .name(a.getName()).build());
        }
        return adminTreeVoList;
    }

    @Override
    public List<Long> getUserRoleList(Long uid) {
        return adminUserDao.getUserRoleList(uid);
    }

    private void toSave(AdminUserAO adminUserAO, Long uid) {

        /*
         * 验证
         */
        validatorSaveUser(adminUserAO);
        Long userId = SnowFlake.getInstance();
        adminUserAO.setId(userId);
        String salt = StringUtils.getRandomStr(512);
        AdminUserDto adminUserDTO = AdminUserDto.builder()
                .id(userId)
                .username(adminUserAO.getUsername())
                .email(adminUserAO.getEmail())
                .phone(adminUserAO.getPhone())
                .gender(adminUserAO.getGender())
                .roleList(adminUserAO.getRoleList())
                .salt(salt)
                .password(DesUtils.encrypt(PASSWORD, salt)).build();
        adminUserDao.save(adminUserDTO, uid);
        if(!adminUserAO.getRoleList().isEmpty()) {
            adminUserDao.saveUserRole(userId, adminUserAO.getRoleList());
        }
    }

    /**
     * 验证
     * @param adminUserAo AdminUserAO
     * @author haigle
     * @date 2020/12/17 20:37
     */
    private void validatorSaveUser(AdminUserAO adminUserAo) {

        if(adminUserDao.getIsName(adminUserAo.getUsername()) != null) {
            throw new UserExistException();
        }
        if(adminUserDao.getIsPhone(adminUserAo.getPhone()) != null) {
            throw new PhoneExistException();
        }
        if(!adminUserAo.getEmail().isEmpty()) {
            if(adminUserDao.getIsEmail(adminUserAo.getEmail()) != null) {
                throw new EmailExistException();
            }
        }
    }

    private void validatorUpdateUser(AdminUserAO adminUserAO) {

        if(adminUserDao.getIsNameNotId(adminUserAO.getUsername(), adminUserAO.getId()) != null) {
            throw new UserExistException();
        }
        if(adminUserDao.getIsPhoneNotId(adminUserAO.getPhone(), adminUserAO.getId()) != null) {
            throw new PhoneExistException();
        }
        if(!adminUserAO.getEmail().isEmpty()) {
            if(adminUserDao.getIsEmailNotId(adminUserAO.getEmail(), adminUserAO.getId()) != null) {
                throw new EmailExistException();
            }
        }
    }
}
