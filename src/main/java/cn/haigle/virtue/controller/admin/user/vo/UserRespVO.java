
package cn.haigle.virtue.controller.admin.user.vo;

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
public class UserRespVO {

    /** 用户ID */
    private Long id;

    /** 部门ID */
    private Long deptId;

    /** 用户名称 */
    private String username;

    /** 用户昵称 */
    private String nickname;

    /** 邮箱 */
    private String email;

    /** 电话 */
    private String phone;

    /**
     * 用户状态 0正常 1停用
     * {@link cn.haigle.virtue.common.constants.StateEnum}
     */
    private String state;

    /** 用户角色 */
    private List<Long> roleIds;

    /** 用户角色 */
    private List<UserRoleRespVO> roles;

}