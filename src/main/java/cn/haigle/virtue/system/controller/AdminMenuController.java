package cn.haigle.virtue.system.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.haigle.virtue.system.entity.ao.AdminMenuAo;
import cn.haigle.virtue.system.entity.vo.AdminMenuVo;
import cn.haigle.virtue.system.service.AdminMenuService;
import cn.haigle.virtue.common.base.validator.Save;
import cn.haigle.virtue.common.base.validator.Update;
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
    @PostMapping("/list")
    public ApiResult<List<AdminMenuVo>> list(@NotNull(message = "common.id.not_blank") @RequestParam("id") Long id) {
        return ApiResult.ok(adminMenuService.list(id));
    }

    /**
     * 保存菜单
     * @author haigle
     * @date 2019/9/6 9:01
     */
    @ApiOperation("保存菜单")
    @PostMapping("/save")
    public ApiResult<String> save(@Validated(Save.class) @RequestBody AdminMenuAo adminMenuAo) {
        adminMenuService.save(adminMenuAo, StpUtil.getLoginIdAsLong());
        return ApiResult.ok();
    }

    /**
     * 更新菜单
     * @author haigle
     * @date 2019/9/6 9:01
     */
    @ApiOperation("更新菜单")
    @PostMapping("/update")
    public ApiResult<String> update(@Validated(Update.class) @RequestBody AdminMenuAo adminMenuAo) {
        adminMenuService.update(adminMenuAo, StpUtil.getLoginIdAsLong());
        return ApiResult.ok();
    }

    /**
     * 删除菜单
     * @author haigle
     * @date 2019/9/6 9:01
     */
    @ApiOperation("删除菜单")
    @PostMapping("/delete")
    public ApiResult<String> delete(@NotNull(message = "common.id.not_blank") @RequestParam("id") Long id) {
        adminMenuService.delete(id);
        return ApiResult.ok();
    }

}
