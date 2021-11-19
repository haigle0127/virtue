package cn.haigle.virtue.system.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.haigle.virtue.common.base.page.Page;
import cn.haigle.virtue.system.entity.vo.AdminRoleVo;
import cn.haigle.virtue.system.entity.vo.AdminTreeVo;
import cn.haigle.virtue.system.service.AdminMenuService;
import cn.haigle.virtue.system.entity.ao.AdminRoleAo;
import cn.haigle.virtue.system.service.AdminRoleService;
import cn.haigle.virtue.common.base.validator.Delete;
import cn.haigle.virtue.common.base.validator.Save;
import cn.haigle.virtue.common.base.validator.Update;
import cn.haigle.virtue.common.entity.query.NameQuery;
import cn.haigle.virtue.common.interceptor.model.ApiResult;
import cn.haigle.virtue.config.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

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
    @PostMapping("/list")
    public ApiResult<Page<AdminRoleVo>> list(@RequestBody NameQuery adminSearchNameQuery) {
        return ApiResult.ok(adminRoleService.list(adminSearchNameQuery));
    }

    /**
     * 新增角色
     * @author haigle
     * @date 2019-08-04 20:10
     */
    @ApiOperation("新增角色")
    @PostMapping("/save")
    public ApiResult<String> save(@Validated(Save.class) @RequestBody AdminRoleAo adminRoleAo) {
        adminRoleService.save(adminRoleAo, StpUtil.getLoginIdAsLong());
        return ApiResult.ok();
    }

    /**
     * 修改角色
     * @author haigle
     * @date 2019-08-04 20:10
     */
    @ApiOperation("修改角色")
    @PostMapping("/update")
    public ApiResult<String> update(@Validated(Update.class) @RequestBody AdminRoleAo adminRoleAo) {
        adminRoleService.update(adminRoleAo, StpUtil.getLoginIdAsLong());
        return ApiResult.ok();
    }

    /**
     * 删除角色
     * @author haigle
     * @date 2019-08-04 22:18
     */
    @ApiOperation("删除角色")
    @PostMapping("/delete")
    public ApiResult<String> delete(@Validated(Delete.class) Long id) {
        adminRoleService.delete(id);
        return ApiResult.ok();
    }

    /**
     * 菜单所有结构
     * @author haigle
     * @date 2019/9/3 14:47
     */
    @ApiOperation("菜单所有结构")
    @PostMapping("/getMenuAllTree")
    public ApiResult<List<AdminTreeVo>> menuAllTree() {
        return ApiResult.ok(adminMenuService.menuAllTree());
    }

    /**
     * 单角色下所有菜单ID
     * @author haigle
     * @date 2019/9/3 15:31
     */
    @ApiOperation("角色下菜单ID")
    @PostMapping("/getRoleMenuList")
    public ApiResult<List<Long>> getRoleMenuList(@NotNull(message = "common.id.not_blank") @RequestParam("id") Long id) {
        return ApiResult.ok(adminMenuService.getRoleMenuList(id));
    }

}
