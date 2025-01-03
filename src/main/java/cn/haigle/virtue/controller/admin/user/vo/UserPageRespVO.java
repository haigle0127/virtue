
package cn.haigle.virtue.controller.admin.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "用户分页 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPageRespVO {

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

    /**
     * 用户状态 0正常 1停用
     * {@link cn.haigle.virtue.common.constants.StateEnum}
     */
    @Schema(description = "角色状态 0正常 1停用")
    private String state;

    @Schema(description = "创建时间")
    private String createTime;

}