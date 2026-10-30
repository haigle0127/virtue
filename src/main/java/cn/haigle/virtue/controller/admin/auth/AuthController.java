package cn.haigle.virtue.controller.admin.auth;

import cn.haigle.virtue.common.enums.LoginTypeEnum;
import cn.haigle.virtue.common.pojo.ApiResult;
import cn.haigle.virtue.controller.admin.auth.vo.AuthLoginRespVO;
import cn.haigle.virtue.controller.admin.auth.vo.LoginReqVO;
import cn.haigle.virtue.controller.admin.auth.vo.RefreshTokenReqVO;
import cn.haigle.virtue.controller.admin.user.vo.UserInfoVO;
import cn.haigle.virtue.service.auth.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理后台认证接口
 *
 * @author haigle
 */
@Tag(name = "认证管理")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    private AuthService authService;

    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public ApiResult<AuthLoginRespVO> login(@Validated @RequestBody LoginReqVO reqVO) {
        return ApiResult.ok(authService.login(reqVO, LoginTypeEnum.ADMIN.getCode()));
    }

    @Operation(summary = "退出登录")
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/logout")
    public ApiResult<Boolean> logout() {
        authService.logout();
        return ApiResult.ok();
    }

    @Operation(summary = "刷新令牌")
    @PostMapping("/refresh-token")
    public ApiResult<AuthLoginRespVO> refreshToken(@Validated @RequestBody RefreshTokenReqVO reqVO) {
        return ApiResult.ok(authService.refreshToken(reqVO.getRefreshToken()));
    }

    @Operation(summary = "获取当前登录用户")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    public ApiResult<UserInfoVO> me() {
        return ApiResult.ok(authService.getCurrentUser());
    }

}
