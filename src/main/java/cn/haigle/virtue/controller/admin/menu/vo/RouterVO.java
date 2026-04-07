package cn.haigle.virtue.controller.admin.menu.vo;

import lombok.Data;

import java.util.List;

/**
 * 管理后台 - 路由配置信息 Response VO
 */
@Data
public class RouterVO {

    /** 路由名字 */
    private String name;

    /** 路由地址 */
    private String path;

    /** 是否隐藏路由，当设置 true 的时候该路由不会再侧边栏出现 */
    private boolean hidden;

    /** 重定向地址，当设置 noRedirect 的时候该路由在面包屑导航中不可被点击 */
    private String redirect;

    /** 组件地址 */
    private String component;

    /** 路由参数：如 {"id": 1, "name": "小明"} */
    private String param;

    /** 当你一个路由下面的 children 声明的路由大于1个时，自动会变成嵌套的模式--如组件页面 */
    private Boolean alwaysShow;

    /** 其他元素 */
    private MetaVO meta;

    /** 子路由 */
    private List<RouterVO> children;

}
