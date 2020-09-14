package cn.haigle.around.admin.menu.service;

import cn.haigle.around.admin.menu.entity.ao.AdminMenuAO;
import cn.haigle.around.admin.menu.entity.vo.AdminTreeVO;
import cn.haigle.around.admin.menu.entity.vo.AdminMenuVO;
import cn.haigle.around.common.interceptor.model.service.ServiceResult;

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
     * @return List<AdminMenuVO> 菜单、权限列表
     * @author haigle
     * @date 2019/8/23 9:19
     */
    List<AdminMenuVO> list(Long id);

    /**
     * 菜单、权限总查询
     * @return List<AdminTreeVO> 菜单、权限列表
     * @author haigle
     * @date 2019/8/23 9:19
     */
    List<AdminTreeVO> menuAllTree();

    /**
     * 保存菜单
     * @param adminMenuAO AdminMenuAO
     * @param uid 操作人ID
     * @return ServiceResult
     * @author haigle
     * @date 2019/8/23 13:17
     */
    ServiceResult save(AdminMenuAO adminMenuAO, Long uid);

    /**
     *更新菜单
     * @param adminMenuAO AdminMenuAO
     * @param uid 操作人ID
     * @return ServiceResult
     * @author haigle
     * @date 2019/8/23 13:17
     */
    ServiceResult update(AdminMenuAO adminMenuAO, Long uid);

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
