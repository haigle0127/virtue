package cn.haigle.virtue.common.entity;

import cn.haigle.virtue.system.entity.bo.SysUserBo;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 所有DO实体的父类
 * @author haigle
 * @date 2021/12/4 23:01
 */
@MappedSuperclass
@Getter
@Setter
@Accessors(chain = true)
public class EditableUserEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "created_by", insertable = false, updatable = false)
    private SysUserBo createdByUser;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "updated_by", insertable = false, updatable = false)
    private SysUserBo updatedByUser;
}


