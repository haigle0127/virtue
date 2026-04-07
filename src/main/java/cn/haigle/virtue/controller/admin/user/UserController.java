package cn.haigle.virtue.controller.admin.user;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.haigle.virtue.common.pojo.ApiResult;
import cn.haigle.virtue.common.pojo.PageResult;
import cn.haigle.virtue.common.web.Constant;
import cn.haigle.virtue.controller.admin.user.vo.*;
import cn.haigle.virtue.service.user.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理
 * @author haigle
 * @date 2019/7/25 13:36
 */
@RestController
@RequestMapping(Constant.ADMIN+"/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 获取用户信息
     * <p>获取当前登录用户的详细信息，包括权限和角色</p>
     *
     * @return 用户信息响应对象
     */
    @GetMapping("/getInfo")
    public ApiResult<UserInfoVO> getInfo() {
        Long userId = 1L;
        return ApiResult.ok(userService.getInfo(userId));
    }

    /**
     * 获取用户分页列表
     * <p>根据查询条件分页获取用户列表</p>
     *
     * @param reqVO 用户分页查询请求参数
     * @return 用户分页响应对象
     */
    @SaCheckPermission("sys-user-page")
    @GetMapping("/page")
    public ApiResult<PageResult<UserPageRespVO>> page(UserPageReqVO reqVO) {
        Long userId = 1L;
        return ApiResult.ok(userService.page(reqVO));
    }

    /**
     * 获取用户详情
     * <p>根据用户ID获取用户详细信息</p>
     *
     * @param userId 用户ID
     * @return 用户详情响应对象
     */
    @SaCheckPermission("sys-user-detail")
    @GetMapping("/detail")
    public ApiResult<UserRespVO> detail(@RequestParam(value = "userId", required = false) Long userId) {
        return ApiResult.ok(userService.detail(userId));
    }

    /**
     * 新增用户
     * <p>创建新的用户信息</p>
     *
     * @param reqVO 用户保存请求参数
     * @return 操作结果，成功返回true
     */
    @SaCheckPermission("sys-user-add")
    @PostMapping("/add")
    public ApiResult<Boolean> add(@RequestBody UserSaveReqVO reqVO) {
        userService.add(reqVO);
        return ApiResult.ok();
    }

    /**
     * 修改用户
     * <p>更新已存在的用户信息</p>
     *
     * @param reqVO 用户保存请求参数
     * @return 操作结果，成功返回true
     */
    @SaCheckPermission("sys-user-update")
    @PostMapping("/update")
    public ApiResult<Boolean> update(@RequestBody UserSaveReqVO reqVO) {
        userService.update(reqVO);
        return ApiResult.ok();
    }
}
