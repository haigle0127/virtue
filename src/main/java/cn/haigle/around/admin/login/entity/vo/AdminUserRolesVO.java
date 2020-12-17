package cn.haigle.around.admin.login.entity.vo;

import cn.haigle.around.common.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * 用户视图和权限列表
 * @author haigle
 * @date 2019/3/5 17:08
 */
@Accessors(chain = true)
@Data
public class AdminUserRolesVO {

    private String username;
    private String email;
    private String phone;
    private String avatar;
    private String introduction;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "GMT+8",pattern = DateUtils.YMDHMS1)
    private LocalDateTime birth;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "GMT+8",pattern = DateUtils.YMDHMS1)
    private LocalDateTime createTime;

    /**
     * 权限数组
     */
    private Set<String> roles;

}
