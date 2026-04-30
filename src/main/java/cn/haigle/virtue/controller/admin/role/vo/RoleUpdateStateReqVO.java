
package cn.haigle.virtue.controller.admin.role.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 管理后台 - 角色状态更新 Request VO
 * @author haigle
 * @date 2024/11/13 下午1:34
 */
@Data
@Schema(description = "角色状态更新 Request VO")
public class RoleUpdateStateReqVO {

    @Schema(description = "编号ID")
    private Long id;

    @Schema(description = "角色状态 0正常 1停用", example = "0")
    private String state;

}
