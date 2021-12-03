package cn.haigle.virtue.system.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.haigle.virtue.system.dao.LoginDao;
import cn.haigle.virtue.system.dao.PaiDao;
import cn.haigle.virtue.system.entity.ao.LoginAo;
import cn.haigle.virtue.system.entity.ao.RegisterAo;
import cn.haigle.virtue.system.entity.bo.RegisterBo;
import cn.haigle.virtue.system.entity.bo.SysUserBo;
import cn.haigle.virtue.system.entity.bo.UserInfoBo;
import cn.haigle.virtue.system.entity.bo.UserLoginBo;
import cn.haigle.virtue.system.entity.vo.UserAndRolesVo;
import cn.haigle.virtue.system.entity.vo.LoginUserInfoVo;
import cn.haigle.virtue.system.exception.PasswordErrorException;
import cn.haigle.virtue.system.exception.UserNotExistException;
import cn.haigle.virtue.system.repository.UserRepository;
import cn.haigle.virtue.system.service.LoginService;
import cn.haigle.virtue.system.service.UserPermissionCacheService;
import cn.haigle.virtue.common.annotation.transaction.Commit;
import cn.haigle.virtue.common.util.DesUtils;
import cn.haigle.virtue.common.util.SnowFlake;
import cn.haigle.virtue.common.util.StringUtils;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

import static cn.haigle.virtue.common.util.AccountValidatorUtils.REGEX_MOBILE;
import static cn.haigle.virtue.common.util.AccountValidatorUtils.REGEX_EMAIL;

/**
 * 登录注册 服务实现
 * @author haigle
 * @date 2019/6/21 10:07
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {

    @Resource(name = "userPermissionCacheService")
    private UserPermissionCacheService userPermissionCacheService;

    @Resource(name = "loginDao")
    private LoginDao loginDao;

    @Resource(name = "userRepository")
    private UserRepository userRepository;


    private JavaMailSenderImpl mailSender;

    private void mailSender(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }

    @Resource(name = "paiDao")
    private PaiDao paiDao;

    @Override
    public LoginUserInfoVo login(LoginAo ao) {

//        Optional<SysUserBo> optional = userRepository.findByUsernameOrPhoneOrEmail(ao.getAccount());
//        if(!optional.isPresent()) {
//            throw new UserNotExistException();
//        }
//        optional.filter(user -> {
//            String inputPassword = ao.getPassword();
//            String userPassword = DesUtils.decrypt(user.getPassword(), user.getSalt());
//            return inputPassword.equals(userPassword);
//        }).map(user -> {
//
//        })








        UserLoginBo user;

        if(ao.getAccount().matches(REGEX_MOBILE)) {
            user = loginDao.getUserByPhone(ao.getAccount());
        }else if(ao.getAccount().matches(REGEX_EMAIL)) {
            user = loginDao.getUserByEmail(ao.getAccount());
        } else {
            user = loginDao.getUserByUsername(ao.getAccount());
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
        return userPermissionCacheService.get(userId);
    }

    @Override
    public UserAndRolesVo getUserAndRoles(Long uid) {
        UserInfoBo userInfo = loginDao.getAdminUserInfo(uid);
        return new UserAndRolesVo()
                .setUsername(userInfo.getUsername())
                .setEmail(userInfo.getEmail())
                .setPhone(userInfo.getPhone())
                .setAvatar(userInfo.getAvatar())
                .setBirth(userInfo.getBirth())
                .setIntroduction(userInfo.getIntroduction())
                .setRoles(loginDao.findRolesById(uid))
                .setPermissions(userPermissionCacheService.get(uid));
    }

    @Override
    public boolean emailIsExist(String email) {
        return loginDao.getIsEmail(email) != null;
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

        paiDao.delete(email);
        paiDao.save(email, String.valueOf(code));
    }

    @Commit
    @Override
    public void save(RegisterAo adminLoginAo) {
        RegisterBo adminRegisterPo = new RegisterBo();
        Long uid = SnowFlake.getInstance();
        adminRegisterPo.setId(uid);
        adminRegisterPo.setEmail(adminLoginAo.getEmail());
        adminRegisterPo.setSalt(StringUtils.getRandomStr(512));
        adminRegisterPo.setPassword(DesUtils.encrypt(adminLoginAo.getPassword(), adminRegisterPo.getSalt()));
        loginDao.save(adminRegisterPo);;
    }
}
