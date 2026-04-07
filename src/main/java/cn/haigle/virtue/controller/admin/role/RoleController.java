package cn.haigle.virtue.controller.admin.role;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.haigle.virtue.common.pojo.ApiResult;
import cn.haigle.virtue.common.pojo.PageResult;
import cn.haigle.virtue.common.web.Constant;
import cn.haigle.virtue.controller.admin.role.vo.*;
import cn.haigle.virtue.service.role.RoleService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * 角色管理
 * @author haigle
 * @date 2024/11/14 14:34
 */
@RestController
@RequestMapping(Constant.ADMIN+"/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @SaCheckPermission("sys-role-page")
    @GetMapping("/page")
    public ApiResult<PageResult<RolePageRespVO>> page(RolePageReqVO reqVO) {
        Long userId = 1L;
        return ApiResult.ok(roleService.page(reqVO));
    }

    @SaCheckPermission("sys-role-detail")
    @GetMapping("/detail")
    public ApiResult<RoleRespVO> detail(@RequestParam(value = "roleId") Long roleId) {
        return ApiResult.ok(roleService.detail(roleId));
    }

    @SaCheckPermission("sys-role-add")
    @PostMapping("/add")
    public ApiResult<Boolean> add(@RequestBody RoleSaveReqVO reqVO) {
        roleService.add(reqVO);
        return ApiResult.ok();
    }

    @SaCheckPermission("sys-role-update")
    @PostMapping("/update")
    public ApiResult<Boolean> update(@RequestBody RoleSaveReqVO reqVO) {
        roleService.update(reqVO);
        return ApiResult.ok();
    }

    @SaCheckPermission("sys-role-update-dataScope")
    @PostMapping("/updateDataScope")
    public ApiResult<Boolean> updateDataScope(@RequestBody RoleSaveReqVO reqVO) {
        roleService.updateDataScope(reqVO);
        return ApiResult.ok();
    }

    @SaCheckPermission("sys-role-update-state")
    @PostMapping("/updateState")
    public ApiResult<Boolean> updateState(@RequestBody RoleUpdateStateReqVO reqVO) {
        roleService.updateState(reqVO);
        return ApiResult.ok();
    }

    @SaCheckPermission("sys-role-delete")
    @DeleteMapping("/delete")
    public ApiResult<Boolean> delete(@RequestParam(value = "roleId") Long roleId) {
        roleService.delete(roleId);
        return ApiResult.ok();
    }

}
