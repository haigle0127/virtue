package cn.haigle.virtue.mapper.dataobject.role;

import cn.haigle.virtue.common.base.BaseDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色数据
 * @author haigle
 * @date 2019-08-04 10:40
 */
@TableName("role_info")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRoleDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 角色标识，鉴权唯一依据，非空且唯一
     */
    private String mark;

    /**
     * 角色说明
     */
    private String description;

    /**
     * 角色状态 0正常 1停用
     * {@link cn.haigle.virtue.common.constants.StateEnum}
     */
    private String state;

    /**
     * 排序
     */
    private String sort;

}
