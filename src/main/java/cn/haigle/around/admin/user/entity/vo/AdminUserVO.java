package cn.haigle.around.admin.user.entity.vo;

import cn.haigle.around.common.entity.BaseEntity;
import cn.haigle.around.common.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户视图表
 * @author haigle
 * @date 2019/3/5 17:08
 */
@Getter
@Setter
public class AdminUserVO extends BaseEntity {

    private String username;
    private String email;
    private String phone;
    private String avatar;
    private String introduction;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "GMT+8",pattern = DateUtils.YMDHMS1)
    private LocalDateTime birth;

    private String education;

    public AdminUserVO() {
    }

    public AdminUserVO(Long id) {
        super.setId(id);
    }

    /**
     * 权限数组
     */
    public List<String> roles;

}
