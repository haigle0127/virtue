package cn.haigle.virtue.system.dao.cache;

import cn.haigle.virtue.system.entity.po.UserPermissionDo;

import java.util.List;

/**
 * @author haigle
 * @date 2020/11/29 19:08
 */
public interface UserPermissionCacheDao {

    /**
     * 查询用户权限缓存
     * @author haigle
     * @date 2020/11/29 19:18
     */
    UserPermissionDo findById(Long uid);

    /**
     * 保存用户权限到缓存中
     * @param uid 用户主键
     * @param permissions 权限集合
     * @author haigle
     * @date 2020/11/29 19:18
     */
    void save(Long uid, List<String> permissions);

    /**
     * 删除用户权限缓存
     * @param uid 用户主键
     * @author haigle
     * @date 2020/11/29 19:18
     */
    void remove(Long uid);

    /**
     * 清空所有用户权限缓存
     * @author haigle
     * @date 2020/11/29 19:18
     */
    void removeAll();

    /**
     * 清空所有用户权限缓存
     * @param ids 清除用户权限缓存
     * @author haigle
     * @date 2020/11/29 19:19
     */
    void removeAll(Long... ids);
}
