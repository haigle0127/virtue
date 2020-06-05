package com.haigle.around.admin.rbac.entity.dto;

import lombok.Builder;
import lombok.Data;
import java.util.List;

/**
 * 用户数据
 * @author haigle
 * @date 2019/9/5 10:29
 */
@Builder
@Data
public class AdminUserDto {

    private Long id;
    private String username;
    private String password;
    private String salt;
    private String email;
    private String phone;
    private String gender;
    private List<Long> roleList;

}
