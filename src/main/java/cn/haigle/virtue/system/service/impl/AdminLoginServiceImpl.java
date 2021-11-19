package cn.haigle.virtue.system.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.haigle.virtue.system.dao.AdminLoginDao;
import cn.haigle.virtue.system.dao.AdminPaiDao;
import cn.haigle.virtue.system.entity.ao.AdminLoginAo;
import cn.haigle.virtue.system.entity.ao.AdminRegisterAo;
import cn.haigle.virtue.system.entity.bo.AdminRegisterBo;
import cn.haigle.virtue.system.entity.bo.AdminUserInfoBo;
import cn.haigle.virtue.system.entity.bo.AdminUserLoginBo;
import cn.haigle.virtue.system.entity.vo.AdminUserAndRolesVo;
import cn.haigle.virtue.system.entity.vo.LoginUserInfoVo;
import cn.haigle.virtue.system.exception.PasswordErrorException;
import cn.haigle.virtue.system.exception.UserNotExistException;
import cn.haigle.virtue.system.service.AdminLoginService;
import cn.haigle.virtue.system.service.AdminUserPermissionCacheService;
import cn.haigle.virtue.common.annotation.transaction.Commit;
import cn.haigle.virtue.common.util.DesUtils;
import cn.haigle.virtue.common.util.SnowFlake;
import cn.haigle.virtue.common.util.StringUtils;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static cn.haigle.virtue.common.util.AccountValidatorUtils.REGEX_MOBILE;
import static cn.haigle.virtue.common.util.AccountValidatorUtils.REGEX_EMAIL;

/**
 * 登录注册 服务实现
 * @author haigle
 * @date 2019/6/21 10:07
 */
@Service("adminLoginService")
public class AdminLoginServiceImpl implements AdminLoginService {

    @Resource(name = "adminUserPermissionCacheService")
    private AdminUserPermissionCacheService adminUserPermissionCacheService;

    @Resource(name = "adminLoginDao")
    private AdminLoginDao adminLoginDao;


    private JavaMailSenderImpl mailSender;

    private void mailSender(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }

    @Resource(name = "adminPaiDao")
    private AdminPaiDao adminPaiDao;

    @Override
    public LoginUserInfoVo login(AdminLoginAo ao) {

        AdminUserLoginBo user;

        if(ao.getAccount().matches(REGEX_MOBILE)) {
            user = adminLoginDao.getUserByPhone(ao.getAccount());
        }else if(ao.getAccount().matches(REGEX_EMAIL)) {
            user = adminLoginDao.getUserByEmail(ao.getAccount());
        } else {
            user = adminLoginDao.getUserByUsername(ao.getAccount());
        }

        if(user == null) {
            throw new UserNotExistException();
        }

        String inputPassword = ao.getPassword();
        String userPassword = DesUtils.decrypt(user.getPassword(), user.getSalt());
        if(!inputPassword.equals(userPassword)) {
            throw new PasswordErrorException();
        }

        StpUtil.login(user.getId());
        return new LoginUserInfoVo(StpUtil.getTokenValue());

    }

    @Override
    public List<String> getPermission(Long userId) {
        return adminUserPermissionCacheService.get(userId);
    }

    @Override
    public AdminUserAndRolesVo getAdminUserAndRoles(Long uid) {
        AdminUserInfoBo userInfo = adminLoginDao.getAdminUserInfo(uid);
        return new AdminUserAndRolesVo()
                .setUsername(userInfo.getUsername())
                .setEmail(userInfo.getEmail())
                .setPhone(userInfo.getPhone())
                .setAvatar(userInfo.getAvatar())
                .setBirth(userInfo.getBirth())
                .setIntroduction(userInfo.getIntroduction())
                .setRoles(adminLoginDao.findRolesById(uid))
                .setPermissions(adminUserPermissionCacheService.get(uid));
    }

    @Override
    public boolean emailIsExist(String email) {
        return adminLoginDao.getIsEmail(email) != null;
    }

    @Override
    public void sendEmailCode(String email) {

        SimpleMailMessage message = new SimpleMailMessage();

        /*
         * 邮箱设置
         */
        int code = ((int)((Math.random()*9+1)*1000000));
        message.setSubject("VIRTUE注册验证码");
        message.setText("您的验证码是 "+ code + " 请不要告诉其他人哟");

        /*
         * 发送给谁
         */
        message.setTo(email);

        /*
         * 来自邮箱
         */
        message.setFrom("940121265@qq.com");
        mailSender.send(message);

        adminPaiDao.delete(email);
        adminPaiDao.save(email, String.valueOf(code));
    }

    @Commit
    @Override
    public void save(AdminRegisterAo adminLoginAo) {
        AdminRegisterBo adminRegisterPo = new AdminRegisterBo();
        Long uid = SnowFlake.getInstance();
        adminRegisterPo.setId(uid);
        adminRegisterPo.setEmail(adminLoginAo.getEmail());
        adminRegisterPo.setSalt(StringUtils.getRandomStr(512));
        adminRegisterPo.setPassword(DesUtils.encrypt(adminLoginAo.getPassword(), adminRegisterPo.getSalt()));
        adminLoginDao.save(adminRegisterPo);;
    }
}
