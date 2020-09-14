package cn.haigle.around.admin.menu.entity.ao;

import cn.haigle.around.common.base.validator.Save;
import cn.haigle.around.common.base.validator.Update;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 权限菜单接口实体
 * @author haigle
 * @date 2019-06-09 14:15
 */
@Getter
@Setter
public class AdminMenuAO {

    @ApiModelProperty(value = "菜单ID")
    @NotNull(message = "common.id.not_null", groups = {Update.class})
    private Long id;

    @ApiModelProperty(value = "菜单父级ID")
    @Min(value = 0, message = "menu.parent_id.min0", groups = {Save.class, Update.class})
    private Long parentId;

    @ApiModelProperty(value = "菜单名称", required = true)
    @NotBlank(message = "menu.name.not_blank", groups = {Save.class, Update.class})
    @Size(max = 20, min = 1,message = "menu.name.max20", groups = {Save.class, Update.class})
    private String name;

    @ApiModelProperty(value = "菜单别名", required = true)
    @NotBlank(message = "menu.ename.not_blank", groups = {Save.class, Update.class})
    @Size(max = 20, min = 1, message = "menu.ename.max20", groups = {Save.class, Update.class})
    private String ename;

    @ApiModelProperty(value = "权限标识")
    @NotBlank(message = "menu.power.not_blank", groups = {Save.class, Update.class})
    private String power;

    @ApiModelProperty(value = "权限简介", example = "权限简介")
    @Size(max = 150, message = "menu.description.max150", groups = {Save.class, Update.class})
    private String description;

}
