package cn.haigle.virtue.system.entity.bo;

import lombok.Data;

/**
 * 权限菜单总表
 * @author haigle
 * @date 2019-06-09 14:15
 */
@Data
public class AdminTreeBo {

    private Long id;
    private Long parentId;
    private String name;
    private boolean hasChildren;

}
