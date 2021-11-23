package cn.haigle.virtue.system.service.impl;

import cn.haigle.virtue.system.entity.bo.TreeBo;
import cn.haigle.virtue.system.entity.vo.TreeVo;
import cn.haigle.virtue.system.dao.UserDao;
import cn.haigle.virtue.system.entity.ao.UserAo;
import cn.haigle.virtue.system.entity.dto.UserDto;
import cn.haigle.virtue.system.entity.vo.UserVo;
import cn.haigle.virtue.system.exception.EmailExistException;
import cn.haigle.virtue.system.exception.PhoneExistException;
import cn.haigle.virtue.system.exception.UserExistException;
import cn.haigle.virtue.system.service.UserService;
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
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public UserVo info(Long uid) {
        return userDao.getUserWithRoleMenuListById(uid);
    }

    @Override
    public Page<UserVo> list(NameQuery adminSearchNameQuery) {
        Page<UserVo> page = Page.<UserVo>builder()
                .page(adminSearchNameQuery.getPage())
                .pageSize(adminSearchNameQuery.getPageSize())
                .build();
        page.setRows(userDao.list(page, adminSearchNameQuery.getName()));
        page.setTotal(userDao.getTotal(page, adminSearchNameQuery.getName()));
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
     * @param userAo 用户信息
     * @param uid 操作用户ID
     * @author YL
     */
    @Commit
    @Override
    public void save(UserAo userAo, Long uid) {
        synchronized (("name_" + userAo.getUsername()).intern()) {
            synchronized (("phone_" + userAo.getPhone()).intern()) {
                if (StringUtils.isBlank(userAo.getEmail())) {
                    toSave(userAo, uid);
                } else {
                    synchronized (("email_" + userAo.getEmail()).intern()) {
                        toSave(userAo, uid);
                    }
                }
            }
        }
    }

    @Commit
    @Override
    public void update(UserAo userAo, Long uid) {

        validatorUpdateUser(userAo);
        userDao.update(userAo, uid);
        userDao.deleteUserRole(userAo.getId());
        if(!userAo.getRoleList().isEmpty()) {
            userDao.saveUserRole(userAo.getId(), userAo.getRoleList());
        }

    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
        userDao.deleteUserRole(id);
    }

    @Override
    public List<TreeVo> roleAllList() {
        List<TreeVo> treeVoList = new ArrayList<>();
        List<TreeBo> adminTreePoList = userDao.getRoleAllList();
        for (TreeBo a :  adminTreePoList) {
            treeVoList.add(TreeVo.builder()
                    .id(a.getId())
                    .name(a.getName()).build());
        }
        return treeVoList;
    }

    @Override
    public List<Long> getUserRoleList(Long uid) {
        return userDao.getUserRoleList(uid);
    }

    private void toSave(UserAo userAo, Long uid) {

        /*
         * 验证
         */
        validatorSaveUser(userAo);
        Long userId = SnowFlake.getInstance();
        userAo.setId(userId);
        String salt = StringUtils.getRandomStr(512);
        UserDto userDTO = UserDto.builder()
                .id(userId)
                .username(userAo.getUsername())
                .email(userAo.getEmail())
                .phone(userAo.getPhone())
                .gender(userAo.getGender())
                .roleList(userAo.getRoleList())
                .salt(salt)
                .password(DesUtils.encrypt(PASSWORD, salt)).build();
        userDao.save(userDTO, uid);
        if(!userAo.getRoleList().isEmpty()) {
            userDao.saveUserRole(userId, userAo.getRoleList());
        }
    }

    /**
     * 验证
     * @param userAo AdminUserAO
     * @author haigle
     * @date 2020/12/17 20:37
     */
    private void validatorSaveUser(UserAo userAo) {

        if(userDao.getIsName(userAo.getUsername()) != null) {
            throw new UserExistException();
        }
        if(userDao.getIsPhone(userAo.getPhone()) != null) {
            throw new PhoneExistException();
        }
        if(!userAo.getEmail().isEmpty()) {
            if(userDao.getIsEmail(userAo.getEmail()) != null) {
                throw new EmailExistException();
            }
        }
    }

    private void validatorUpdateUser(UserAo userAo) {

        if(userDao.getIsNameNotId(userAo.getUsername(), userAo.getId()) != null) {
            throw new UserExistException();
        }
        if(userDao.getIsPhoneNotId(userAo.getPhone(), userAo.getId()) != null) {
            throw new PhoneExistException();
        }
        if(!userAo.getEmail().isEmpty()) {
            if(userDao.getIsEmailNotId(userAo.getEmail(), userAo.getId()) != null) {
                throw new EmailExistException();
            }
        }
    }
}
