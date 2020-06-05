package com.haigle.around.admin.rbac.entity.ao;

import com.haigle.around.common.base.validator.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 角色数据
 * @author haigle
 * @date 2019-08-04 10:40
 */
@Data
public class AdminRoleAo {

    @ApiModelProperty(value = "角色ID")
    @NotNull(message = "common.id.not_null", groups = {Update.class})
    private Long id;

    @ApiModelProperty(value = "角色名", required = true, example = "管理员")
    @NotBlank(message = "role.name.not_blank", groups = {Save.class, Update.class})
    @Size(max = 20,min = 1,message = "role.name.max20", groups = {Save.class, Update.class})
    private String name;

    @ApiModelProperty(value = "角色别名", required = true)
    @NotBlank(message = "role.ename.not_blank", groups = {Save.class, Update.class})
    @Size(max = 20,min = 1,message = "role.ename.max20", groups = {Save.class, Update.class})
    private String ename;

    @ApiModelProperty(value = "角色简介", example = "角色简介")
    @Size(max = 150, message = "role.description.max150", groups = {Save.class, Update.class})
    private String description;

    @ApiModelProperty(value = "权限ID列表")
    private List<Long> menuList;

}
