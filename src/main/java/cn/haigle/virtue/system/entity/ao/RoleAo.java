package cn.haigle.virtue.system.entity.ao;

import cn.haigle.virtue.common.base.validator.Save;
import cn.haigle.virtue.common.base.validator.Update;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 角色数据
 * @author haigle
 * @date 2019-08-04 10:40
 */
@Data
public class RoleAo {

    /** ID */
    @NotNull(message = "ID不能为空", groups = {Update.class})
    private Long id;

    /** 角色名称 */
    @NotBlank(message = "角色名称不能为空", groups = {Save.class, Update.class})
    @Size(max = 20,min = 1,message = "角色名称长度在1-20个字符", groups = {Save.class, Update.class})
    private String name;

    /** 角色别名 */
    @NotBlank(message = "角色别名不能为空", groups = {Save.class, Update.class})
    @Size(max = 20,min = 1,message = "角色别名长度在1-20个字符", groups = {Save.class, Update.class})
    private String ename;

    /** 简介 */
    @Size(max = 150, message = "角色描述过长，最长为150个字符", groups = {Save.class, Update.class})
    private String description;

    /** 权限ID列表 */
    private List<Long> menuList;

}