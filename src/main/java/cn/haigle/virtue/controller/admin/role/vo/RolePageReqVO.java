package cn.haigle.virtue.controller.admin.role.vo;

import cn.haigle.virtue.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * 管理后台 - 角色分页查询 Request VO
 * @author haigle
 * @date 2024/11/14 14:37
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Schema(description = "角色分页查询 Request VO")
public class RolePageReqVO extends PageParam {

    @Schema(description = "角色名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "角色名称")
    private String name;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "状态")
    private Integer state;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "创建时间")
    private List<String> createTime;

}
