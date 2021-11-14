package cn.haigle.virtue.system.entity.ao;

import cn.haigle.virtue.common.base.validator.Save;
import cn.haigle.virtue.common.base.validator.Update;
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
public class AdminUserAO {

    @ApiModelProperty(value = "用户ID")
    @NotNull(message = "ID不能为空", groups = {Update.class})
    private Long id;

    @ApiModelProperty(value = "用户名", required = true, example = "用户名")
    @NotBlank(message = "用户名不能为空", groups = {Save.class, Update.class})
    private String username;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "手机号码", required = true)
    @NotNull(message = "手机号码不能为空", groups = {Save.class, Update.class})
    @Size(max=11, min=11, message = "手机号码必须11位", groups = {Save.class, Update.class})
    private String phone;

    @ApiModelProperty(value = "性别", example = "角色简介")
    private String gender;

    @ApiModelProperty(value = "角色ID列表")
    private List<Long> roleList;

}
