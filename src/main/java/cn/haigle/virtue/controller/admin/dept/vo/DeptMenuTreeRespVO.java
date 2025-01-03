package cn.haigle.virtue.controller.admin.dept.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

/**
 * @author haigle
 * @date 2024/11/16 12:58
 */
@Schema(description = "管理后台 - 角色部门树 Response VO")
@Data
public class DeptMenuTreeRespVO {

    @NotBlank(message = "选中菜单ID列表")
    private List<Long> checkedKeys;

    @NotBlank(message = "菜单树结构")
    private List<DeptTreeDTO> menus;

}
