package cn.haigle.virtue.system.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.haigle.virtue.common.interceptor.exception.NoPermissionAccessException;
import cn.haigle.virtue.system.dao.LoginDao;
import cn.haigle.virtue.system.dao.PaiDao;
import cn.haigle.virtue.system.entity.ao.LoginAo;
import cn.haigle.virtue.system.entity.ao.RegisterAo;
import cn.haigle.virtue.system.entity.bo.RegisterBo;
import cn.haigle.virtue.system.entity.bo.SysUserBo;
import cn.haigle.virtue.system.entity.vo.Menu;
import cn.haigle.virtue.system.entity.vo.UserInfoVo;
import cn.haigle.virtue.system.entity.vo.LoginUserInfoVo;
import cn.haigle.virtue.system.exception.PasswordErrorException;
import cn.haigle.virtue.system.exception.UserNotExistException;
import cn.haigle.virtue.system.repository.RoleRepository;
import cn.haigle.virtue.system.repository.UserRepository;
import cn.haigle.virtue.system.service.LoginService;
import cn.haigle.virtue.system.service.UserPowerCacheService;
import cn.haigle.virtue.common.annotation.transaction.Commit;
import cn.haigle.virtue.common.util.DesUtils;
import cn.haigle.virtue.common.util.SnowFlake;
import cn.haigle.virtue.common.util.StringUtils;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.DES;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * 登录注册 服务实现
 * @author haigle
 * @date 2019/6/21 10:07
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {

    @Resource(name = "userPowerCacheService")
    private UserPowerCacheService userPowerCacheService;

    @Resource(name = "loginDao")
    private LoginDao loginDao;

    @Resource(name = "userRepository")
    private UserRepository userRepository;

    @Resource(name = "roleRepository")
    private RoleRepository roleRepository;


    private JavaMailSenderImpl mailSender;

    private void mailSender(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }

    @Resource(name = "paiDao")
    private PaiDao paiDao;

    @Override
    public LoginUserInfoVo login(LoginAo ao) {
        Optional<SysUserBo> optional = userRepository.findByUsernameOrPhoneOrEmail(ao.getAccount());
        if(!optional.isPresent()) {
            throw new UserNotExistException();
        }
        return optional.filter(user -> {
            //校验密码是否一样
            DES desSalt = SecureUtil.des(Base64.decode(user.getSalt()));
            String userPassword = desSalt.decryptStr(user.getPassword());
            return CharSequenceUtil.equals(new String(Base64.decode(ao.getPassword())), userPassword);
        }).map(user -> {
            List<Menu> permissions = userPowerCacheService.saveOrGet(user.getId());
            if (permissions != null && !permissions.isEmpty()) {
                StpUtil.login(user.getId());
                return new LoginUserInfoVo(StpUtil.getTokenValue());
            }else {
                throw new NoPermissionAccessException();
            }
        }).orElseThrow(PasswordErrorException::new);
    }

    @Override
    public UserInfoVo userInfo(Long uid) {

        return userRepository.findById(uid)
                .map(user -> {
                    List<String> roles = roleRepository.selectRoleName(uid);
                    return new UserInfoVo()
                            .setUsername(user.getUsername())
                            .setEmail(user.getEmail())
                            .setPhone(user.getPhone())
                            .setAvatar(user.getAvatar())
                            .setBirth(user.getBirth())
                            .setIntroduction(user.getIntroduction())
                            .setRoles(loginDao.findRolesById(uid));
                }).orElseThrow(UserNotExistException::new);
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
