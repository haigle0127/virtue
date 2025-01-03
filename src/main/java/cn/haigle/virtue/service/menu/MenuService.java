package cn.haigle.virtue.service.menu;

import cn.haigle.virtue.controller.admin.menu.vo.*;

import java.util.List;

/**
 * 菜单、权限接口
 * @author haigle
 * @date 2019/7/25 14:43
 */
public interface MenuService {

    List<MenuRespVO> list(MenuReqVO reqVO);

    MenuRespVO detail(Long menuId);

    void add(MenuSaveReqVO reqVO);

    void update(MenuSaveReqVO reqVO);

    void delete(Long menuId);

    List<MenuRespVO> selectTreeByUserId(Long userId);

    List<RouterVO> buildMenus(List<MenuRespVO> menus);

    List<MenuTreeDTO> userMenuTree(Long userMenuTree);

    RoleMenuTreeRespVO roleMenuTree(Long roleId);


}
