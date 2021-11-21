package cn.haigle.virtue.system.entity.ao;

import cn.haigle.virtue.common.base.validator.Save;
import cn.haigle.virtue.common.base.validator.Update;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 权限菜单接口实体
 * @author haigle
 * @date 2019-06-09 14:15
 */
@Getter
@Setter
public class MenuAo {

    /** ID */
    @NotNull(message = "ID不能为空", groups = {Update.class})
    private Long id;

    /** 父级ID */
    @Min(value = 0, message = "必须包含上级ID", groups = {Save.class, Update.class})
    private Long parentId;

    /** 菜单名称 */
    @NotBlank(message = "菜单名称不能为空", groups = {Save.class, Update.class})
    @Size(max = 20, min = 1,message = "菜单名称长度在1-20个字符", groups = {Save.class, Update.class})
    private String name;

    /** 菜单别名 */
    @NotBlank(message = "菜单别名不能为空", groups = {Save.class, Update.class})
    @Size(max = 20, min = 1, message = "菜单别名长度在1-20个字符", groups = {Save.class, Update.class})
    private String ename;

    /** 权限标识 */
    @NotBlank(message = "权限标识不能为空", groups = {Save.class, Update.class})
    private String power;

    /** 简介 */
    @Size(max = 150, message = "菜单描述过长，最长为150个字符", groups = {Save.class, Update.class})
    private String description;

}
