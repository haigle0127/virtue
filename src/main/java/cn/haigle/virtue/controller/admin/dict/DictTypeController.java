package cn.haigle.virtue.controller.admin.dict;

import cn.haigle.virtue.common.pojo.ApiResult;
import cn.haigle.virtue.common.pojo.PageResult;
import cn.haigle.virtue.controller.admin.dict.vo.DictTypePageReqVO;
import cn.haigle.virtue.controller.admin.dict.vo.DictTypeRespVO;
import cn.haigle.virtue.controller.admin.dict.vo.DictTypeSaveReqVO;
import cn.haigle.virtue.service.dict.DictTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 字典类型管理
 *
 * @author haigle
 */
@Tag(name = "字典类型管理")
@RestController
@RequestMapping("/dict-type")
@PreAuthorize("isAuthenticated()")
public class DictTypeController {

    @Resource
    private DictTypeService dictTypeService;

    @Operation(summary = "获取字典类型精简列表")
    @GetMapping("/simple-list")
    public ApiResult<List<DictTypeRespVO>> simpleList() {
        return ApiResult.ok(dictTypeService.simpleList());
    }

    @Operation(summary = "获取字典类型分页")
    @GetMapping("/page")
    public ApiResult<PageResult<DictTypeRespVO>> page(DictTypePageReqVO reqVO) {
        return ApiResult.ok(dictTypeService.page(reqVO));
    }

    @Operation(summary = "获取字典类型详情")
    @GetMapping("/get")
    public ApiResult<DictTypeRespVO> get(@RequestParam("id") Long id) {
        return ApiResult.ok(dictTypeService.detail(id));
    }

    @Operation(summary = "新增字典类型")
    @PostMapping("/create")
    public ApiResult<Boolean> create(@RequestBody @Valid DictTypeSaveReqVO reqVO) {
        dictTypeService.add(reqVO);
        return ApiResult.ok();
    }

    @Operation(summary = "修改字典类型")
    @PutMapping("/update")
    public ApiResult<Boolean> update(@RequestBody @Valid DictTypeSaveReqVO reqVO) {
        dictTypeService.update(reqVO);
        return ApiResult.ok();
    }

    @Operation(summary = "删除字典类型")
    @DeleteMapping("/delete")
    public ApiResult<Boolean> delete(@RequestParam("id") Long id) {
        dictTypeService.delete(id);
        return ApiResult.ok();
    }

    @Operation(summary = "批量删除字典类型")
    @DeleteMapping("/delete-list")
    public ApiResult<Boolean> deleteList(@RequestParam("ids") List<Long> ids) {
        if (ids != null) {
            ids.forEach(dictTypeService::delete);
        }
        return ApiResult.ok();
    }

    @Operation(summary = "导出字典类型")
    @GetMapping("/export-excel")
    public ApiResult<List<?>> exportExcel(DictTypePageReqVO reqVO) {
        return ApiResult.ok(new ArrayList<>());
    }
}
