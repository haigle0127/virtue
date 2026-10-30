package cn.haigle.virtue.controller.admin.role.vo;

import cn.haigle.virtue.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * 管理后台 - 角色分页查询 Request VO
 *
 * @author haigle
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Schema(description = "角色分页查询 Request VO")
public class RolePageReqVO extends PageParam {

    @Schema(description = "角色名称")
    private String name;

    @Schema(description = "角色标识")
    private String code;

    @Schema(description = "状态 0正常 1停用")
    private Integer status;

    @Schema(description = "创建时间")
    private List<String> createTime;

}
