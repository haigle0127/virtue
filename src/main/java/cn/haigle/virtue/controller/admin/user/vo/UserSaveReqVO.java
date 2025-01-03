package cn.haigle.virtue.controller.admin.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

/**
 * @author haigle
 * @date 2024/12/12 13:59
 */
@Schema(description = "用户保存 Request VO")
@Data
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

    /**
     * 用户状态 0正常 1停用
     * {@link cn.haigle.virtue.common.constants.StateEnum}
     */
    @Schema(description = "角色状态 0正常 1停用")
    private String state;

    @Schema(description = "用户角色")
    private List<Long> roleIds;

}
