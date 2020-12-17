package cn.haigle.around.admin.role.controller;

import cn.haigle.around.admin.menu.service.AdminMenuService;
import cn.haigle.around.admin.role.entity.ao.AdminRoleAO;
import cn.haigle.around.admin.role.service.AdminRoleService;
import cn.haigle.around.common.base.validator.Delete;
import cn.haigle.around.common.base.validator.Save;
import cn.haigle.around.common.base.validator.Update;
import cn.haigle.around.common.entity.query.NameQuery;
import cn.haigle.around.common.interceptor.model.ApiResult;
import cn.haigle.around.common.interceptor.model.message.CodeStatus;
import cn.haigle.around.common.interceptor.permission.annotation.Permissions;
import cn.haigle.around.common.util.JwtUtils;
import cn.haigle.around.config.Constant;
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
public class AdminRoleController {

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
    public ApiResult list(@RequestBody NameQuery adminSearchNameQuery, @RequestHeader(Constant.TOKEN) String token) {
        Long uid = JwtUtils.getSubject(token);
        return new ApiResult<>(adminRoleService.list(adminSearchNameQuery), CodeStatus.OK);
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
    public ApiResult save(@Validated(Save.class) @RequestBody AdminRoleAO adminRoleAo, @RequestHeader(Constant.TOKEN) String token) {
        adminRoleService.save(adminRoleAo, JwtUtils.getSubject(token));
        return new ApiResult<>(CodeStatus.OK);
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
    public ApiResult update(@Validated(Update.class) @RequestBody AdminRoleAO adminRoleAo, @RequestHeader(Constant.TOKEN) String token) {
        adminRoleService.update(adminRoleAo, JwtUtils.getSubject(token));
        return new ApiResult<>(CodeStatus.OK);
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
        return new ApiResult<>(CodeStatus.OK);
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
        return new ApiResult<>(adminMenuService.menuAllTree(), CodeStatus.OK);
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
        return new ApiResult<>(adminMenuService.getRoleMenuList(id), CodeStatus.OK);
    }

}
