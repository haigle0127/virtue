package cn.haigle.virtue.mapper.dataobject.role;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 角色部门关联数据
 * @author haigle
 * @date 2019-08-04 10:40
 */
@TableName("sys_role_dept")
@Data
public class SysRoleDeptDO {

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 部门ID
     */
    private Long deptId;

}
