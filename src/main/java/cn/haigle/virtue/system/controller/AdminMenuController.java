package cn.haigle.virtue.system.controller;

import cn.haigle.virtue.system.entity.ao.AdminMenuAo;
import cn.haigle.virtue.system.entity.vo.AdminMenuVo;
import cn.haigle.virtue.system.service.AdminMenuService;
import cn.haigle.virtue.common.base.validator.Save;
import cn.haigle.virtue.common.base.validator.Update;
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
 * 菜单、权限管理
 * @author haigle
 * @date 2019/7/25 13:36
 */
@Api(tags = "菜单管理")
@RestController
@RequestMapping(Constant.ADMIN+"/menu")
public class AdminMenuController {

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
    public ApiResult<List<AdminMenuVo>> list(@NotNull(message = "common.id.not_blank") @RequestParam("id") Long id, @RequestHeader(Constant.TOKEN) String token) {
        return ApiResult.ok(adminMenuService.list(id));
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
    public ApiResult<String> save(@Validated(Save.class) @RequestBody AdminMenuAo adminMenuAo, @RequestHeader(Constant.TOKEN) String token) {
        adminMenuService.save(adminMenuAo, JwtUtils.getSubject(token));
        return ApiResult.ok();
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
    public ApiResult<String> update(@Validated(Update.class) @RequestBody AdminMenuAo adminMenuAo, @RequestHeader(Constant.TOKEN) String token) {
        adminMenuService.update(adminMenuAo, JwtUtils.getSubject(token));
        return ApiResult.ok();
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
    public ApiResult<String> delete(@NotNull(message = "common.id.not_blank") @RequestParam("id") Long id, @RequestHeader(Constant.TOKEN) String token) {
        adminMenuService.delete(id);
        return ApiResult.ok();
    }

}
