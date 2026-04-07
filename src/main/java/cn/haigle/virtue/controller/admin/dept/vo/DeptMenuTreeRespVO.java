package cn.haigle.virtue.controller.admin.dept.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

/**
 * 管理后台 - 角色部门树 Response VO
 * @author haigle
 * @date 2024/11/16 12:58
 */
@Data
public class DeptMenuTreeRespVO {

    @NotBlank(message = "选中菜单ID列表")
    private List<Long> checkedKeys;

    @NotBlank(message = "菜单树结构")
    private List<DeptTreeDTO> menus;

}
