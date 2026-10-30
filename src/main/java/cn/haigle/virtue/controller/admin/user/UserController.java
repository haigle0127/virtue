package cn.haigle.virtue.controller.admin.user;

import cn.haigle.virtue.common.pojo.ApiResult;
import cn.haigle.virtue.common.pojo.PageResult;
import cn.haigle.virtue.controller.admin.user.vo.UserPageReqVO;
import cn.haigle.virtue.controller.admin.user.vo.UserRespVO;
import cn.haigle.virtue.controller.admin.user.vo.UserSaveReqVO;
import cn.haigle.virtue.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 用户管理
 *
 * @author haigle
 */
@Tag(name = "用户管理")
@RestController
@RequestMapping("/user")
@PreAuthorize("hasRole('admin')")
public class UserController {

    @Resource
    private UserService userService;

    @Operation(summary = "获取用户分页列表")
    @GetMapping("/page")
    public ApiResult<PageResult<?>> page(UserPageReqVO reqVO) {
        return ApiResult.ok(userService.page(reqVO));
    }

    @Operation(summary = "获取用户详情")
    @GetMapping("/get")
    public ApiResult<UserRespVO> get(@RequestParam("id") Long id) {
        return ApiResult.ok(userService.detail(id));
    }

    @Operation(summary = "新增用户")
    @PostMapping("/create")
    public ApiResult<Boolean> create(@RequestBody UserSaveReqVO reqVO) {
        userService.add(reqVO);
        return ApiResult.ok();
    }

    @Operation(summary = "修改用户")
    @PutMapping("/update")
    public ApiResult<Boolean> update(@RequestBody UserSaveReqVO reqVO) {
        userService.update(reqVO);
        return ApiResult.ok();
    }

    @Operation(summary = "删除用户")
    @DeleteMapping("/delete")
    public ApiResult<Boolean> delete(@RequestParam("id") Long id) {
        userService.delete(id);
        return ApiResult.ok();
    }

    @Operation(summary = "批量删除用户")
    @DeleteMapping("/delete-list")
    public ApiResult<Boolean> deleteList(@RequestParam("ids") String ids) {
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Long::valueOf)
                .toList();
        userService.deleteList(idList);
        return ApiResult.ok();
    }

    @Operation(summary = "获取用户精简列表")
    @GetMapping("/simple-list")
    public ApiResult<List<UserRespVO>> simpleList() {
        return ApiResult.ok(userService.simpleList());
    }

    @Operation(summary = "修改用户密码")
    @PutMapping("/update-password")
    public ApiResult<Boolean> updatePassword(@RequestBody java.util.Map<String, Object> params) {
        Long id = Long.valueOf(params.get("id").toString());
        String password = params.get("password").toString();
        userService.updatePassword(id, password);
        return ApiResult.ok();
    }

    @Operation(summary = "修改用户状态")
    @PutMapping("/update-status")
    public ApiResult<Boolean> updateStatus(@RequestBody java.util.Map<String, Object> params) {
        Long id = Long.valueOf(params.get("id").toString());
        Integer status = Integer.valueOf(params.get("status").toString());
        userService.updateStatus(id, status);
        return ApiResult.ok();
    }

}
