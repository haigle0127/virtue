package cn.haigle.virtue.system.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.haigle.virtue.system.entity.ao.MenuAo;
import cn.haigle.virtue.system.entity.vo.MenuVo;
import cn.haigle.virtue.system.service.MenuService;
import cn.haigle.virtue.common.base.validator.Save;
import cn.haigle.virtue.common.base.validator.Update;
import cn.haigle.virtue.common.interceptor.model.ApiResult;
import cn.haigle.virtue.config.Constant;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 菜单管理
 * @author haigle
 * @date 2019/7/25 13:36
 */
@RestController
@RequestMapping(Constant.ADMIN+"/menu")
public class MenuController {

    @Resource(name = "menuService")
    private MenuService menuService;

    /**
     * 菜单列表
     * @author haigle
     * @date 2019/8/23 9:16
     */
    @PostMapping("/list")
    public ApiResult<List<MenuVo>> list(@NotNull(message = "common.id.not_blank") @RequestParam("id") Long id) {
        return ApiResult.ok(menuService.list(id));
    }

    /**
     * 保存菜单
     * @author haigle
     * @date 2019/9/6 9:01
     */
    @PostMapping("/save")
    public ApiResult<String> save(@Validated(Save.class) @RequestBody MenuAo menuAo) {
        menuService.save(menuAo, StpUtil.getLoginIdAsLong());
        return ApiResult.ok();
    }

    /**
     * 更新菜单
     * @author haigle
     * @date 2019/9/6 9:01
     */
    @PostMapping("/update")
    public ApiResult<String> update(@Validated(Update.class) @RequestBody MenuAo menuAo) {
        menuService.update(menuAo, StpUtil.getLoginIdAsLong());
        return ApiResult.ok();
    }

    /**
     * 删除菜单
     * @author haigle
     * @date 2019/9/6 9:01
     */
    @PostMapping("/delete")
    public ApiResult<String> delete(@NotNull(message = "common.id.not_blank") @RequestParam("id") Long id) {
        menuService.delete(id);
        return ApiResult.ok();
    }

}
