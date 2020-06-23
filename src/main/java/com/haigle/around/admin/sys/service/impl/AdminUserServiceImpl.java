package com.haigle.around.admin.sys.service.impl;

import com.haigle.around.admin.sys.entity.query.AdminSearchNameQuery;
import com.haigle.around.admin.sys.entity.ao.AdminUserAo;
import com.haigle.around.admin.sys.entity.dto.AdminUserDto;
import com.haigle.around.admin.sys.entity.po.AdminTreePo;
import com.haigle.around.admin.sys.entity.vo.AdminTreeVo;
import com.haigle.around.admin.sys.entity.vo.AdminUserVo;
import com.haigle.around.admin.sys.dao.AdminUserDao;
import com.haigle.around.admin.sys.service.AdminUserService;
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
     * @param adminUserAo 用户信息
     * @param uid 操作用户ID
     * @author YL
     * @return ServiceResult
     */
    @Commit
    @Override
    public ServiceResult save(AdminUserAo adminUserAo, Long uid) {
        synchronized (("name_" + adminUserAo.getUsername()).intern()) {
            synchronized (("phone_" + adminUserAo.getPhone()).intern()) {
                if (StringUtils.isBlank(adminUserAo.getEmail())) {
                    return toSave(adminUserAo, uid);
                } else {
                    synchronized (("email_" + adminUserAo.getEmail()).intern()) {
                        return toSave(adminUserAo, uid);
                    }
                }
            }
        }
    }

    @Commit
    @Override
    public ServiceResult update(AdminUserAo adminUserAo, Long uid) {

        ServiceResult serviceResult = validatorUpdateUser(adminUserAo);
        if(!serviceResult.isSuccess()) {
            return serviceResult;
        }
        adminUserDao.update(adminUserAo, uid);
        adminUserDao.deleteUserRole(adminUserAo.getId());
        if(!adminUserAo.getRoleList().isEmpty()) {
            adminUserDao.saveUserRole(adminUserAo.getId(), adminUserAo.getRoleList());
        }
        return new ServiceResult("common.success", true);

    }

    @Override
    public void delete(Long id) {
        adminUserDao.delete(id);
        adminUserDao.deleteUserRole(id);
    }

    @Override
    public List<AdminTreeVo> roleAllList() {
        List<AdminTreeVo> adminTreeVoList = new ArrayList<>();
        List<AdminTreePo> adminTreePoList = adminUserDao.getRoleAllList();
        for (AdminTreePo a :  adminTreePoList) {
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

    private ServiceResult toSave(AdminUserAo adminUserAo, Long uid) {
        ServiceResult serviceResult = validatorSaveUser(adminUserAo);
        if(!serviceResult.isSuccess()) {
            return serviceResult;
        }
        Long userId = SnowFlake.getInstance();
        adminUserAo.setId(userId);
        String salt = StringUtils.getRandomStr(512);
        AdminUserDto adminUserDto = AdminUserDto.builder()
                .id(userId)
                .username(adminUserAo.getUsername())
                .email(adminUserAo.getEmail())
                .phone(adminUserAo.getPhone())
                .gender(adminUserAo.getGender())
                .roleList(adminUserAo.getRoleList())
                .salt(salt)
                .password(DesUtils.encrypt(PASSWORD, salt)).build();
        adminUserDao.save(adminUserDto, uid);
        if(!adminUserAo.getRoleList().isEmpty()) {
            adminUserDao.saveUserRole(userId, adminUserAo.getRoleList());
        }
        return new ServiceResult("common.success", true);
    }

    private ServiceResult validatorSaveUser(AdminUserAo adminUserAo) {

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

    private ServiceResult validatorUpdateUser(AdminUserAo adminUserAo) {

        if(adminUserDao.getIsNameNotId(adminUserAo.getUsername(), adminUserAo.getId()) != null) {
            return new ServiceResult("user.username.exist", false);
        }
        if(adminUserDao.getIsPhoneNotId(adminUserAo.getPhone(), adminUserAo.getId()) != null) {
            return new ServiceResult("user.phone.exist", false);
        }
        if(!adminUserAo.getEmail().isEmpty()) {
            if(adminUserDao.getIsEmailNotId(adminUserAo.getEmail(), adminUserAo.getId()) != null) {
                return new ServiceResult("user.email.exist", false);
            }
        }
        return new ServiceResult("common.success", true);

    }
}
