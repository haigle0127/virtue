package cn.haigle.virtue.controller.admin.role;

import cn.haigle.virtue.common.pojo.ApiResult;
import cn.haigle.virtue.common.pojo.PageResult;
import cn.haigle.virtue.common.security.annotation.RequireLogin;
import cn.haigle.virtue.controller.admin.role.vo.*;
import cn.haigle.virtue.service.role.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * 角色管理
 * @author haigle
 * @date 2024/11/14 14:34
 */
@Tag(name = "角色管理")
@RestController
@RequestMapping("/role")
@RequireLogin
public class RoleController {

    @Resource
    private RoleService roleService;

    /**
     * 获取角色分页列表
     * <p>根据查询条件分页获取角色列表</p>
     *
     * @param reqVO 角色分页查询请求参数
     * @return 角色分页响应对象
     */
    @Operation(summary = "获取角色分页列表")
    @GetMapping("/page")
    public ApiResult<PageResult<RolePageRespVO>> page(RolePageReqVO reqVO) {
        return ApiResult.ok(roleService.page(reqVO));
    }

    /**
     * 获取角色详情
     * <p>根据角色ID获取角色详细信息</p>
     *
     * @param roleId 角色ID
     * @return 角色详情响应对象
     */
    @Operation(summary = "获取角色详情")
    @GetMapping("/detail")
    public ApiResult<RoleRespVO> detail(@RequestParam(value = "roleId") Long roleId) {
        return ApiResult.ok(roleService.detail(roleId));
    }

    /**
     * 新增角色
     * <p>创建新的角色信息</p>
     *
     * @param reqVO 角色保存请求参数
     * @return 操作结果，成功返回true
     */
    @Operation(summary = "新增角色")
    @PostMapping("/add")
    public ApiResult<Boolean> add(@RequestBody RoleSaveReqVO reqVO) {
        roleService.add(reqVO);
        return ApiResult.ok();
    }

    /**
     * 修改角色
     * <p>更新已存在的角色信息</p>
     *
     * @param reqVO 角色保存请求参数
     * @return 操作结果，成功返回true
     */
    @Operation(summary = "修改角色")
    @PostMapping("/update")
    public ApiResult<Boolean> update(@RequestBody RoleSaveReqVO reqVO) {
        roleService.update(reqVO);
        return ApiResult.ok();
    }

    /**
     * 修改角色数据权限
     * <p>更新角色的数据范围权限配置</p>
     *
     * @param reqVO 角色保存请求参数，包含数据权限配置
     * @return 操作结果，成功返回true
     */
    @Operation(summary = "修改角色数据权限")
    @PostMapping("/updateDataScope")
    public ApiResult<Boolean> updateDataScope(@RequestBody RoleSaveReqVO reqVO) {
        roleService.updateDataScope(reqVO);
        return ApiResult.ok();
    }

    /**
     * 修改角色状态
     * <p>更新角色的启用或停用状态</p>
     *
     * @param reqVO 角色状态更新请求参数
     * @return 操作结果，成功返回true
     */
    @Operation(summary = "修改角色状态")
    @PostMapping("/updateState")
    public ApiResult<Boolean> updateState(@RequestBody RoleUpdateStateReqVO reqVO) {
        roleService.updateState(reqVO);
        return ApiResult.ok();
    }

    /**
     * 删除角色
     * <p>根据角色ID删除角色信息</p>
     *
     * @param roleId 角色ID
     * @return 操作结果，成功返回true
     */
    @Operation(summary = "删除角色")
    @DeleteMapping("/delete")
    public ApiResult<Boolean> delete(@RequestParam(value = "roleId") Long roleId) {
        roleService.delete(roleId);
        return ApiResult.ok();
    }

}
