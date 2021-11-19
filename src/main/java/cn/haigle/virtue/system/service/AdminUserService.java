package cn.haigle.virtue.system.service;

import cn.haigle.virtue.system.entity.vo.AdminTreeVo;
import cn.haigle.virtue.system.entity.ao.AdminUserAo;
import cn.haigle.virtue.system.entity.vo.AdminUserVo;
import cn.haigle.virtue.common.base.page.Page;
import cn.haigle.virtue.common.entity.query.NameQuery;

import java.util.List;

/**
 * 用户接口
 * @author haigle
 * @date 2019-06-06 21:12
 */
public interface AdminUserService {

    /**
     * 用户信息
     * @param uid 用户ID
     * @return UserVoEntity 用户信息
     * @author haigle
     * @date 2019-06-09 14:06
     */
    AdminUserVo info(Long uid);

    /**
     * 用户列表
     * @param adminNameQuery 分页搜索
     * @return Page<AdminUserVo> 分页数据
     * @author haigle
     * @date 2019-08-14 20:20
     */
    Page<AdminUserVo> list(NameQuery adminNameQuery);

    /**
     * 添加用户
     * @param adminUserAo 用户信息
     * @param uid 操作用户ID
     * @author haigle
     * @date 2019/9/5 13:56
     */
    void save(AdminUserAo adminUserAo, Long uid);

    /**
     * 更新用户
     * @param adminUserAo 用户信息
     * @param uid 操作用户ID
     * @author haigle
     * @date 2019/9/6 8:45
     */
    void update(AdminUserAo adminUserAo, Long uid);

    /**
     * 删除用户
     * @param id 说删除用户的ID
     * @author haigle
     * @date 2019/9/6 9:03
     */
    void delete(Long id);

    /**
     * 返回所有角色
     * @return List<AdminTreeVo> 树结构
     * @author haigle
     * @date 2019/9/9 9:53
     */
    List<AdminTreeVo> roleAllList();

    /**
     * 用户下角色ID
     * @param uid 用户ID
     * @return List<Long> 角色ID列表
     * @author haigle
     * @date 2019/9/9 10:30
     */
    List<Long> getUserRoleList(Long uid);
}
