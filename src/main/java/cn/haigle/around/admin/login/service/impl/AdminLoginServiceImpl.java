package cn.haigle.around.admin.login.service.impl;

import cn.haigle.around.admin.login.dao.AdminLoginDao;
import cn.haigle.around.admin.login.dao.AdminPaiDao;
import cn.haigle.around.admin.login.entity.ao.AdminLoginAO;
import cn.haigle.around.admin.login.entity.ao.AdminRegisterAO;
import cn.haigle.around.admin.login.entity.bo.AdminRegisterBO;
import cn.haigle.around.admin.login.entity.bo.AdminUserInfoBO;
import cn.haigle.around.admin.login.entity.bo.AdminUserLoginBO;
import cn.haigle.around.admin.login.entity.vo.AdminUserAndRolesVO;
import cn.haigle.around.admin.login.entity.vo.LoginUserInfoVo;
import cn.haigle.around.admin.login.exception.NoPermissionAccessException;
import cn.haigle.around.admin.login.exception.PasswordErrorException;
import cn.haigle.around.admin.login.exception.UserNotExistException;
import cn.haigle.around.admin.login.service.AdminLoginService;
import cn.haigle.around.admin.login.service.AdminUserPermissionCacheService;
import cn.haigle.around.common.annotation.transaction.Commit;
import cn.haigle.around.common.util.DesUtils;
import cn.haigle.around.common.util.JwtUtils;
import cn.haigle.around.common.util.SnowFlake;
import cn.haigle.around.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

import static cn.haigle.around.common.util.AccountValidatorUtils.REGEX_MOBILE;
import static cn.haigle.around.common.util.AccountValidatorUtils.REGEX_EMAIL;

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

    @Autowired
    JavaMailSenderImpl mailSender;

    @Resource(name = "adminPaiDao")
    private AdminPaiDao adminPaiDao;

    @Override
    public LoginUserInfoVo login(AdminLoginAO ao) {

        AdminUserLoginBO user;

        if(ao.getAccount().matches(REGEX_MOBILE)) {
            user = adminLoginDao.getUserByPhone(ao.getAccount());
        }else if(ao.getAccount().matches(REGEX_EMAIL)) {
            user = adminLoginDao.getUserByEmail(ao.getAccount());
        } else {
            user = adminLoginDao.getUserByUserName(ao.getAccount());
        }

        if(user == null) {
            throw new UserNotExistException();
        }

        String inputPassword = ao.getPassword();
        String userPassword = DesUtils.decrypt(user.getPassword(), user.getSalt());
        if(!inputPassword.equals(userPassword)) {
            throw new PasswordErrorException();
        }

        Set<String> permissions = adminUserPermissionCacheService.get(user.getId());
        if (permissions == null) {
            throw new NoPermissionAccessException();
        }

        return new LoginUserInfoVo(JwtUtils.sign(user.getId().toString()));

    }

    @Override
    public AdminUserAndRolesVO getAdminUserAndRoles(Long uid) {
        AdminUserInfoBO userInfo = adminLoginDao.getAdminUserInfo(uid);
        return new AdminUserAndRolesVO()
                .setUsername(userInfo.getUsername())
                .setEmail(userInfo.getEmail())
                .setPhone(userInfo.getPhone())
                .setAvatar(userInfo.getAvatar())
                .setBirth(userInfo.getBirth())
                .setIntroduction(userInfo.getIntroduction())
                .setRoles(adminUserPermissionCacheService.get(uid));
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
        message.setSubject("AROUND注册验证码");
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
    public String save(AdminRegisterAO adminLoginAo) {
        AdminRegisterBO adminRegisterPo = new AdminRegisterBO();
        Long uid = SnowFlake.getInstance();
        adminRegisterPo.setId(uid);
        adminRegisterPo.setEmail(adminLoginAo.getEmail());
        adminRegisterPo.setSalt(StringUtils.getRandomStr(512));
        adminRegisterPo.setPassword(DesUtils.encrypt(adminLoginAo.getPassword(), adminRegisterPo.getSalt()));
        adminLoginDao.save(adminRegisterPo);
        return JwtUtils.sign(uid.toString());
    }
}
