package cn.haigle.virtue.controller.admin.dept;

import cn.haigle.virtue.common.pojo.ApiResult;
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
public class DeptController {

    @Resource
    private DeptService deptService;

    @GetMapping("/list")
    public ApiResult<List<DeptRespVO>> list(DeptReqVO reqVO) {
        return ApiResult.ok(deptService.list(reqVO));
    }

    @GetMapping("/detail")
    public ApiResult<DeptRespVO> detail(@RequestParam(value = "deptId") Long deptId) {
        return ApiResult.ok(deptService.detail(deptId));
    }

    @PostMapping("/add")
    public ApiResult<Boolean> add(@RequestBody DeptSaveReqVO reqVO) {
        deptService.add(reqVO);
        return ApiResult.ok();
    }

    @PostMapping("/update")
    public ApiResult<Boolean> update(@RequestBody DeptSaveReqVO reqVO) {
        deptService.update(reqVO);
        return ApiResult.ok();
    }

    @DeleteMapping("/delete")
    public ApiResult<Boolean> delete(@RequestParam(value = "deptId") Long deptId) {
        deptService.delete(deptId);
        return ApiResult.ok();
    }

    @GetMapping("/userDeptTree")
    public ApiResult<List<DeptTreeDTO>> userDeptTree() {
        Long userId = 1L;
        return ApiResult.ok(deptService.userDeptTree(userId));
    }

    @GetMapping("/roleDeptTree")
    public ApiResult<RoleDeptTreeRespVO> roleDeptTree(@RequestParam(value = "roleId") Long roleId) {
        return ApiResult.ok(deptService.roleDeptTree(roleId));
    }



}
