package cn.haigle.virtue.controller.admin.user.vo;

import lombok.Data;
import java.util.List;

/**
 * 用户保存 Request VO
 * @author haigle
 * @date 2024/12/12 13:59
 */
@Data
public class UserSaveReqVO {

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

    /** 密码 */
    private String password;

    /**
     * 用户状态 0正常 1停用
     * {@link cn.haigle.virtue.common.constants.StateEnum}
     */
    private String state;

    /** 用户角色 */
    private List<Long> roleIds;

}
