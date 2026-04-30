package cn.haigle.virtue.controller.admin.dept.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

/**
 * 管理后台 - 角色部门树 Response VO
 * @author haigle
 * @date 2024/11/16 12:58
 */
@Data
@Schema(description = "角色部门树 Response VO")
public class DeptMenuTreeRespVO {

    @Schema(description = "选中菜单ID列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "选中菜单ID列表")
    private List<Long> checkedKeys;

    @Schema(description = "菜单树结构", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "菜单树结构")
    private List<DeptTreeDTO> menus;

}
