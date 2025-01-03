package cn.haigle.virtue.controller.admin.menu.vo;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 菜单树结构
 */
@Data
public class MenuTreeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 节点ID
     */
    private Long id;

    /**
     * 节点名称
     */
    private String label;

    /**
     * 子节点
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<MenuTreeDTO> children;

    public MenuTreeDTO(){}

    public MenuTreeDTO(MenuRespVO menu) {
        this.id = menu.getId();
        this.label = menu.getName();
        this.children = menu.getChildren().stream().map(MenuTreeDTO::new).collect(Collectors.toList());
    }
}
