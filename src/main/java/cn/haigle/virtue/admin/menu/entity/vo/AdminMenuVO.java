package cn.haigle.virtue.admin.menu.entity.vo;

import cn.haigle.virtue.common.entity.BaseVO;
import lombok.Getter;
import lombok.Setter;

/**
 * 权限菜单实体
 * @author haigle
 * @date 2019-06-09 14:15
 */
@Getter
@Setter
public class AdminMenuVO extends BaseVO {

    private Long parentId;
    private String name;
    private String ename;
    private String power;
    private String description;
    private boolean hasChildren;

}
