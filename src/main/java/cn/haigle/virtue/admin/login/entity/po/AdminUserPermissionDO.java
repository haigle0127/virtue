package cn.haigle.virtue.admin.login.entity.po;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

/**
 * @author haigle
 * @date 2020/11/29 19:17
 */
@Data
@Accessors(chain = true)
public class AdminUserPermissionDO {

    private Long id;
    private Set<String> permissions;

}
