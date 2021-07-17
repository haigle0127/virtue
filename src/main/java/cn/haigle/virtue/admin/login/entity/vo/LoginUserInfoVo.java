package cn.haigle.virtue.admin.login.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 登陆用户信息
 * @author haigle
 * @date 2020/11/28 23:34
 */
@ApiModel
@Data
@AllArgsConstructor
public class LoginUserInfoVo {

    @ApiModelProperty(value = "登录凭证")
    private String token;

}
