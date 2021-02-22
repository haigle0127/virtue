package cn.haigle.around.admin.user.service.impl;

import cn.haigle.around.admin.menu.entity.bo.AdminTreeBO;
import cn.haigle.around.admin.menu.entity.vo.AdminTreeVO;
import cn.haigle.around.admin.user.dao.AdminUserDao;
import cn.haigle.around.admin.user.entity.ao.AdminUserAO;
import cn.haigle.around.admin.user.entity.dto.AdminUserDTO;
import cn.haigle.around.admin.user.entity.vo.AdminUserVO;
import cn.haigle.around.admin.user.exception.EmailExistException;
import cn.haigle.around.admin.user.exception.PhoneExistException;
import cn.haigle.around.admin.user.exception.UserExistException;
import cn.haigle.around.admin.user.service.AdminUserService;
import cn.haigle.around.common.annotation.transaction.Commit;
import cn.haigle.around.common.base.page.Page;
import cn.haigle.around.common.entity.query.NameQuery;
import cn.haigle.around.common.util.DesUtils;
import cn.haigle.around.common.util.SnowFlake;
import cn.haigle.around.common.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static cn.haigle.around.config.Constant.PASSWORD;

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
    public AdminUserVO info(Long uid) {
        return adminUserDao.getUserWithRoleMenuListById(uid);
    }

    @Override
    public Page<AdminUserVO> list(NameQuery adminSearchNameQuery) {
        Page<AdminUserVO> page = Page.<AdminUserVO>builder()
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
    public List<AdminTreeVO> roleAllList() {
        List<AdminTreeVO> adminTreeVoList = new ArrayList<>();
        List<AdminTreeBO> adminTreePoList = adminUserDao.getRoleAllList();
        for (AdminTreeBO a :  adminTreePoList) {
            adminTreeVoList.add(AdminTreeVO.builder()
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
        AdminUserDTO adminUserDTO = AdminUserDTO.builder()
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
