package cn.haigle.virtue.admin.login.entity.bo;

import lombok.Data;

/**
 * 注册接口实体
 * @author haigle
 * @date 2019/6/21 9:44
 */
@Data
public class AdminRegisterBO {

    private Long id;
    private String username;
    private String email;
    private String phone;
    private String password;
    private String salt;


}
