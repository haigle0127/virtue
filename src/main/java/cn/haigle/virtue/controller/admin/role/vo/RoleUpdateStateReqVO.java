
package cn.haigle.virtue.controller.admin.role.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author haigle
 * @date 2024/11/13 下午1:34
 */
@Schema(description = "管理后台 - 角色状态更新 Request VO")
@Data
public class RoleUpdateStateReqVO {

    @Schema(description = "编号ID")
    private Long id;

    /**
     * 角色状态 0正常 1停用
     * {@link cn.haigle.virtue.common.constants.StateEnum}
     */
    @Schema(description = "角色状态 0正常 1停用")
    private String state;

}
