package cn.haigle.virtue.system.entity.vo;

import cn.haigle.virtue.common.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户视图和权限列表
 * @author haigle
 * @date 2019/3/5 17:08
 */
@Accessors(chain = true)
@Data
public class UserInfoVo {

    /** 账号 */
    private String username;

    /** 邮箱 */
    private String email;

    /** 电话 */
    private String phone;

    /** 头像 */
    private String avatar;

    /** 简介 */
    private String introduction;

    /** 性别 */
    private String gender;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "GMT+8",pattern = DateUtils.YMDHMS1)
    private LocalDateTime birth;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "GMT+8",pattern = DateUtils.YMDHMS1)
    private LocalDateTime createTime;

    /**
     * 角色数组
     */
    private List<String> roles;

}
