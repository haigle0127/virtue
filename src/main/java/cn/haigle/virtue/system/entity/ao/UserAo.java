package cn.haigle.virtue.system.entity.ao;

import cn.haigle.virtue.common.base.validator.Save;
import cn.haigle.virtue.common.base.validator.Update;
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
public class UserAo {

    /** ID */
    @NotNull(message = "ID不能为空", groups = {Update.class})
    private Long id;

    /** 用户名 */
    @NotBlank(message = "用户名不能为空", groups = {Save.class, Update.class})
    private String username;

    /** 邮箱 */
    private String email;

    /** 手机号码 */
    @NotNull(message = "手机号码不能为空", groups = {Save.class, Update.class})
    @Size(max=11, min=11, message = "手机号码必须11位", groups = {Save.class, Update.class})
    private String phone;

    /** 性别 */
    private String gender;

    /** 角色ID列表 */
    private List<Long> roleList;

}
