package cn.haigle.virtue.system.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 登陆用户信息
 * @author haigle
 * @date 2020/11/28 23:34
 */
@Data
@AllArgsConstructor
public class LoginUserInfoVo {

    /** 登录凭证 */
    private String token;

}
