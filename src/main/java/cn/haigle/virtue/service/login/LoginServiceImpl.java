package cn.haigle.virtue.service.login;

import cn.haigle.virtue.common.enums.LoginTypeEnum;
import cn.haigle.virtue.common.interceptor.exception.ServiceException;
import cn.haigle.virtue.common.interceptor.exception.enums.CodeConstants;
import cn.haigle.virtue.common.security.jwt.JwtUtil;
import cn.haigle.virtue.controller.admin.login.vo.LoginReqVO;
import cn.haigle.virtue.mapper.dataobject.user.SysUserDO;
import cn.haigle.virtue.mapper.mysql.user.UserMapper;
import cn.hutool.core.lang.Validator;
import cn.hutool.crypto.symmetric.AES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static cn.haigle.virtue.common.web.Constant.PASSWORD_KEY;

/**
 * 登录注册 服务实现
 * <p>支持不同登录类型，各类型可对应不同用户表或同一张表的不同逻辑</p>
 *
 * @author haigle
 * @date 2019/6/21 10:07
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String login(LoginReqVO reqVO, String loginType) {
        LoginTypeEnum typeEnum = LoginTypeEnum.fromCode(loginType);
        if (typeEnum == null) {
            throw new ServiceException("无效的登录类型");
        }

        // 根据登录类型走不同的查询逻辑，后续可扩展为不同用户表
        SysUserDO sysUserDO = findUser(reqVO, typeEnum);
        if (sysUserDO == null) {
            throw new ServiceException(CodeConstants.USER_ERROR);
        }

        AES aes = new AES(PASSWORD_KEY.getBytes());
        if (!reqVO.getPassword().equals(aes.decryptStr(sysUserDO.getPassword()))) {
            throw new ServiceException(CodeConstants.USER_ERROR);
        }

        return JwtUtil.generateToken(sysUserDO.getId(), typeEnum.getCode());
    }

    /**
     * 根据登录类型查找用户
     * <p>目前各类型共用 sys_user 表，后续需要扩展时在此方法中按类型查询不同表即可</p>
     */
    private SysUserDO findUser(LoginReqVO reqVO, LoginTypeEnum loginType) {
        // 目前所有登录类型共用 sys_user 表，后续可在此按 loginType 切换不同 Mapper
        if (Validator.isEmail(reqVO.getAccount())) {
            return userMapper.selectOne(SysUserDO::getEmail, reqVO.getAccount());
        } else if (Validator.isMobile(reqVO.getAccount())) {
            return userMapper.selectOne(SysUserDO::getPhone, reqVO.getAccount());
        } else {
            return userMapper.selectOne(SysUserDO::getUsername, reqVO.getAccount());
        }
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
