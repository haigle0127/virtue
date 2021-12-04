package cn.haigle.virtue.common.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 所有DO实体的父类
 * @author haigle
 * @date 2021/12/4 23:01
 */
@MappedSuperclass
@Getter
@Setter
@Accessors(chain = true)
public class EditableEntity extends BaseEntity {

    /** 创建人ID */
    @Column
    private Long createBy;

    /** 创建时间 */
    @Column(updatable = false)
    private LocalDateTime createTime;

    /** 更新人ID */
    @Column
    private Long updateBy;

    /** 更新时间 */
    @Column
    private LocalDateTime updateTime;
}


