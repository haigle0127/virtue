package cn.haigle.virtue.system.entity.vo;

import cn.haigle.virtue.common.util.tree.TreeNode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 菜单
 * @author haigle
 * @date 2021/11/14 21:18
 */
@Getter
@Setter
public class Menu implements TreeNode<Long, Menu> {

    /** 菜单ID */
    private Long id;

    /** 父级ID */
    private Long parentId;

    /** 路由地址 */
    private String path;

    /** 重定向 */
    private String redirect;

    /** 组件路径 */
    private String component;

    /** 菜单类型（MENU目录 BUTTON按钮） */
    private String menuType;

    /** 权限标识 */
    private String power;

    /** 路由破配置 */
    private Meta meta;

    private List<Menu> children;

    /**
     * 是否拥有子集
     *
     * @return boolean
     */
    public boolean hasChildren() {
        return children != null && !children.isEmpty();
    }

}
