package com.haigle.around.admin.sys.service;

import com.haigle.around.admin.sys.entity.query.AdminSearchNameQuery;
import com.haigle.around.common.base.page.Page;
import com.haigle.around.admin.sys.entity.ao.AdminRoleAo;
import com.haigle.around.admin.sys.entity.vo.AdminRoleVo;

/**
 * 角色接口
 * @author haigle
 * @date 2019/7/25 14:43
 */
public interface AdminRoleService {

    /**
     * 角色查询
     * @param adminSearchNameQuery 分页搜索
     * @return Page<AdminRoleVo> 分页数据
     * @author haigle
     * @date 2019/7/25 14:46
     */
    Page<AdminRoleVo> list(AdminSearchNameQuery adminSearchNameQuery);

    /**
     * 角色保存
     * @param adminRoleAo AdminRoleAo
     * @param uid 操作人ID
     * @author haigle
     * @date 2019-08-04 21:16
     */
    void save(AdminRoleAo adminRoleAo, Long uid);

    /**
     * 角色更新
     * @param adminRoleAo AdminRoleAo
     * @param uid 操作人ID
     * @author haigle
     * @date 2019-08-04 22:10
     */
    void update(AdminRoleAo adminRoleAo, Long uid);

    /**
     * 角色删除
     * @param id 角色ID
     * @author haigle
     * @date 2019-08-04 22:23
     */
    void delete(Long id);

}
