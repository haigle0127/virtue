package cn.haigle.virtue.system.entity.po;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Set;

/**
 * @author haigle
 * @date 2020/11/29 19:17
 */
@Data
@Accessors(chain = true)
public class UserPermissionDo {

    private Long id;
    private List<String> permissions;

}
