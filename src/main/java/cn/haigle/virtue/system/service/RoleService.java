package cn.haigle.virtue.system.service;

import cn.haigle.virtue.system.entity.ao.RoleAo;
import cn.haigle.virtue.system.entity.vo.RoleVo;
import cn.haigle.virtue.common.base.page.Page;
import cn.haigle.virtue.common.entity.query.NameQuery;

/**
 * 角色接口
 * @author haigle
 * @date 2019/7/25 14:43
 */
public interface RoleService {

    /**
     * 角色查询
     * @param adminSearchNameQuery 分页搜索
     * @return Page<AdminRoleVo> 分页数据
     * @author haigle
     * @date 2019/7/25 14:46
     */
    Page<RoleVo> list(NameQuery adminSearchNameQuery);

    /**
     * 角色保存
     * @param roleAO AdminRoleAO
     * @param uid 操作人ID
     * @author haigle
     * @date 2019-08-04 21:16
     */
    void save(RoleAo roleAO, Long uid);

    /**
     * 角色更新
     * @param roleAO AdminRoleAO
     * @param uid 操作人ID
     * @author haigle
     * @date 2019-08-04 22:10
     */
    void update(RoleAo roleAO, Long uid);

    /**
     * 角色删除
     * @param id 角色ID
     * @author haigle
     * @date 2019-08-04 22:23
     */
    void delete(Long id);

}
