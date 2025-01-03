package cn.haigle.virtue.controller.admin.menu.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author haigle
 * @date 2024/11/13 下午1:34
 */
@Schema(description = "管理后台 - 菜单列表查询 Request VO")
@Data
public class MenuReqVO {

    @NotBlank(message = "菜单名称")
    private String name;

    @NotBlank(message = "状态")
    private Integer state;

}
