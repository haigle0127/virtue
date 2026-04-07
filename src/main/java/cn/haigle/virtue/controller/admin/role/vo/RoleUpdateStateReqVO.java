
package cn.haigle.virtue.controller.admin.role.vo;

import lombok.Data;

/**
 * 管理后台 - 角色状态更新 Request VO
 * @author haigle
 * @date 2024/11/13 下午1:34
 */
@Data
public class RoleUpdateStateReqVO {

    /** 编号ID */
    private Long id;

    /**
     * 角色状态 0正常 1停用
     * {@link cn.haigle.virtue.common.constants.StateEnum}
     */
    private String state;

}
