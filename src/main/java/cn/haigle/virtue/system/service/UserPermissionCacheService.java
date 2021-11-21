package cn.haigle.virtue.system.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * 用户权限缓存
 * @author haigle
 * @date 2020/11/29 19:01
 */
public interface UserPermissionCacheService {

    /**
     * 用户是否有访问权限
     * @param uid 登录用户id
     * @param permission 待验证的权限
     * @author haigle
     * @date 2020/11/29 19:02
     */
    default boolean hasPermission(Long uid, String permission){

        /*
         * 判断用户是否有权限
         */
        return Optional.ofNullable(get(uid))
                .filter(permissions -> permissions.contains(permission))
                .isPresent();
    }

    /**
     * 将用户权限放入缓存中
     * @param uid 用户主键
     * @param permissions 用户权限
     * @author haigle
     * @date 2020/11/29 19:02
     */
    void set(Long uid, List<String> permissions);

    /**
     * 在缓存中获取用户权限
     * @param uid 用户主键
     * @return List<String>
     * @author haigle
     * @date 2020/11/29 19:03
     */
    List<String> get(Long uid);

    /**
     * 删除缓存
     * @param uid 用户ID
     * @author haigle
     * @date 2020/11/29 19:03
     */
    void remove(Long uid);

    /**
     * 清空权限缓存
     * @author haigle
     * @date 2020/11/29 19:03
     */
    void removeAll();

    /**
     * 清除用户权限缓存
     * @param ids 需要清除用户的主键
     * @author haigle
     * @date 2020/11/29 19:04
     */
    void removeAll(Long ...ids);


}
