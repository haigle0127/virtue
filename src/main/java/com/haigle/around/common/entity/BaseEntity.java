package com.haigle.around.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.haigle.around.common.base.validator.Delete;
import com.haigle.around.common.base.validator.Id;
import com.haigle.around.common.util.DateUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 实体bean的基类
 * @author haigle
 * @date 2018/12/5 15:31
 */
@EqualsAndHashCode(exclude = {"id"})
@Data
public class BaseEntity{

    @org.springframework.data.annotation.Id
    @NotNull(message = "common.delete.primaryKey.null", groups = {Delete.class})
    @NotNull(message = "common.operate.primaryKey.null", groups = {Id.class})
    private Long id;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "GMT+8",pattern = DateUtils.YMDHMS1)
    private LocalDateTime createTime;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "GMT+8",pattern = DateUtils.YMDHMS1)
    private LocalDateTime  updateTime;

    private boolean isDeleted = false;

    protected BaseEntity() {
    }

}
