package cn.haigle.virtue.system.controller;

import cn.haigle.virtue.common.base.page.Page;
import cn.haigle.virtue.system.entity.ao.AdminUserAo;
import cn.haigle.virtue.system.entity.vo.AdminTreeVo;
import cn.haigle.virtue.system.entity.vo.AdminUserVo;
import cn.haigle.virtue.system.service.AdminUserService;
import cn.haigle.virtue.common.base.validator.Save;
import cn.haigle.virtue.common.base.validator.Update;
import cn.haigle.virtue.common.entity.query.NameQuery;
import cn.haigle.virtue.common.interceptor.model.ApiResult;
import cn.haigle.virtue.common.interceptor.permission.annotation.Permissions;
import cn.haigle.virtue.common.util.JwtUtils;
import cn.haigle.virtue.config.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 用户管理
 * @author haigle
 * @date 2019/7/25 13:36
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping(Constant.ADMIN+"/user")
public class AdminUserController {

    @Resource(name = "adminUserService")
    private AdminUserService adminUserService;

    /**
     * 管理用户列表
     * @author haigle
     * @date 2019/8/19 9:03
     */
    @ApiOperation("用户列表")
    @PostMapping("/list")
    public ApiResult<Page<AdminUserVo>> list(@RequestBody NameQuery adminSearchNameQuery) {
        return ApiResult.ok(adminUserService.list(adminSearchNameQuery));
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
    public ApiResult<String> save(@Validated(Save.class) @RequestBody AdminUserAo adminUserAo,
                          @RequestHeader(Constant.TOKEN) String token) {
        adminUserService.save(adminUserAo, JwtUtils.getSubject(token));
        return ApiResult.ok();
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
    public ApiResult<String> update(@Validated(Update.class) @RequestBody AdminUserAo adminUserAo, @RequestHeader(Constant.TOKEN) String token) {
        adminUserService.update(adminUserAo, JwtUtils.getSubject(token));
        return ApiResult.ok();
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
    public ApiResult<String> delete(@NotNull(message = "common.id.not_blank") @RequestParam("id") Long id, @RequestHeader(Constant.TOKEN) String token) {
        adminUserService.delete(id);
        return ApiResult.ok();
    }

    /**
     * 角色所有结构
     * @author haigle
     * @date 2019/9/9 9:26
     */
    @ApiOperation("角色所有结构")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", dataType = "String", name = Constant.TOKEN, value = "登录凭证", required = true)})
    @PostMapping("/getRoleAllList")
    public ApiResult<List<AdminTreeVo>> getRoleAllList() {
        return ApiResult.ok(adminUserService.roleAllList());
    }

    /**
     * 用户下角色ID
     * @author haigle
     * @date 2019/9/9 10:31
     */
    @ApiOperation("用户下角色ID")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", dataType = "String", name = Constant.TOKEN, value = "登录凭证", required = true)})
    @PostMapping("/getUserRoleList")
    public ApiResult<List<Long>> getUserRoleList(@NotNull(message = "common.id.not_blank") @RequestParam("id") Long id, @RequestHeader(Constant.TOKEN) String token) {
        return ApiResult.ok(adminUserService.getUserRoleList(id));
    }

}
