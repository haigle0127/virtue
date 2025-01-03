package cn.haigle.virtue.mapper.dataobject.role;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 角色菜单关联数据
 * @author haigle
 * @date 2019-08-04 10:40
 */
@TableName("sys_role_menu")
@Data
public class SysRoleMenuDO {

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;

}
