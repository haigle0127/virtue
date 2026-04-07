package cn.haigle.virtue.controller.admin.menu;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.haigle.virtue.common.pojo.ApiResult;
import cn.haigle.virtue.common.web.Constant;
import cn.haigle.virtue.controller.admin.menu.vo.*;
import cn.haigle.virtue.service.menu.MenuService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单管理
 * @author haigle
 * @date 2019/7/25 13:36
 */
@RestController
@RequestMapping(Constant.ADMIN+"/menu")
public class MenuController {

    @Resource
    private MenuService menuService;

    @GetMapping("/getRouters")
    public ApiResult<List<RouterVO>> getRouters() {
        Long userId = 1L;
        List<MenuRespVO> menus = menuService.selectTreeByUserId(userId);
        return ApiResult.ok(menuService.buildMenus(menus));
    }

    @SaCheckPermission("sys-sys-menu-list")
    @GetMapping("/list")
    public ApiResult<List<MenuRespVO>> list(MenuReqVO reqVO) {
        return ApiResult.ok(menuService.list(reqVO));
    }

    @SaCheckPermission("sys-menu-detail")
    @GetMapping("/detail")
    public ApiResult<MenuRespVO> detail(@RequestParam(value = "menuId") Long menuId) {
        return ApiResult.ok(menuService.detail(menuId));
    }

    @SaCheckPermission("sys-menu-add")
    @PostMapping("/add")
    public ApiResult<Boolean> add(@RequestBody MenuSaveReqVO reqVO) {
        menuService.add(reqVO);
        return ApiResult.ok();
    }

    @SaCheckPermission("sys-menu-update")
    @PostMapping("/update")
    public ApiResult<Boolean> update(@RequestBody MenuSaveReqVO reqVO) {
        menuService.update(reqVO);
        return ApiResult.ok();
    }

    @SaCheckPermission("sys-menu-delete")
    @DeleteMapping("/delete")
    public ApiResult<Boolean> delete(@RequestParam(value = "menuId") Long menuId) {
        menuService.delete(menuId);
        return ApiResult.ok();
    }

    @SaCheckPermission("sys-user-menu-tree")
    @GetMapping("/userMenuTree")
    public ApiResult<List<MenuTreeDTO>> userMenuTree() {
        Long userId = 1L;
        return ApiResult.ok(menuService.userMenuTree(userId));
    }

    @SaCheckPermission("sys-role-menu-tree")
    @GetMapping("/roleMenuTree")
    public ApiResult<RoleMenuTreeRespVO> roleMenuTree(@RequestParam(value = "roleId") Long roleId) {
        return ApiResult.ok(menuService.roleMenuTree(roleId));
    }



}
