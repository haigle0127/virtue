package com.haigle.around.admin.rbac.entity.po;

import lombok.Data;

/**
 * 权限菜单总表
 * @author haigle
 * @date 2019-06-09 14:15
 */
@Data
public class AdminTreePo {

    private Long id;
    private Long parentId;
    private String name;
    private boolean hasChildren;

}
