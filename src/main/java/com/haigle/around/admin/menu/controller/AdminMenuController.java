package com.haigle.around.admin.menu.controller;

import com.haigle.around.admin.menu.entity.ao.AdminMenuAO;
import com.haigle.around.admin.menu.service.AdminMenuService;
import com.haigle.around.common.base.validator.Save;
import com.haigle.around.common.base.validator.Update;
import com.haigle.around.common.interceptor.model.ApiResultDataI18n;
import com.haigle.around.common.interceptor.permission.annotation.Permissions;
import com.haigle.around.common.interceptor.model.ApiResultI18n;
import com.haigle.around.common.interceptor.model.BaseI18n;
import com.haigle.around.common.interceptor.model.service.ServiceResult;
import com.haigle.around.common.util.JwtUtils;
import com.haigle.around.config.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * 菜单、权限管理
 * @author haigle
 * @date 2019/7/25 13:36
 */
@Api(tags = "菜单管理")
@RestController
@RequestMapping(Constant.ADMIN+"/menu")
public class AdminMenuController extends BaseI18n {

    private static final String PARENT_NOT_ERROR = "menu.parent_not.error";

    @Resource(name = "adminMenuService")
    private AdminMenuService adminMenuService;

    /**
     * 菜单、权限列表
     * @author haigle
     * @date 2019/8/23 9:16
     */
    @ApiOperation("菜单列表")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", dataType = "String", name = Constant.TOKEN, value = "登录凭证", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "Long", name = "id", value = "菜单ID", required = true)})
    @PostMapping("/list")
    public ApiResultI18n list(@NotNull(message = "common.id.not_blank") @RequestParam("id") Long id, @RequestHeader(Constant.TOKEN) String token) {
        return new ApiResultDataI18n<>(true, adminMenuService.list(id));
    }

    /**
     * 保存菜单
     * @author haigle
     * @date 2019/9/6 9:01
     */
    @ApiOperation("保存菜单")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", dataType = "String", name = Constant.TOKEN, value = "登录凭证", required = true)})
    @Permissions("system-menu-add")
    @PostMapping("/save")
    public ApiResultI18n save(@Validated(Save.class) @RequestBody AdminMenuAO adminMenuAO, @RequestHeader(Constant.TOKEN) String token) {
        ServiceResult serviceResult = adminMenuService.save(adminMenuAO, JwtUtils.getSubject(token));
        if(!serviceResult.isSuccess()) {
            return new ApiResultI18n(serviceResult.getMessage(), false);
        }
        return new ApiResultI18n(SAVE_SUCCESS, true);
    }

    /**
     * 更新菜单
     * @author haigle
     * @date 2019/9/6 9:01
     */
    @ApiOperation("更新菜单")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", dataType = "String", name = Constant.TOKEN, value = "登录凭证", required = true)})
    @Permissions("system-menu-edit")
    @PostMapping("/update")
    public ApiResultI18n update(@Validated(Update.class) @RequestBody AdminMenuAO adminMenuAO, @RequestHeader(Constant.TOKEN) String token) {
        ServiceResult serviceResult = adminMenuService.update(adminMenuAO, JwtUtils.getSubject(token));
        if(!serviceResult.isSuccess()) {
            return new ApiResultI18n(serviceResult.getMessage(), false);
        }
        return new ApiResultI18n(SAVE_SUCCESS, true);
    }

    /**
     * 删除菜单
     * @author haigle
     * @date 2019/9/6 9:01
     */
    @ApiOperation("删除菜单")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", dataType = "String", name = Constant.TOKEN, value = "登录凭证", required = true)})
    @Permissions("system-menu-delete")
    @PostMapping("/delete")
    public ApiResultI18n delete(@NotNull(message = "common.id.not_blank") @RequestParam("id") Long id, @RequestHeader(Constant.TOKEN) String token) {
        if(adminMenuService.getIsChildren(id)) {
            return new ApiResultI18n(PARENT_NOT_ERROR, false);
        }
        adminMenuService.delete(id);
        return new ApiResultI18n(DELETE_SUCCESS, true);
    }

}
