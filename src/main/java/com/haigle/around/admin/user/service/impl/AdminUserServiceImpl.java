package com.haigle.around.admin.user.service.impl;

import com.haigle.around.common.entity.query.AdminSearchNameQuery;
import com.haigle.around.admin.user.entity.ao.AdminUserAO;
import com.haigle.around.admin.user.entity.dto.AdminUserDTO;
import com.haigle.around.admin.menu.entity.bo.AdminTreeBO;
import com.haigle.around.admin.menu.entity.vo.AdminTreeVO;
import com.haigle.around.admin.user.entity.vo.AdminUserVo;
import com.haigle.around.admin.user.dao.AdminUserDao;
import com.haigle.around.admin.user.service.AdminUserService;
import com.haigle.around.common.base.page.Page;
import com.haigle.around.common.interceptor.annotation.Commit;
import com.haigle.around.common.interceptor.model.service.ServiceResult;
import com.haigle.around.common.util.DesUtils;
import com.haigle.around.common.util.SnowFlake;
import com.haigle.around.common.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static com.haigle.around.config.Constant.PASSWORD;

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
    public Page<AdminUserVo> list(AdminSearchNameQuery adminSearchNameQuery) {
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
    public ServiceResult save(AdminUserAO adminUserAO, Long uid) {
        synchronized (("name_" + adminUserAO.getUsername()).intern()) {
            synchronized (("phone_" + adminUserAO.getPhone()).intern()) {
                if (StringUtils.isBlank(adminUserAO.getEmail())) {
                    return toSave(adminUserAO, uid);
                } else {
                    synchronized (("email_" + adminUserAO.getEmail()).intern()) {
                        return toSave(adminUserAO, uid);
                    }
                }
            }
        }
    }

    @Commit
    @Override
    public ServiceResult update(AdminUserAO adminUserAO, Long uid) {

        ServiceResult serviceResult = validatorUpdateUser(adminUserAO);
        if(!serviceResult.isSuccess()) {
            return serviceResult;
        }
        adminUserDao.update(adminUserAO, uid);
        adminUserDao.deleteUserRole(adminUserAO.getId());
        if(!adminUserAO.getRoleList().isEmpty()) {
            adminUserDao.saveUserRole(adminUserAO.getId(), adminUserAO.getRoleList());
        }
        return new ServiceResult("common.success", true);

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

    private ServiceResult toSave(AdminUserAO adminUserAO, Long uid) {
        ServiceResult serviceResult = validatorSaveUser(adminUserAO);
        if(!serviceResult.isSuccess()) {
            return serviceResult;
        }
        Long userId = SnowFlake.getInstance();
        adminUserAO.setId(userId);
        String salt = StringUtils.getRandomStr(512);
        AdminUserDTO adminUserDto = AdminUserDTO.builder()
                .id(userId)
                .username(adminUserAO.getUsername())
                .email(adminUserAO.getEmail())
                .phone(adminUserAO.getPhone())
                .gender(adminUserAO.getGender())
                .roleList(adminUserAO.getRoleList())
                .salt(salt)
                .password(DesUtils.encrypt(PASSWORD, salt)).build();
        adminUserDao.save(adminUserDto, uid);
        if(!adminUserAO.getRoleList().isEmpty()) {
            adminUserDao.saveUserRole(userId, adminUserAO.getRoleList());
        }
        return new ServiceResult("common.success", true);
    }

    private ServiceResult validatorSaveUser(AdminUserAO adminUserAo) {

        if(adminUserDao.getIsName(adminUserAo.getUsername()) != null) {
            return new ServiceResult("user.username.exist", false);
        }
        if(adminUserDao.getIsPhone(adminUserAo.getPhone()) != null) {
            return new ServiceResult("user.phone.exist", false);
        }
        if(!adminUserAo.getEmail().isEmpty()) {
            if(adminUserDao.getIsEmail(adminUserAo.getEmail()) != null) {
                return new ServiceResult("user.email.exist", false);
            }
        }
        return new ServiceResult("common.success", true);

    }

    private ServiceResult validatorUpdateUser(AdminUserAO adminUserAO) {

        if(adminUserDao.getIsNameNotId(adminUserAO.getUsername(), adminUserAO.getId()) != null) {
            return new ServiceResult("user.username.exist", false);
        }
        if(adminUserDao.getIsPhoneNotId(adminUserAO.getPhone(), adminUserAO.getId()) != null) {
            return new ServiceResult("user.phone.exist", false);
        }
        if(!adminUserAO.getEmail().isEmpty()) {
            if(adminUserDao.getIsEmailNotId(adminUserAO.getEmail(), adminUserAO.getId()) != null) {
                return new ServiceResult("user.email.exist", false);
            }
        }
        return new ServiceResult("common.success", true);

    }
}
