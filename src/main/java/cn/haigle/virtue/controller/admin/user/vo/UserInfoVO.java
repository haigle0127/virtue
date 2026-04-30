package cn.haigle.virtue.controller.admin.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "用户基本信息 Response VO")
public class UserInfoVO {

    @Schema(description = "用户名称")
    private String username;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "电话")
    private String phone;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "简介")
    private String introduction;

    @Schema(description = "教育背景")
    private String education;

    @Schema(description = "角色标识列表")
    private List<String> roles;

    @Schema(description = "权限标识列表")
    private List<String> permissions;

}
