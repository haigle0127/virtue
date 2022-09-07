package cn.haigle.virtue.system.entity.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author haigle
 * @date 2021/12/3 22:17
 */
@Setter
@Getter
@Accessors(chain = true)
public class Meta {

    /** 菜单名称 */
    private String title;

    /** 菜单图标 */
    private String icon;
}
