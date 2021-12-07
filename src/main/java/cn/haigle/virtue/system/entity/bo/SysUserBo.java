package cn.haigle.virtue.system.entity.bo;

import cn.haigle.virtue.common.entity.EditableEntity;
import cn.haigle.virtue.common.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * 用户信息
 * @author haigle
 * @date 2021/12/3 22:51
 */
@Table(name = "sys_user")
@Entity
@Getter
@Setter
public class SysUserBo extends EditableEntity {

    /** 用户名称 */
    @Column
    private String username;

    /** 邮箱 */
    @Column
    private String email;

    /** 电话 */
    @Column
    private String phone;

    /** 密码 */
    @Column(updatable = false)
    private String password;

    /** 盐 */
    @Column(updatable = false)
    private String salt;

    /** 头像 */
    @Column
    private String avatar;

    /** 生日 */
    @Column
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "GMT+8",pattern = DateUtils.YMDHMS1)
    private LocalDateTime birth;

    /** 简介 */
    @Column
    private String introduction;

}
