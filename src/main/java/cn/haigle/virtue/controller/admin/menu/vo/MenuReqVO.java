package cn.haigle.virtue.controller.admin.menu.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 管理后台 - 菜单列表查询 Request VO
 * @author haigle
 * @date 2024/11/13 下午1:34
 */
@Data
public class MenuReqVO {

    @NotBlank(message = "菜单名称")
    private String name;

    @NotBlank(message = "状态")
    private Integer state;

}
