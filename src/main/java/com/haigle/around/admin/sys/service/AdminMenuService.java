package com.haigle.around.admin.sys.service;

import com.haigle.around.admin.sys.entity.ao.AdminMenuAo;
import com.haigle.around.admin.sys.entity.vo.AdminTreeVo;
import com.haigle.around.admin.sys.entity.vo.AdminMenuVo;
import com.haigle.around.common.interceptor.model.ServiceResult;

import java.util.List;

/**
 * 菜单、权限接口
 * @author haigle
 * @date 2019/7/25 14:43
 */
public interface AdminMenuService {

    /**
     * 菜单、权限查询
     * @param id 菜单ID
     * @return List<AdminMenuVo> 菜单、权限列表
     * @author haigle
     * @date 2019/8/23 9:19
     */
    List<AdminMenuVo> list(Long id);

    /**
     * 菜单、权限总查询
     * @return List<AdminTreeVo> 菜单、权限列表
     * @author haigle
     * @date 2019/8/23 9:19
     */
    List<AdminTreeVo> menuAllTree();

    /**
     * 保存菜单
     * @param adminMenuAo AdminMenuDto
     * @param uid 操作人ID
     * @return ServiceResult
     * @author haigle
     * @date 2019/8/23 13:17
     */
    ServiceResult save(AdminMenuAo adminMenuAo, Long uid);

    /**
     *更新菜单
     * @param adminMenuAo AdminMenuDto
     * @param uid 操作人ID
     * @return ServiceResult
     * @author haigle
     * @date 2019/8/23 13:17
     */
    ServiceResult update(AdminMenuAo adminMenuAo, Long uid);

    /**
     * 删除菜单
     * @param id 菜单ID
     * @author haigle
     * @date 2019/8/23 13:18
     */
    void delete(Long id);

    /**
     * 判断菜单是否存在子集
     * @param parentId 菜单原有ID
     * @return boolean
     * @author haigle
     * @date 2019/8/29 13:25
     */
    boolean getIsChildren(Long parentId);

    /**
     * 获取菜单ID列表
     * @param id 角色ID
     * @return List<Long> 菜单ID列表
     * @author haigle
     * @date 2019/9/3 16:00
     */
    List<Long> getRoleMenuList(Long id);


}
