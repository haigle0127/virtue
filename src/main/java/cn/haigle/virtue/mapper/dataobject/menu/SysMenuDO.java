package cn.haigle.virtue.mapper.dataobject.menu;

import cn.haigle.virtue.common.base.BaseDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜单
 * @author haigle
 * @date 2021/11/14 21:18
 */
@TableName("sys_menu")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysMenuDO extends BaseDO {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 父ID
     */
    private Long parentId;
    /**
     * 路由地址
     */
    private String path;
    /**
     * 路由名称(路由地址一样的驼峰结构)
     */
    private String routeName;
    /**
     * 权限名
     */
    private String name;
    /**
     * 组件路径
     */
    private String component;
    /**
     * 路由参数
     */
    private String param;
    /**
     * 是否为外链 0否 1是
     * {@link cn.haigle.virtue.common.constants.StateEnum}
     */
    private String iframe;
    /**
     * 是否缓存 0不缓存 1缓存
     * {@link cn.haigle.virtue.common.constants.StateEnum}
     */
    private String cache;
    /**
     * 菜单状态 0正常 1停用
     * {@link cn.haigle.virtue.common.constants.StateEnum}
     */
    private String state;
    /**
     * 显示状态 0显示 1隐藏
     * {@link cn.haigle.virtue.common.constants.StateEnum}
     */
    private String visible;
    /**
     * 重定向
     */
    private String redirect;
    /**
     * 图标
     */
    private String icon;
    /**
     * 菜单类型 MENU目录 VIEW按钮
     */
    private String menuType;
    /**
     * 权限标识
     */
    private String power;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 权限说明
     */
    private String description;

}
