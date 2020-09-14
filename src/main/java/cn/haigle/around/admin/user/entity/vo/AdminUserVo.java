package cn.haigle.around.admin.user.entity.vo;

import cn.haigle.around.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 用户视图表
 * @author haigle
 * @date 2019/3/5 17:08
 */
@Getter
@Setter
public class AdminUserVo extends BaseEntity {

    private String username;
    private String email;
    private String phone;
    private String avatar;
    private String introduction;
    private String birth;
    private String school;
    private String education;

    public AdminUserVo() {
    }

    public AdminUserVo(Long id) {
        super.setId(id);
    }

    /**
     * 权限数组
     */
    public List<String> roles;

}
