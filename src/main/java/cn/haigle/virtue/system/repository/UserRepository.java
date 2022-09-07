package cn.haigle.virtue.system.repository;

import cn.haigle.virtue.common.base.repository.BaseRepository;
import cn.haigle.virtue.system.entity.bo.SysUserBo;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * 用户信息JPA
 * @author haigle
 * @date 2021/12/3 21:54
 */
@Service("userRepository")
public interface UserRepository extends BaseRepository<SysUserBo> {

    /**
     * 获取用户信息
     * @param account 账号
     * @return Optional<SysUserBo>
     * @author haigle
     * @date 2021/12/4 23:36
     */
    Optional<SysUserBo> findByUsernameOrPhoneOrEmail(String account);

}
