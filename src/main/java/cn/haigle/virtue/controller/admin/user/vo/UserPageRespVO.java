
package cn.haigle.virtue.controller.admin.user.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户分页 Response VO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPageRespVO {

    /** 用户ID */
    private Long id;

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

    /** 创建时间 */
    private String createTime;

}