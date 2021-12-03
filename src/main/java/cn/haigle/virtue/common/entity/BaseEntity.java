package cn.haigle.virtue.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import cn.haigle.virtue.common.base.validator.Delete;
import cn.haigle.virtue.common.util.DateUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 实体bean的基类
 * @author haigle
 * @date 2018/12/5 15:31
 */
@Accessors(chain = true)
@Getter
@Setter
@MappedSuperclass
public class BaseEntity{

    /** 主键ID */
    @Id
    private Long id;

    /** 创建人ID */
    @Column
    private Long createBy;

    /** 创建时间 */
    @Column
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "GMT+8",pattern = DateUtils.YMDHMS1)
    private LocalDateTime createTime;

    /** 更新人ID */
    @Column
    private LocalDateTime  updateBy;

    /** 更新时间 */
    @Column
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "GMT+8",pattern = DateUtils.YMDHMS1)
    private LocalDateTime  updateTime;

    /** 是否删除 */
    private boolean isDeleted = false;

    /** 版本 */
    @Version
    private Long revision;

}
