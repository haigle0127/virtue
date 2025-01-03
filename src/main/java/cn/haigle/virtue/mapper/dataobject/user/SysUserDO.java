package cn.haigle.virtue.mapper.dataobject.user;

import cn.haigle.virtue.common.base.BaseDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户信息
 * @author haigle
 * @date 2021/12/3 22:51
 */
@TableName("sys_user")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserDO extends BaseDO {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话
     */
    private String phone;

    /**
     * 用户状态 0正常 1停用
     * {@link cn.haigle.virtue.common.constants.StateEnum}
     */
    private String state;

    /**
     * 密码
     */
    private String password;

}
