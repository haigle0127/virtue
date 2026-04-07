package cn.haigle.virtue.controller.admin.user.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 管理后台 - 用户基本信息 Response VO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoVO {

    /** 编号ID */
    private String username;

    /** 编号ID */
    private String email;

    /** 编号ID */
    private String phone;

    /** 编号ID */
    private String avatar;

    /** 编号ID */
    private String introduction;

    /** 编号ID */
    private String education;

    /** 角色标识 */
    private List<String> roles;

    /** 权限标识 */
    private List<String> permissions;

}
