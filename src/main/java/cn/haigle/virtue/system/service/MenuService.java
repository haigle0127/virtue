package cn.haigle.virtue.system.service;

import cn.haigle.virtue.system.entity.ao.MenuAo;
import cn.haigle.virtue.system.entity.vo.MenuVo;
import cn.haigle.virtue.system.entity.vo.TreeVo;
import cn.haigle.virtue.system.entity.vo.Menu;

import java.util.List;

/**
 * 菜单、权限接口
 * @author haigle
 * @date 2019/7/25 14:43
 */
public interface MenuService {

    /**
     * 菜单、权限查询
     * @param id 菜单ID
     * @return List<MenuVo> 菜单、权限列表
     * @author haigle
     * @date 2019/8/23 9:19
     */
    List<MenuVo> list(Long id);

    /**
     * 获取菜单树结构
     * @param userId 用户ID
     * @return List<Menu> 菜单
     * @author haigle
     * @date 2021/11/19 22:51
     */
    List<Menu> menuTree(Long userId);

    /**
     * 菜单、权限总查询
     * @return List<TreeVo> 菜单、权限列表
     * @author haigle
     * @date 2019/8/23 9:19
     */
    List<TreeVo> menuAllTree();

    /**
     * 保存菜单
     * @param menuAo MenuAo
     * @param uid 操作人ID
     * @author haigle
     * @date 2019/8/23 13:17
     */
    void save(MenuAo menuAo, Long uid);

    /**
     *更新菜单
     * @param menuAo MenuAo
     * @param uid 操作人ID
     * @author haigle
     * @date 2019/8/23 13:17
     */
    void update(MenuAo menuAo, Long uid);

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
