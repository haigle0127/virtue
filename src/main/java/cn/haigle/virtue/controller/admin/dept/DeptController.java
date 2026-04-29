package cn.haigle.virtue.controller.admin.dept;

import cn.haigle.virtue.common.pojo.ApiResult;
import cn.haigle.virtue.common.security.annotation.RequireLogin;
import cn.haigle.virtue.common.security.context.SecurityContext;
import cn.haigle.virtue.common.web.Constant;
import cn.haigle.virtue.controller.admin.dept.vo.*;
import cn.haigle.virtue.service.dept.DeptService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理
 * @author haigle
 * @date 2024/11/16 11:26
 */
@RestController
@RequestMapping(Constant.ADMIN+"/dept")
@RequireLogin
public class DeptController {

    @Resource
    private DeptService deptService;

    /**
     * 获取部门列表
     * <p>根据查询条件获取部门列表，支持树形结构展示</p>
     *
     * @param reqVO 部门查询请求参数
     * @return 部门列表响应对象集合
     */
    @GetMapping("/list")
    public ApiResult<List<DeptRespVO>> list(DeptReqVO reqVO) {
        return ApiResult.ok(deptService.list(reqVO));
    }

    /**
     * 获取部门详情
     * <p>根据部门ID获取部门详细信息</p>
     *
     * @param deptId 部门ID
     * @return 部门详情响应对象
     */
    @GetMapping("/detail")
    public ApiResult<DeptRespVO> detail(@RequestParam(value = "deptId") Long deptId) {
        return ApiResult.ok(deptService.detail(deptId));
    }

    /**
     * 新增部门
     * <p>创建新的部门信息</p>
     *
     * @param reqVO 部门保存请求参数
     * @return 操作结果，成功返回true
     */
    @PostMapping("/add")
    public ApiResult<Boolean> add(@RequestBody DeptSaveReqVO reqVO) {
        deptService.add(reqVO);
        return ApiResult.ok();
    }

    /**
     * 修改部门
     * <p>更新已存在的部门信息</p>
     *
     * @param reqVO 部门保存请求参数
     * @return 操作结果，成功返回true
     */
    @PostMapping("/update")
    public ApiResult<Boolean> update(@RequestBody DeptSaveReqVO reqVO) {
        deptService.update(reqVO);
        return ApiResult.ok();
    }

    /**
     * 删除部门
     * <p>根据部门ID删除部门信息</p>
     *
     * @param deptId 部门ID
     * @return 操作结果，成功返回true
     */
    @DeleteMapping("/delete")
    public ApiResult<Boolean> delete(@RequestParam(value = "deptId") Long deptId) {
        deptService.delete(deptId);
        return ApiResult.ok();
    }

    /**
     * 获取用户部门树
     * <p>根据当前登录用户ID获取其所属的部门树形结构</p>
     *
     * @return 部门树DTO集合
     */
    @GetMapping("/userDeptTree")
    public ApiResult<List<DeptTreeDTO>> userDeptTree() {
        Long userId = SecurityContext.getUserId();
        return ApiResult.ok(deptService.userDeptTree(userId));
    }

    /**
     * 获取角色部门树
     * <p>根据角色ID获取该角色关联的部门树形结构</p>
     *
     * @param roleId 角色ID
     * @return 角色部门树响应对象
     */
    @GetMapping("/roleDeptTree")
    public ApiResult<RoleDeptTreeRespVO> roleDeptTree(@RequestParam(value = "roleId") Long roleId) {
        return ApiResult.ok(deptService.roleDeptTree(roleId));
    }



}
