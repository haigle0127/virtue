package cn.haigle.virtue.service.login;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import cn.haigle.virtue.common.interceptor.exception.ServiceException;
import cn.haigle.virtue.common.interceptor.exception.enums.CodeConstants;
import cn.haigle.virtue.controller.admin.login.vo.LoginReqVO;
import cn.haigle.virtue.mapper.dataobject.user.SysUserDO;
import cn.haigle.virtue.mapper.mysql.user.UserMapper;
import cn.hutool.core.lang.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static cn.haigle.virtue.common.web.Constant.PASSWORD_KEY;

/**
 * 登录注册 服务实现
 * @author haigle
 * @date 2019/6/21 10:07
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String login(LoginReqVO reqVO) {
        SysUserDO sysUserDO;
        if(Validator.isEmail(reqVO.getAccount())) {
            sysUserDO = userMapper.selectOne(SysUserDO::getEmail, reqVO.getAccount());
        }else if(Validator.isMobile(reqVO.getAccount())) {
            sysUserDO = userMapper.selectOne(SysUserDO::getPhone, reqVO.getAccount());
        }else {
            sysUserDO = userMapper.selectOne(SysUserDO::getUsername, reqVO.getAccount());
        }
        if(sysUserDO == null) {
            throw new ServiceException(CodeConstants.USER_ERROR);
        }
        if(!reqVO.getPassword().equals(SaSecureUtil.aesDecrypt(PASSWORD_KEY, sysUserDO.getPassword()))){
            throw new ServiceException(CodeConstants.USER_ERROR);
        }
        StpUtil.login(sysUserDO.getId());
        return StpUtil.getTokenValue();
    }

    @Override
    public List<String> getPermission(Long userId) {
        return List.of();
    }

    @Override
    public boolean emailIsExist(String email) {
        return false;
    }

    @Override
    public void sendEmailCode(String email) {

    }
}
