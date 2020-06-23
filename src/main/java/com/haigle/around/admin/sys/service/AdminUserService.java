package com.haigle.around.admin.sys.service;

import com.haigle.around.admin.sys.entity.query.AdminSearchNameQuery;
import com.haigle.around.admin.sys.entity.ao.AdminUserAo;
import com.haigle.around.admin.sys.entity.vo.AdminTreeVo;
import com.haigle.around.admin.sys.entity.vo.AdminUserVo;
import com.haigle.around.common.base.page.Page;
import com.haigle.around.common.interceptor.model.service.ServiceResult;

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
     * @param adminSearchNameQuery 分页搜索
     * @return Page<AdminUserVo> 分页数据
     * @author haigle
     * @date 2019-08-14 20:20
     */
    Page<AdminUserVo> list(AdminSearchNameQuery adminSearchNameQuery);

    /**
     * 添加用户
     * @param adminUserAo 用户信息
     * @param uid 操作用户ID
     * @return ServiceResult
     * @author haigle
     * @date 2019/9/5 13:56
     */
    ServiceResult save(AdminUserAo adminUserAo, Long uid);

    /**
     * 更新用户
     * @param adminUserAo 用户信息
     * @param uid 操作用户ID
     * @return ServiceResult
     * @author haigle
     * @date 2019/9/6 8:45
     */
    ServiceResult update(AdminUserAo adminUserAo, Long uid);

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
