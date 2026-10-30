package cn.haigle.virtue.controller.admin.dict;

import cn.haigle.virtue.common.pojo.ApiResult;
import cn.haigle.virtue.common.pojo.PageResult;
import cn.haigle.virtue.controller.admin.dict.vo.DictDataPageReqVO;
import cn.haigle.virtue.controller.admin.dict.vo.DictDataRespVO;
import cn.haigle.virtue.controller.admin.dict.vo.DictDataSaveReqVO;
import cn.haigle.virtue.service.dict.DictDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 字典数据管理
 *
 * @author haigle
 */
@Tag(name = "字典数据管理")
@RestController
@RequestMapping("/dict-data")
@PreAuthorize("isAuthenticated()")
public class DictDataController {

    @Resource
    private DictDataService dictDataService;

    @Operation(summary = "获取字典数据精简列表")
    @GetMapping("/simple-list")
    public ApiResult<List<?>> simpleList() {
        return ApiResult.ok(new ArrayList<>());
    }

    @Operation(summary = "获取字典数据分页")
    @GetMapping("/page")
    public ApiResult<PageResult<DictDataRespVO>> page(DictDataPageReqVO reqVO) {
        return ApiResult.ok(dictDataService.page(reqVO));
    }

    @Operation(summary = "获取字典数据详情")
    @GetMapping("/get")
    public ApiResult<DictDataRespVO> get(@RequestParam("id") Long id) {
        return ApiResult.ok(dictDataService.detail(id));
    }

    @Operation(summary = "根据字典类型获取字典数据")
    @GetMapping("/type")
    public ApiResult<List<DictDataRespVO>> getByType(@RequestParam("type") String type) {
        return ApiResult.ok(dictDataService.listByDictType(type));
    }

    @Operation(summary = "新增字典数据")
    @PostMapping("/create")
    public ApiResult<Boolean> create(@RequestBody @Valid DictDataSaveReqVO reqVO) {
        dictDataService.add(reqVO);
        return ApiResult.ok();
    }

    @Operation(summary = "修改字典数据")
    @PutMapping("/update")
    public ApiResult<Boolean> update(@RequestBody @Valid DictDataSaveReqVO reqVO) {
        dictDataService.update(reqVO);
        return ApiResult.ok();
    }

    @Operation(summary = "删除字典数据")
    @DeleteMapping("/delete")
    public ApiResult<Boolean> delete(@RequestParam("id") Long id) {
        dictDataService.delete(id);
        return ApiResult.ok();
    }

    @Operation(summary = "批量删除字典数据")
    @DeleteMapping("/delete-list")
    public ApiResult<Boolean> deleteList(@RequestParam("ids") List<Long> ids) {
        if (ids != null) {
            ids.forEach(dictDataService::delete);
        }
        return ApiResult.ok();
    }

    @Operation(summary = "导出字典数据")
    @GetMapping("/export-excel")
    public ApiResult<List<?>> exportExcel(DictDataPageReqVO reqVO) {
        return ApiResult.ok(new ArrayList<>());
    }
}
