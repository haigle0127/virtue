package com.haigle.around.admin.controller;

import com.haigle.around.admin.entity.Query.AdminSearchNameQuery;
import com.haigle.around.admin.entity.ao.AdminUserAo;
import com.haigle.around.admin.service.AdminUserService;
import com.haigle.around.common.base.validator.Save;
import com.haigle.around.common.base.validator.Update;
import com.haigle.around.common.interceptor.permission.annotation.Permissions;
import com.haigle.around.common.interceptor.model.I18n;
import com.haigle.around.common.interceptor.model.ServiceResult;
import com.haigle.around.common.util.JwtUtils;
import com.haigle.around.config.Constant;
import com.haigle.around.common.interceptor.model.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * 用户管理
 * @author haigle
 * @date 2019/7/25 13:36
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping(Constant.ADMIN+"/user")
public class AdminUserController extends I18n {

    @Resource(name = "adminUserService")
    private AdminUserService adminUserService;

    /**
     * 管理用户列表
     * @author haigle
     * @date 2019/8/19 9:03
     */
    @ApiOperation("用户列表")
    @PostMapping("/list")
    public ApiResult list(@RequestBody AdminSearchNameQuery adminSearchNameQuery) {
        return new ApiResult<>(true, adminUserService.list(adminSearchNameQuery));
    }

    /**
     * 录入用户
     * @author haigle
     * @date 2019/9/5 10:08
     */
    @ApiOperation("添加用户")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", dataType = "String", name = Constant.TOKEN, value = "登录凭证", required = true)})
    @Permissions("system-user-add")
    @PostMapping("/save")
    public ApiResult save(@Validated(Save.class) @RequestBody AdminUserAo adminUserAo, @RequestHeader(Constant.TOKEN) String token) {
        ServiceResult serviceResult = adminUserService.save(adminUserAo, JwtUtils.getSubject(token));
        if(!serviceResult.isSuccess()) {
            return apiResult.setMessage(serviceResult.getMessage(), false);
        }
        return apiResult.setMessage(SAVE_SUCCESS, true);
    }

    /**
     * 更新用户
     * @author haigle
     * @date 2019/9/6 8:45
     */
    @ApiOperation("更新用户")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", dataType = "String", name = Constant.TOKEN, value = "登录凭证", required = true)})
    @Permissions("system-user-edit")
    @PostMapping("/update")
    public ApiResult update(@Validated(Update.class) @RequestBody AdminUserAo adminUserAo, @RequestHeader(Constant.TOKEN) String token) {
        ServiceResult serviceResult = adminUserService.update(adminUserAo, JwtUtils.getSubject(token));
        if(!serviceResult.isSuccess()) {
            return apiResult.setMessage(serviceResult.getMessage(), false);
        }
        return apiResult.setMessage(UPDATE_SUCCESS, true);
    }

    /**
     * 删除用户
     * @author haigle
     * @date 2019/9/6 9:03
     */
    @ApiOperation("删除用户")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", dataType = "String", name = Constant.TOKEN, value = "登录凭证", required = true)})
    @Permissions("system-user-delete")
    @PostMapping("/delete")
    public ApiResult delete(@NotNull(message = "common.id.not_blank") @RequestParam("id") Long id, @RequestHeader(Constant.TOKEN) String token) {
        adminUserService.delete(id);
        return apiResult.setMessage(DELETE_SUCCESS, true);
    }

    /**
     * 角色所有结构
     * @author haigle
     * @date 2019/9/9 9:26
     */
    @ApiOperation("角色所有结构")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", dataType = "String", name = Constant.TOKEN, value = "登录凭证", required = true)})
    @PostMapping("/getRoleAllList")
    public ApiResult getRoleAllList() {
        return new ApiResult<>(true, adminUserService.roleAllList());
    }

    /**
     * 用户下角色ID
     * @author haigle
     * @date 2019/9/9 10:31
     */
    @ApiOperation("用户下角色ID")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", dataType = "String", name = Constant.TOKEN, value = "登录凭证", required = true)})
    @PostMapping("/getUserRoleList")
    public ApiResult getUserRoleList(@NotNull(message = "common.id.not_blank") @RequestParam("id") Long id, @RequestHeader(Constant.TOKEN) String token) {
        return new ApiResult<>(true, adminUserService.getUserRoleList(id));
    }

}
