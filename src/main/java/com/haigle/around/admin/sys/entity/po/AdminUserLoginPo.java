package com.haigle.around.admin.sys.entity.po;

import lombok.Data;

/**
 * 登录传输数据
 * @author haigle
 * @date 2019/6/21 9:22
 */
@Data
public class AdminUserLoginPo {

    private Long id;
    private String username;
    private String email;
    private String phone;
    private String password;
    private String salt;

}
