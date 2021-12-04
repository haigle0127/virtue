package cn.haigle.virtue.system.entity.vo;

import cn.haigle.virtue.common.entity.vo.BaseVo;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户角色
 * @author haigle
 * @date 2019-06-09 14:11
 */
@Getter
@Setter
public class RoleVo extends BaseVo {

    /** 角色名称 */
    private String name;

    /** 角色说明 */
    private String description;

}
