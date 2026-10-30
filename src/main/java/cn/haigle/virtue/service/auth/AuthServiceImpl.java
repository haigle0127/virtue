package cn.haigle.virtue.service.auth;

import cn.haigle.virtue.common.enums.LoginTypeEnum;
import cn.haigle.virtue.common.exception.CodeConstants;
import cn.haigle.virtue.common.exception.ServerException;
import cn.haigle.virtue.common.exception.ServiceException;
import cn.haigle.virtue.common.security.jwt.JwtTokenProvider;
import cn.haigle.virtue.common.security.model.LoginUser;
import cn.haigle.virtue.common.security.util.SecurityUtils;
import cn.haigle.virtue.controller.admin.auth.vo.AuthLoginRespVO;
import cn.haigle.virtue.controller.admin.auth.vo.LoginReqVO;
import cn.haigle.virtue.controller.admin.user.vo.UserInfoVO;
import cn.haigle.virtue.service.user.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 认证服务实现，登录走 Spring Security 官方认证链
 *
 * @author haigle
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private JwtTokenProvider jwtTokenProvider;

    @Resource
    private LoginUserDetailsService loginUserDetailsService;

    @Resource
    private RefreshTokenService refreshTokenService;

    @Resource
    private UserService userService;

    @Override
    public AuthLoginRespVO login(LoginReqVO reqVO, String loginType) {
        LoginTypeEnum typeEnum = LoginTypeEnum.fromCode(loginType);
        if (typeEnum == null) {
            throw new ServiceException("无效的登录类型");
        }

        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(reqVO.getUsername(), reqVO.getPassword()));
        } catch (DisabledException e) {
            throw new ServiceException(CodeConstants.USER_DISABLED);
        } catch (InternalAuthenticationServiceException e) {
            // 数据库不可用等内部错误，不能伪装成密码错误
            throw new ServerException("认证服务异常，请稍后重试");
        } catch (AuthenticationException e) {
            // 账号不存在、密码错误等统一提示，不暴露账号是否存在
            throw new ServiceException(CodeConstants.USER_ERROR);
        }

        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        loginUser.setLoginType(typeEnum.getCode());
        RefreshTokenService.IssuedRefreshToken refreshToken =
                refreshTokenService.create(loginUser.getId(), typeEnum.getCode());
        return buildLoginResult(loginUser, refreshToken);
    }

    @Override
    public AuthLoginRespVO refreshToken(String refreshToken) {
        RefreshTokenService.RefreshSession session = refreshTokenService.resolve(refreshToken);

        LoginUser loginUser;
        try {
            loginUser = loginUserDetailsService.loadUserById(session.userId());
        } catch (UsernameNotFoundException e) {
            refreshTokenService.revokeSession(session.sessionId());
            throw new ServiceException(CodeConstants.TOKEN_ERROR);
        }
        if (!loginUser.isEnabled()) {
            refreshTokenService.revokeSession(session.sessionId());
            throw new ServiceException(CodeConstants.USER_DISABLED);
        }

        RefreshTokenService.IssuedRefreshToken rotated = refreshTokenService.rotate(session, refreshToken);
        loginUser.setLoginType(session.loginType());
        return buildLoginResult(loginUser, rotated);
    }

    @Override
    public void logout() {
        refreshTokenService.revokeSession(SecurityUtils.getSessionId());
    }

    @Override
    public UserInfoVO getCurrentUser() {
        Long userId = SecurityUtils.getUserId();
        if (userId == null) {
            throw new ServiceException(CodeConstants.TOKEN_ERROR);
        }
        return userService.getInfo(userId);
    }

    private AuthLoginRespVO buildLoginResult(LoginUser loginUser, RefreshTokenService.IssuedRefreshToken refreshToken) {
        AuthLoginRespVO respVO = new AuthLoginRespVO();
        respVO.setAccessToken(jwtTokenProvider.createAccessToken(loginUser, refreshToken.sessionId()));
        respVO.setRefreshToken(refreshToken.token());
        respVO.setUserId(loginUser.getId());
        respVO.setExpiresTime(System.currentTimeMillis() + jwtTokenProvider.getAccessTokenTtlSeconds() * 1000);
        return respVO;
    }

}
