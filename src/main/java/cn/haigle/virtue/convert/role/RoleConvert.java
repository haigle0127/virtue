package cn.haigle.virtue.convert.role;

import cn.haigle.virtue.common.pojo.PageResult;
import cn.haigle.virtue.controller.admin.role.vo.RolePageRespVO;
import cn.haigle.virtue.mapper.dataobject.role.SysRoleDO;
import cn.haigle.virtue.mapper.dataobject.role.SysRoleDeptDO;
import cn.haigle.virtue.mapper.dataobject.role.SysRoleMenuDO;
import cn.hutool.core.bean.BeanUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色 Convert
 *
 * @author haigle
 */
public class RoleConvert {

    public static PageResult<RolePageRespVO> convertPage(PageResult<SysRoleDO> page) {
        PageResult<RolePageRespVO> result = new PageResult<>(page.getTotal(), page.getPageNo());
        List<RolePageRespVO> list = new ArrayList<>();
        page.getList().forEach(item -> list.add(BeanUtil.toBean(item, RolePageRespVO.class)));
        result.setList(list);
        return result;
    }

    public static List<SysRoleMenuDO> convertRoleMenuList(Long[] menuIds, Long roleId) {
        List<SysRoleMenuDO> list = new ArrayList<>();
        for (Long menuId : menuIds) {
            SysRoleMenuDO roleMenu = new SysRoleMenuDO();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            list.add(roleMenu);
        }
        return list;
    }

    public static List<SysRoleDeptDO> convertRoleDeptList(Long[] deptIds, Long roleId) {
        List<SysRoleDeptDO> list = new ArrayList<>();
        for (Long deptId : deptIds) {
            SysRoleDeptDO roleDept = new SysRoleDeptDO();
            roleDept.setRoleId(roleId);
            roleDept.setDeptId(deptId);
            list.add(roleDept);
        }
        return list;
    }

}
