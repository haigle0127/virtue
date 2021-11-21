package cn.haigle.virtue.system.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.haigle.virtue.common.base.page.Page;
import cn.haigle.virtue.system.entity.ao.UserAo;
import cn.haigle.virtue.system.entity.vo.TreeVo;
import cn.haigle.virtue.system.entity.vo.UserVo;
import cn.haigle.virtue.system.service.UserService;
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
 * 用户管理
 * @author haigle
 * @date 2019/7/25 13:36
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping(Constant.ADMIN+"/user")
public class UserController {

    @Resource(name = "userService")
    private UserService userService;

    /**
     * 管理用户列表
     * @author haigle
     * @date 2019/8/19 9:03
     */
    @ApiOperation("用户列表")
    @PostMapping("/list")
    public ApiResult<Page<UserVo>> list(@RequestBody NameQuery searchNameQuery) {
        return ApiResult.ok(userService.list(searchNameQuery));
    }

    /**
     * 录入用户
     * @author haigle
     * @date 2019/9/5 10:08
     */
    @ApiOperation("添加用户")
    @PostMapping("/save")
    public ApiResult<String> save(@Validated(Save.class) @RequestBody UserAo userAo) {
        userService.save(userAo, StpUtil.getLoginIdAsLong());
        return ApiResult.ok();
    }

    /**
     * 更新用户
     * @author haigle
     * @date 2019/9/6 8:45
     */
    @ApiOperation("更新用户")
    @PostMapping("/update")
    public ApiResult<String> update(@Validated(Update.class) @RequestBody UserAo userAo) {
        userService.update(userAo, StpUtil.getLoginIdAsLong());
        return ApiResult.ok();
    }

    /**
     * 删除用户
     * @author haigle
     * @date 2019/9/6 9:03
     */
    @ApiOperation("删除用户")
    @PostMapping("/delete")
    public ApiResult<String> delete(@NotNull(message = "common.id.not_blank") @RequestParam("id") Long id) {
        userService.delete(id);
        return ApiResult.ok();
    }

    /**
     * 角色所有结构
     * @author haigle
     * @date 2019/9/9 9:26
     */
    @ApiOperation("角色所有结构")
    @PostMapping("/getRoleAllList")
    public ApiResult<List<TreeVo>> getRoleAllList() {
        return ApiResult.ok(userService.roleAllList());
    }

    /**
     * 用户下角色ID
     * @author haigle
     * @date 2019/9/9 10:31
     */
    @ApiOperation("用户下角色ID")
    @PostMapping("/getUserRoleList")
    public ApiResult<List<Long>> getUserRoleList(@NotNull(message = "common.id.not_blank") @RequestParam("id") Long id) {
        return ApiResult.ok(userService.getUserRoleList(id));
    }

}
