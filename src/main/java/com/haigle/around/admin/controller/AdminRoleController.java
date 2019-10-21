package com.haigle.around.admin.controller;

import com.haigle.around.admin.entity.Query.AdminSearchNameQuery;
import com.haigle.around.admin.service.AdminMenuService;
import com.haigle.around.admin.service.AdminRoleService;
import com.haigle.around.common.base.validator.Delete;
import com.haigle.around.common.base.validator.Save;
import com.haigle.around.common.base.validator.Update;
import com.haigle.around.common.interceptor.permission.annotation.Permissions;
import com.haigle.around.common.interceptor.model.ApiResult;
import com.haigle.around.common.interceptor.model.I18n;
import com.haigle.around.common.util.JwtUtils;
import com.haigle.around.config.Constant;
import com.haigle.around.admin.entity.ao.AdminRoleAo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * 角色管理
 * @author haigle
 * @date 2019/7/25 13:36
 */
@Api(tags = "角色管理")
@RestController
@RequestMapping(Constant.ADMIN+"/role")
public class AdminRoleController extends I18n {

    @Resource(name = "adminRoleService")
    private AdminRoleService adminRoleService;

    @Resource(name = "adminMenuService")
    private AdminMenuService adminMenuService;

    /**
     * 获取角色列表
     * @author haigle
     * @date 2019-08-04 01:10
     */
    @ApiOperation("角色列表")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", dataType = "String", name = Constant.TOKEN, value = "登录凭证", required = true)})
    @PostMapping("/list")
    public ApiResult list(@RequestBody AdminSearchNameQuery adminSearchNameQuery, @RequestHeader(Constant.TOKEN) String token) {
        Long uid = JwtUtils.getSubject(token);
        return new ApiResult<>(true, adminRoleService.list(adminSearchNameQuery));
    }

    /**
     * 新增角色
     * @author haigle
     * @date 2019-08-04 20:10
     */
    @ApiOperation("新增角色")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", dataType = "String", name = Constant.TOKEN, value = "登录凭证", required = true)})
    @Permissions("system-role-add")
    @PostMapping("/save")
    public ApiResult save(@Validated(Save.class) @RequestBody AdminRoleAo adminRoleAo, @RequestHeader(Constant.TOKEN) String token) {
        adminRoleService.save(adminRoleAo, JwtUtils.getSubject(token));
        return apiResult.setMessage(SAVE_SUCCESS, true);
    }

    /**
     * 修改角色
     * @author haigle
     * @date 2019-08-04 20:10
     */
    @ApiOperation("修改角色")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", dataType = "String", name = Constant.TOKEN, value = "登录凭证", required = true)})
    @Permissions("system-role-edit")
    @PostMapping("/update")
    public ApiResult update(@Validated(Update.class) @RequestBody AdminRoleAo adminRoleAo, @RequestHeader(Constant.TOKEN) String token) {
        adminRoleService.update(adminRoleAo, JwtUtils.getSubject(token));
        return apiResult.setMessage(UPDATE_SUCCESS, true);
    }

    /**
     * 删除角色
     * @author haigle
     * @date 2019-08-04 22:18
     */
    @ApiOperation("删除角色")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", dataType = "String", name = Constant.TOKEN, value = "登录凭证", required = true)})
    @Permissions("system-role-delete")
    @PostMapping("/delete")
    public ApiResult delete(@Validated(Delete.class) Long id) {
        adminRoleService.delete(id);
        return apiResult.setMessage(DELETE_SUCCESS, true);
    }

    /**
     * 菜单所有结构
     * @author haigle
     * @date 2019/9/3 14:47
     */
    @ApiOperation("菜单所有结构")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", dataType = "String", name = Constant.TOKEN, value = "登录凭证", required = true)})
    @PostMapping("/getMenuAllTree")
    public ApiResult menuAllTree() {
        return new ApiResult<>(true, adminMenuService.menuAllTree());
    }

    /**
     * 单角色下所有菜单ID
     * @author haigle
     * @date 2019/9/3 15:31
     */
    @ApiOperation("角色下菜单ID")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", dataType = "String", name = Constant.TOKEN, value = "登录凭证", required = true)})
    @PostMapping("/getRoleMenuList")
    public ApiResult getRoleMenuList(@NotNull(message = "common.id.not_blank") @RequestParam("id") Long id, @RequestHeader(Constant.TOKEN) String token) {
        return new ApiResult<>(true, adminMenuService.getRoleMenuList(id));
    }

}
