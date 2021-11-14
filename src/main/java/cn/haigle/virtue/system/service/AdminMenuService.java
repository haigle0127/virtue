package cn.haigle.virtue.system.service;

import cn.haigle.virtue.system.entity.ao.AdminMenuAo;
import cn.haigle.virtue.system.entity.vo.AdminMenuVo;
import cn.haigle.virtue.system.entity.vo.AdminTreeVo;
import cn.haigle.virtue.system.entity.vo.Menu;

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
    List<AdminMenuVo> list(Long id);

    List<Menu> menuTree();

    /**
     * 菜单、权限总查询
     * @return List<AdminTreeVO> 菜单、权限列表
     * @author haigle
     * @date 2019/8/23 9:19
     */
    List<AdminTreeVo> menuAllTree();

    /**
     * 保存菜单
     * @param adminMenuAO AdminMenuAO
     * @param uid 操作人ID
     * @author haigle
     * @date 2019/8/23 13:17
     */
    void save(AdminMenuAo adminMenuAO, Long uid);

    /**
     *更新菜单
     * @param adminMenuAO AdminMenuAO
     * @param uid 操作人ID
     * @author haigle
     * @date 2019/8/23 13:17
     */
    void update(AdminMenuAo adminMenuAO, Long uid);

    /**
     * 删除菜单
     * @param id 菜单ID
     * @author haigle
     * @date 2019/8/23 13:18
     */
    void delete(Long id);

    /**
     * 获取菜单ID列表
     * @param id 角色ID
     * @return List<Long> 菜单ID列表
     * @author haigle
     * @date 2019/9/3 16:00
     */
    List<Long> getRoleMenuList(Long id);


}
