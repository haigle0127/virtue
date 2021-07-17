package cn.haigle.virtue.common.interceptor.permission.authentication;

import java.util.List;
import java.util.Optional;

/**
 * 权限接口
 * @author haigle
 * @date 2019/9/11 10:48
 */
public interface PowerPermission {

    /**
     * 判断用户是否有访问权限
     * @param uid 用户ID
     * @param permission 待验证权限
     * @return boolean true 有， false 没有
     * @author haigle
     * @date 2019/9/11 15:03
     */
    default boolean hasPermission(Long uid, String permission){
        return Optional.ofNullable(getPermission(uid))
                .filter(permissions -> permissions.contains(permission))
                .isPresent();
    }

    /**
     * 将权限列表放入缓存
     * @param uid 用户ID
     * @author haigle
     * @date 2019/9/11 15:15
     */
    void setPermission(Long uid, List<String> permissions);

    /**
     * 获取权限列表
     * @param uid 用户ID
     * @return List<String> 权限列表
     * @author haigle
     * @date 2019/9/11 14:24
     */
    List<String> getPermission(Long uid);

    /**
     * 移除当前用户缓存
     * @param uid 用户ID
     * @author haigle
     * @date 2019/9/11 14:25
     */
    void removePermission(Long uid);

    /**
     * 清空缓存（慎重操作！！！）
     * @author haigle
     * @date 2019/9/11 15:17
     */
    void removeAll();

}
