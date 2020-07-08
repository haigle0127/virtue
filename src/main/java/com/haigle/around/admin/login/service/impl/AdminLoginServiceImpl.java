package com.haigle.around.admin.login.service.impl;

import com.haigle.around.admin.login.dao.AdminLoginDao;
import com.haigle.around.common.interceptor.annotation.Commit;
import com.haigle.around.admin.login.entity.query.AdminLoginQuery;
import com.haigle.around.admin.login.entity.ao.AdminRegisterAO;
import com.haigle.around.admin.login.entity.bo.AdminRegisterBO;
import com.haigle.around.admin.login.entity.bo.AdminUserLoginBO;
import com.haigle.around.admin.login.service.AdminLoginService;
import com.haigle.around.admin.login.dao.AdminPaiDao;
import com.haigle.around.common.util.DesUtils;
import com.haigle.around.common.util.JwtUtils;
import com.haigle.around.common.util.SnowFlake;
import com.haigle.around.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.haigle.around.common.util.AccountValidatorUtils.REGEX_EMAIL;
import static com.haigle.around.common.util.AccountValidatorUtils.REGEX_MOBILE;

/**
 * 登录注册 服务实现
 * @author haigle
 * @date 2019/6/21 10:07
 */
@Service("adminLoginService")
public class AdminLoginServiceImpl implements AdminLoginService {

    @Resource(name = "adminLoginDao")
    private AdminLoginDao adminLoginDao;

    @Autowired
    JavaMailSenderImpl mailSender;

    @Resource(name = "adminPaiDao")
    private AdminPaiDao adminPaiDao;

    @Override
    public AdminUserLoginBO login(AdminLoginQuery adminLoginAo) {
        if(adminLoginAo.getAccount().matches(REGEX_EMAIL)) {
            return adminLoginDao.getUserByEmail(adminLoginAo);
        }
        if(adminLoginAo.getAccount().matches(REGEX_MOBILE)) {
            return adminLoginDao.getUserByPhone(adminLoginAo);
        }
        return adminLoginDao.getUserByUserName(adminLoginAo);
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
