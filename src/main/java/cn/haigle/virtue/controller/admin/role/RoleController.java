package cn.haigle.virtue.controller.admin.role;

import cn.haigle.virtue.common.pojo.ApiResult;
import cn.haigle.virtue.common.pojo.PageResult;
import cn.haigle.virtue.controller.admin.role.vo.RolePageReqVO;
import cn.haigle.virtue.controller.admin.role.vo.RolePageRespVO;
import cn.haigle.virtue.controller.admin.role.vo.RoleRespVO;
import cn.haigle.virtue.controller.admin.role.vo.RoleSaveReqVO;
import cn.haigle.virtue.service.role.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色管理
 *
 * @author haigle
 */
@Tag(name = "角色管理")
@RestController
@RequestMapping("/role")
@PreAuthorize("hasRole('admin')")
public class RoleController {

    @Resource
    private RoleService roleService;

    @Operation(summary = "获取角色分页列表")
    @GetMapping("/page")
    public ApiResult<PageResult<RolePageRespVO>> page(RolePageReqVO reqVO) {
        return ApiResult.ok(roleService.page(reqVO));
    }

    @Operation(summary = "获取角色详情")
    @GetMapping("/get")
    public ApiResult<RoleRespVO> get(@RequestParam("id") Long id) {
        return ApiResult.ok(roleService.detail(id));
    }

    @Operation(summary = "新增角色")
    @PostMapping("/create")
    public ApiResult<Boolean> create(@RequestBody RoleSaveReqVO reqVO) {
        roleService.add(reqVO);
        return ApiResult.ok();
    }

    @Operation(summary = "修改角色")
    @PutMapping("/update")
    public ApiResult<Boolean> update(@RequestBody RoleSaveReqVO reqVO) {
        roleService.update(reqVO);
        return ApiResult.ok();
    }

    @Operation(summary = "删除角色")
    @DeleteMapping("/delete")
    public ApiResult<Boolean> delete(@RequestParam("id") Long id) {
        roleService.delete(id);
        return ApiResult.ok();
    }

    @Operation(summary = "获取角色精简列表")
    @GetMapping("/simple-list")
    public ApiResult<List<RoleRespVO>> simpleList() {
        return ApiResult.ok(roleService.simpleList());
    }

}
