package cn.haigle.virtue.controller.admin.menu;

import cn.haigle.virtue.common.pojo.ApiResult;
import cn.haigle.virtue.common.security.annotation.RequireLogin;
import cn.haigle.virtue.common.security.context.SecurityContext;
import cn.haigle.virtue.controller.admin.menu.vo.*;
import cn.haigle.virtue.service.menu.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单管理
 * @author haigle
 * @date 2019/7/25 13:36
 */
@Tag(name = "菜单管理")
@RestController
@RequestMapping("/menu")
@RequireLogin
public class MenuController {

    @Resource
    private MenuService menuService;

    /**
     * 获取路由信息
     * <p>根据当前登录用户ID获取其权限范围内的菜单路由配置</p>
     *
     * @return 路由配置响应对象集合
     */
    @Operation(summary = "获取路由信息")
    @GetMapping("/getRouters")
    public ApiResult<List<RouterVO>> getRouters() {
        Long userId = SecurityContext.getUserId();
        List<MenuRespVO> menus = menuService.selectTreeByUserId(userId);
        return ApiResult.ok(menuService.buildMenus(menus));
    }

    /**
     * 获取菜单列表
     * <p>根据查询条件获取菜单列表，支持树形结构展示</p>
     *
     * @param reqVO 菜单查询请求参数
     * @return 菜单列表响应对象集合
     */
    @Operation(summary = "获取菜单列表")
    @GetMapping("/list")
    public ApiResult<List<MenuRespVO>> list(MenuReqVO reqVO) {
        return ApiResult.ok(menuService.list(reqVO));
    }

    /**
     * 获取菜单详情
     * <p>根据菜单ID获取菜单详细信息</p>
     *
     * @param menuId 菜单ID
     * @return 菜单详情响应对象
     */
    @Operation(summary = "获取菜单详情")
    @GetMapping("/detail")
    public ApiResult<MenuRespVO> detail(@RequestParam(value = "menuId") Long menuId) {
        return ApiResult.ok(menuService.detail(menuId));
    }

    /**
     * 新增菜单
     * <p>创建新的菜单信息</p>
     *
     * @param reqVO 菜单保存请求参数
     * @return 操作结果，成功返回true
     */
    @Operation(summary = "新增菜单")
    @PostMapping("/add")
    public ApiResult<Boolean> add(@RequestBody MenuSaveReqVO reqVO) {
        menuService.add(reqVO);
        return ApiResult.ok();
    }

    /**
     * 修改菜单
     * <p>更新已存在的菜单信息</p>
     *
     * @param reqVO 菜单保存请求参数
     * @return 操作结果，成功返回true
     */
    @Operation(summary = "修改菜单")
    @PostMapping("/update")
    public ApiResult<Boolean> update(@RequestBody MenuSaveReqVO reqVO) {
        menuService.update(reqVO);
        return ApiResult.ok();
    }

    /**
     * 删除菜单
     * <p>根据菜单ID删除菜单信息</p>
     *
     * @param menuId 菜单ID
     * @return 操作结果，成功返回true
     */
    @Operation(summary = "删除菜单")
    @DeleteMapping("/delete")
    public ApiResult<Boolean> delete(@RequestParam(value = "menuId") Long menuId) {
        menuService.delete(menuId);
        return ApiResult.ok();
    }

    /**
     * 获取用户菜单树
     * <p>根据当前登录用户ID获取其权限范围内的菜单树形结构</p>
     *
     * @return 菜单树DTO集合
     */
    @Operation(summary = "获取用户菜单树")
    @GetMapping("/userMenuTree")
    public ApiResult<List<MenuTreeDTO>> userMenuTree() {
        Long userId = SecurityContext.getUserId();
        return ApiResult.ok(menuService.userMenuTree(userId));
    }

    /**
     * 获取角色菜单树
     * <p>根据角色ID获取该角色关联的菜单树形结构</p>
     *
     * @param roleId 角色ID
     * @return 角色菜单树响应对象
     */
    @Operation(summary = "获取角色菜单树")
    @GetMapping("/roleMenuTree")
    public ApiResult<RoleMenuTreeRespVO> roleMenuTree(@RequestParam(value = "roleId") Long roleId) {
        return ApiResult.ok(menuService.roleMenuTree(roleId));
    }



}
