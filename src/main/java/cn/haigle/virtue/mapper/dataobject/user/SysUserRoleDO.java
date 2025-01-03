package cn.haigle.virtue.mapper.dataobject.user;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户信息
 * @author haigle
 * @date 2021/12/3 22:51
 */
@TableName("sys_user_role")
@Data
public class SysUserRoleDO {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;

}
