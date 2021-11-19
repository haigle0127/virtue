package cn.haigle.virtue.system.entity.vo;

import cn.haigle.virtue.common.entity.BaseVo;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户角色
 * @author haigle
 * @date 2019-06-09 14:11
 */
@Getter
@Setter
public class AdminRoleVo extends BaseVo {

    private String name;
    private String ename;
    private String description;

}
