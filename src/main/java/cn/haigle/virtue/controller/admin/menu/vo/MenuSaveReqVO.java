package cn.haigle.virtue.controller.admin.menu.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author haigle
 * @date 2024/11/13 下午1:34
 */
@Schema(description = "管理后台 - 菜单保存 Request VO")
@Data
public class MenuSaveReqVO {

    @Schema(description = "编号ID")
    private Long id;

    @Schema(description = "父ID")
    private Long parentId;

    @Schema(description = "路由地址")
    private String path;

    @Schema(description = "路由名称(路由地址一样的驼峰结构)")
    private String routeName;

    @Schema(description = "权限名")
    private String name;

    @Schema(description = "组件路径")
    private String component;

    @Schema(description = "路由参数")
    private String param;

    @Schema(description = "是否为外链 0否 1是")
    private String iframe;

    @Schema(description = "是否缓存 0不缓存 1缓存")
    private String cache;

    @Schema(description = "菜单状态 0正常 1停用")
    private String state;

    @Schema(description = "显示状态 0显示 1隐藏")
    private String visible;

    @Schema(description = "重定向")
    private String redirect;

    @Schema(description = "图标")
    private String icon;

    @Schema(description = "菜单类型 MENU目录 VIEW按钮")
    private String menuType;

    @Schema(description = "权限标识")
    private String power;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "权限说明")
    private String description;

}
