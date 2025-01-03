package cn.haigle.virtue.controller.admin.menu.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

/**
 * @author haigle
 * @date 2024/11/13 下午1:34
 */
@Schema(description = "管理后台 - 角色菜单树 Response VO")
@Data
public class RoleMenuTreeRespVO {

    @NotBlank(message = "选中菜单ID列表")
    private List<Long> checkedKeys;

    @NotBlank(message = "菜单树结构")
    private List<MenuTreeDTO> menus;

}
