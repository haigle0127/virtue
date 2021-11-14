package cn.haigle.virtue.system.entity.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author 阮雪峰
 */
@Accessors(chain = true)
@Setter
@Getter
public class Meta {

    /** 菜单名称 */
    private String title;

    /** 菜单图标 */
    private String icon;
}
