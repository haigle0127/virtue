package cn.haigle.virtue.mapper.dataobject.dict;

import cn.haigle.virtue.common.base.BaseDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@TableName("dict_data")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysDictDataDO extends BaseDO {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 排序 */
    private Integer sort;

    /** 字典标签 */
    private String label;

    /** 字典值 */
    private String value;

    /** 字典类型 */
    private String dictType;

    /** 状态（0正常 1停用） */
    private String status;

    /** 颜色类型 */
    private String colorType;

    /** 样式类名 */
    private String cssClass;

    /** 备注 */
    private String remark;
}
