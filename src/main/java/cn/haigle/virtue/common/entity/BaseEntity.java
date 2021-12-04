package cn.haigle.virtue.common.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * 实体bean的基类
 * @author haigle
 * @date 2018/12/5 15:31
 */
@MappedSuperclass
@Getter
@Setter
@Accessors(chain = true)
public class BaseEntity{

    /** 主键ID */
    @Id
    private Long id;

    /** 是否删除 */
    private boolean isDeleted = false;

    /** 版本 */
    @Version
    private Long revision;

}
