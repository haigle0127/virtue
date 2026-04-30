package cn.haigle.virtue.controller.admin.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

/**
 * 用户保存 Request VO
 * @author haigle
 * @date 2024/12/12 13:59
 */
@Data
@Schema(description = "用户保存 Request VO")
public class UserSaveReqVO {

    @Schema(description = "用户ID")
    private Long id;

    @Schema(description = "部门ID")
    private Long deptId;

    @Schema(description = "用户名称")
    private String username;

    @Schema(description = "用户昵称")
    private String nickname;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "电话")
    private String phone;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "用户状态 0正常 1停用", example = "0")
    private String state;

    @Schema(description = "用户角色")
    private List<Long> roleIds;

}
