package cn.haigle.virtue.controller.admin.user.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户角色 Response VO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleRespVO {

    /** 角色ID */
    private Long id;

    /** 角色名称 */
    private String name;

}
