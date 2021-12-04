package cn.haigle.virtue.system.entity.po;

import cn.haigle.virtue.system.entity.vo.Menu;
import lombok.*;
import lombok.experimental.Accessors;
import java.util.List;

/**
 * 权限整活.
 * @author haigle
 * @date 2020/11/29 19:17
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Accessors(chain = true)
public class UserPowerDo {
    private Long id;
    private List<Menu> powerList;
    private List<String> powerKeyList;
}
