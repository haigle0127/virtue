package com.haigle.around.admin.rbac.entity;

import com.haigle.around.common.base.validator.*;
import com.haigle.around.admin.rbac.entity.vo.AdminMenuVo;
import com.haigle.around.admin.rbac.entity.vo.AdminRoleVo;
import com.haigle.around.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 用户表
 * @author haigle
 * @date 2019/3/5 17:08
 */
@Getter
@Setter
public class UserEntity extends BaseEntity {

    @NotBlank(message = "姓名不能为空", groups = {Update.class})
    private String username;

    @NotBlank(message = "邮箱不能为空", groups = {Login.class, LoginByEmail.class, Save.class})
    @Email(message = "邮箱格式不正确", groups = {Save.class, LoginByEmail.class, SendEmailCode.class})
    private String email;
    private String phone;

    @NotBlank(message = "密码不能为空", groups = {Login.class, LoginByEmail.class, Save.class})
    @Length(min = 5, message = "密码必须大于等于六位", groups = {Save.class, LoginByEmail.class, Update.class})
    private String password;

    /**
     * 注册用户时为验证码，平时为盐
     */
    @NotBlank(message = "验证码不能为空", groups = {Save.class})
    private String salt;
    private String avatar;
    private String introduction;
    private String birth;
    private String school;
    private String education;
    private String company;
    private String token;

    private UserEntity userEntity;

    public UserEntity() {
    }

    public UserEntity(Long id) {
        super.setId(id);
    }

    public List<AdminRoleVo> roleList;
    public List<AdminMenuVo> menuList;

    /**
     * 移除密码和秘钥敏感信息
     * @return UserEntity
     * @author haigle
     * @date 2019/3/6 9:45
     */
    public UserEntity removePassword() {
        this.password = null;
        this.salt = null;
        return this;
    }

}
