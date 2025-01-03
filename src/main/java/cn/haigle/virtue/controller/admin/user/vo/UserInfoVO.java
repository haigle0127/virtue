package cn.haigle.virtue.controller.admin.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "管理后台 - 用户基本信息 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoVO {

    @Schema(description = "编号ID")
    private String username;

    @Schema(description = "编号ID")
    private String email;

    @Schema(description = "编号ID")
    private String phone;

    @Schema(description = "编号ID")
    private String avatar;

    @Schema(description = "编号ID")
    private String introduction;

    @Schema(description = "编号ID")
    private String education;

    @Schema(description = "角色标识")
    private List<String> roles;

    @Schema(description = "权限标识")
    private List<String> permissions;

}
