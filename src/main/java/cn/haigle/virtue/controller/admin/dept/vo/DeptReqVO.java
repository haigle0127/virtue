package cn.haigle.virtue.controller.admin.dept.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author haigle
 * @date 2024/11/16 12:49
 */
@Schema(description = "管理后台 - 部门列表查询 Request VO")
@Data
public class DeptReqVO {

    @NotBlank(message = "部门名称")
    private String name;

    @NotBlank(message = "状态")
    private Integer state;

}
