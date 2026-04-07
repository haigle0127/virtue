package cn.haigle.virtue.controller.admin.menu.vo;

import lombok.Data;

/**
 * 管理后台 - 路由显示信息 Response VO
 */
@Data
public class MetaVO {

    public MetaVO() {}

    public MetaVO(String name, String icon) {
        this.title = name;
        this.icon = icon;
    }

    public MetaVO(String name, String icon, String path) {
        this.title = name;
        this.icon = icon;
        this.link = path;
    }

    public MetaVO(String name, String icon, boolean noCache, String path) {
        this.title = name;
        this.icon = icon;
        this.noCache = noCache;
        if(path.startsWith("http")) {
            this.link = path;
        }
    }

    /** 设置该路由在侧边栏和面包屑中展示的名字 */
    private String title;

    /** 置该路由的图标，对应路径src/assets/icons/svg */
    private String icon;

    /** 设置为true，则不会被 <keep-alive>缓存 */
    private boolean noCache;

    /** 内链地址（http(s)://开头） */
    private String link;

}
