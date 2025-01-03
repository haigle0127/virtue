package cn.haigle.virtue.controller.admin.user;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.haigle.virtue.common.pojo.ApiResult;
import cn.haigle.virtue.common.pojo.PageResult;
import cn.haigle.virtue.common.web.Constant;
import cn.haigle.virtue.controller.admin.user.vo.*;
import cn.haigle.virtue.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理
 * @author haigle
 * @date 2019/7/25 13:36
 */
@Tag(name = "用户管理")
@RestController
@RequestMapping(Constant.ADMIN+"/user")
public class UserController {

    @Resource
    private UserService userService;

    @Operation(summary = "用户信息")
    @GetMapping("/getInfo")
    public ApiResult<UserInfoVO> getInfo() {
        Long userId = 1L;
        return ApiResult.ok(userService.getInfo(userId));
    }

    @Operation(summary = "用户分页")
    @SaCheckPermission("sys-user-page")
    @GetMapping("/page")
    public ApiResult<PageResult<UserPageRespVO>> page(UserPageReqVO reqVO) {
        Long userId = 1L;
        return ApiResult.ok(userService.page(reqVO));
    }

    @Operation(summary = "用户详情")
    @SaCheckPermission("sys-user-detail")
    @GetMapping("/detail")
    public ApiResult<UserRespVO> detail(@RequestParam(value = "userId", required = false) Long userId) {
        return ApiResult.ok(userService.detail(userId));
    }

    @Operation(summary = "用户新增")
    @SaCheckPermission("sys-user-add")
    @PostMapping("/add")
    public ApiResult<Boolean> add(@RequestBody UserSaveReqVO reqVO) {
        userService.add(reqVO);
        return ApiResult.ok();
    }

    @Operation(summary = "用户更新")
    @SaCheckPermission("sys-user-update")
    @PostMapping("/update")
    public ApiResult<Boolean> update(@RequestBody UserSaveReqVO reqVO) {
        userService.update(reqVO);
        return ApiResult.ok();
    }
}
