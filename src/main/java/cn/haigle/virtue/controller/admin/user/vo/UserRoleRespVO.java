package cn.haigle.virtue.controller.admin.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户角色 Response VO
 *
 * @author haigle
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "用户角色 Response VO")
public class UserRoleRespVO {

    @Schema(description = "角色ID")
    private Long id;

    @Schema(description = "角色名称")
    private String name;

}
