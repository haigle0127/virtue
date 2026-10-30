package cn.haigle.virtue.mapper.dataobject.dict;

import cn.haigle.virtue.common.base.BaseDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@TableName("dict_type")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysDictTypeDO extends BaseDO {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 字典名称 */
    private String name;

    /** 字典类型 */
    private String type;

    /** 状态（0正常 1停用） */
    private String status;

    /** 备注 */
    private String remark;
}
