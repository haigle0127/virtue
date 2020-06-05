package com.haigle.around.admin.rbac.entity.ao;

import com.haigle.around.common.base.validator.Save;
import com.haigle.around.common.base.validator.Update;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 用户数据
 * @author haigle
 * @date 2019/9/5 10:29
 */
@Data
public class AdminUserAo {

    @ApiModelProperty(value = "用户ID")
    @NotNull(message = "common.id.not_null", groups = {Update.class})
    private Long id;

    @ApiModelProperty(value = "用户名", required = true, example = "用户名")
    @NotBlank(message = "user.name.not_blank", groups = {Save.class, Update.class})
    private String username;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "手机号码", required = true)
    @NotNull(message = "user.phone.not_blank", groups = {Save.class, Update.class})
    @Size(max=11, min=11, message = "user.phone.size11", groups = {Save.class, Update.class})
    private String phone;

    @ApiModelProperty(value = "性别", example = "角色简介")
    private String gender;

    @ApiModelProperty(value = "角色ID列表")
    private List<Long> roleList;

}
