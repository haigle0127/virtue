package cn.haigle.virtue.controller.admin.menu.vo;

import lombok.Data;

/**
 * 管理后台 - 菜单保存 Request VO
 * @author haigle
 * @date 2024/11/13 下午1:34
 */
@Data
public class MenuSaveReqVO {

    /** 编号ID */
    private Long id;

    /** 父ID */
    private Long parentId;

    /** 路由地址 */
    private String path;

    /** 路由名称(路由地址一样的驼峰结构) */
    private String routeName;

    /** 权限名 */
    private String name;

    /** 组件路径 */
    private String component;

    /** 路由参数 */
    private String param;

    /** 是否为外链 0否 1是 */
    private String iframe;

    /** 是否缓存 0不缓存 1缓存 */
    private String cache;

    /** 菜单状态 0正常 1停用 */
    private String state;

    /** 显示状态 0显示 1隐藏 */
    private String visible;

    /** 重定向 */
    private String redirect;

    /** 图标 */
    private String icon;

    /** 菜单类型 MENU目录 VIEW按钮 */
    private String menuType;

    /** 权限标识 */
    private String power;

    /** 排序 */
    private Integer sort;

    /** 权限说明 */
    private String description;

}
