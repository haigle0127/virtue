package cn.haigle.virtue.controller.admin.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 用户保存 Request VO
 *
 * @author haigle
 */
@Data
@Schema(description = "用户保存 Request VO")
public class UserSaveReqVO {

    @Schema(description = "用户ID")
    private Long id;

    @Schema(description = "用户名称")
    private String username;

    @Schema(description = "用户昵称")
    private String nickname;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "电话")
    private String phone;

    @Schema(description = "手机号码（mobile 别名，同 phone）")
    private String mobile;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "用户状态 0正常 1停用", example = "0")
    private Integer status;

    @Schema(description = "用户角色ID列表")
    private List<Long> roleIds;

}
