
package cn.haigle.virtue.controller.admin.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 用户详情 Response VO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "用户详情 Response VO")
public class UserRespVO {

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

    @Schema(description = "用户状态 0正常 1停用", example = "0")
    private String state;

    @Schema(description = "用户角色ID列表")
    private List<Long> roleIds;

    @Schema(description = "用户角色列表")
    private List<UserRoleRespVO> roles;

}
