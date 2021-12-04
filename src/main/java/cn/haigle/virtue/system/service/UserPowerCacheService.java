package cn.haigle.virtue.system.service;

import cn.haigle.virtue.system.entity.po.UserPowerDo;
import cn.haigle.virtue.system.entity.vo.Menu;

import java.util.List;
import java.util.Optional;

/**
 * 用户权限缓存
 * https://gitee.com/ruanxuefeng/am-server/blob/master/src/main/java/com/am/server/api/system/permission/service/UserPermissionCacheService.java
 * @author haigle
 * @date 2020/11/29 19:01
 */
public interface UserPowerCacheService {

    /**
     * 将用户权限放入缓存中
     * @param uid          用户主键
     * @param powers       用户权限
     * @param powerKeyList 权限唯一标识
     * @author 阮雪峰
     * @date 2018/8/24 14:50
     */
    void set(Long uid, List<Menu> powers, List<String> powerKeyList);

    /**
     * 在缓存中获取用户权限，如果缓存中没有则在数据库中查询，并保存到缓存
     * @param uid 用户主键
     * @return void
     * @author 阮雪峰
     * @date 2018/8/24 14:44
     */
    List<Menu> saveOrGet(Long uid);

    /**
     * 只在缓存中查询
     * @param uid 用户ID
     * @return Optional<UserPermissionDo>
     */
    Optional<UserPowerDo> get(Long uid);

    /**
     * 删除缓存
     * @param uid 用户ID
     * @date 2019/4/9 15:08
     * @author 阮雪峰
     */
    void remove(Long uid);

    /**
     * 清空权限缓存
     * @author 阮雪峰
     * @date 2019年6月26日14:32:58
     */
    void removeAll();

    /**
     * 清除用户权限缓存
     * @param ids 需要清除用户的主键
     */
    void removeAll(Long... ids);

    /**
     * 登录用户的权限key
     * @param loginId  loginId
     * @param loginKey loginKey
     * @return List<String>
     */
    List<String> getPowerList(Object loginId, String loginKey);

    /**
     * 刷新用户权限缓存
     * @param uid 用户ID
     */
    void refresh(Long uid);

}
