package cn.haigle.virtue.service.role;

import cn.haigle.virtue.common.pojo.PageResult;
import cn.haigle.virtue.controller.admin.role.vo.*;
import cn.haigle.virtue.convert.role.RoleConvert;
import cn.haigle.virtue.mapper.dataobject.role.SysRoleDO;
import cn.haigle.virtue.mapper.dataobject.role.SysRoleDeptDO;
import cn.haigle.virtue.mapper.dataobject.role.SysRoleMenuDO;
import cn.haigle.virtue.mapper.mysql.role.RoleDeptMapper;
import cn.haigle.virtue.mapper.mysql.role.RoleMapper;
import cn.haigle.virtue.mapper.mysql.role.RoleMenuMapper;
import cn.hutool.core.bean.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 角色接口实现
 * @author haigle
 * @date 2026/4/29 15:10
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private RoleDeptMapper roleDeptMapper;

    @Override
    public PageResult<RolePageRespVO> page(RolePageReqVO reqVO) {
        PageResult<SysRoleDO> pageResult = roleMapper.selectPage(reqVO);
        return RoleConvert.convertPage(pageResult);
    }

    @Override
    public RoleRespVO detail(Long roleId) {
        SysRoleDO sysRoleDO = roleMapper.selectById(roleId);
        return BeanUtil.toBean(sysRoleDO, RoleRespVO.class);
    }

    @Override
    public void add(RoleSaveReqVO reqVO) {
        SysRoleDO sysRoleDO = BeanUtil.toBean(reqVO, SysRoleDO.class);
        sysRoleDO.setCreateTime(LocalDateTime.now());
        sysRoleDO.setUpdateTime(LocalDateTime.now());
        roleMapper.insert(sysRoleDO);
    }

    @Override
    public void update(RoleSaveReqVO reqVO) {
        SysRoleDO sysRoleDO = BeanUtil.toBean(reqVO, SysRoleDO.class);
        sysRoleDO.setUpdateTime(LocalDateTime.now());
        roleMapper.updateById(sysRoleDO);
        roleMenuMapper.deleteByRoleId(reqVO.getId());
        List<SysRoleMenuDO> sysRoleMenuDOList = RoleConvert.convertRoleMenuList(reqVO.getMenuIds(), reqVO.getId());
        roleMenuMapper.insertBatch(sysRoleMenuDOList);
    }

    @Override
    public void updateDataScope(RoleSaveReqVO reqVO) {
        SysRoleDO sysRoleDO = new SysRoleDO();
        sysRoleDO.setId(reqVO.getId());
        sysRoleDO.setDataScope(reqVO.getDataScope());
        sysRoleDO.setDeptCheckStrictly(reqVO.getDeptCheckStrictly());
        sysRoleDO.setUpdateTime(LocalDateTime.now());
        roleMapper.updateById(sysRoleDO);
        roleDeptMapper.deleteByRoleId(reqVO.getId());
        List<SysRoleDeptDO> sysRoleDeptDOList = RoleConvert.convertRoleDeptList(reqVO.getDeptIds(), reqVO.getId());
        roleDeptMapper.insertBatch(sysRoleDeptDOList);
    }

    @Override
    public void updateState(RoleUpdateStateReqVO reqVO) {
        SysRoleDO sysRoleDO = BeanUtil.toBean(reqVO, SysRoleDO.class);
        sysRoleDO.setUpdateTime(LocalDateTime.now());
        roleMapper.updateById(sysRoleDO);
    }

    @Override
    public void delete(Long roleId) {
        roleMapper.deleteById(roleId);
    }
}
