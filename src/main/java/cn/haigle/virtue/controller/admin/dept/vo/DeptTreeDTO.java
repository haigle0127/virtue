package cn.haigle.virtue.controller.admin.dept.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 部门树结构
 */
@Data
public class DeptTreeDTO implements Serializable {

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
    private List<DeptTreeDTO> children;

    public DeptTreeDTO(){}

    public DeptTreeDTO(DeptRespVO dept) {
        this.id = dept.getId();
        this.label = dept.getName();
        this.children = dept.getChildren().stream().map(DeptTreeDTO::new).collect(Collectors.toList());
    }
}
