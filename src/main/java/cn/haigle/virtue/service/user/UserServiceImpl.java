package cn.haigle.virtue.service.user;

import cn.haigle.virtue.common.constants.StateEnum;
import cn.haigle.virtue.common.pojo.PageResult;
import cn.haigle.virtue.controller.admin.role.vo.RoleSaveReqVO;
import cn.haigle.virtue.controller.admin.user.vo.*;
import cn.haigle.virtue.convert.user.UserConvert;
import cn.haigle.virtue.mapper.dataobject.role.SysRoleDO;
import cn.haigle.virtue.mapper.dataobject.user.SysUserDO;
import cn.haigle.virtue.mapper.dataobject.user.SysUserRoleDO;
import cn.haigle.virtue.mapper.mysql.role.RoleMapper;
import cn.haigle.virtue.mapper.mysql.user.UserMapper;
import cn.haigle.virtue.mapper.mysql.user.UserRoleMapper;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.crypto.symmetric.AES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static cn.haigle.virtue.common.web.Constant.PASSWORD_KEY;

/**
 * 用户接口实现
 * @author haigle
 * @date 2019-06-09 13:56
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public UserInfoVO getInfo(Long userId) {
        SysUserDO sysUserDO = userMapper.selectById(userId);
        UserInfoVO userInfoVO = BeanUtil.toBean(sysUserDO, UserInfoVO.class);
        List<String> permissions = new ArrayList<>();
        permissions.add("*:*:*");
        userInfoVO.setPermissions(permissions);
        List<String> roles = new ArrayList<>();
        roles.add("admin");
        userInfoVO.setRoles(roles);
        return userInfoVO;
    }

    @Override
    public PageResult<UserPageRespVO> page(UserPageReqVO reqVO) {
        PageResult<SysUserDO> pageResult = userMapper.selectPage(reqVO);
        return UserConvert.convertPage(pageResult);
    }

    @Override
    public UserRespVO detail(Long userId) {
        UserRespVO respVO = new UserRespVO();
        if(userId != null) {
            SysUserDO sysUserDO = userMapper.selectById(userId);
            respVO = BeanUtil.toBean(sysUserDO, UserRespVO.class);
            List<Long> roleIds = userRoleMapper.selectList(SysUserRoleDO::getUserId, userId).stream().map(SysUserRoleDO::getRoleId).toList();
            respVO.setRoleIds(roleIds);
        }
        List<SysRoleDO> roleDOList = roleMapper.selectList(SysRoleDO::getState, StateEnum.ZERO.value());
        List<UserRoleRespVO> roles = BeanUtil.copyToList(roleDOList, UserRoleRespVO.class);
        respVO.setRoles(roles);
        return respVO;
    }

    @Override
    public void add(UserSaveReqVO reqVO) {
        SysUserDO sysUserDO = BeanUtil.toBean(reqVO, SysUserDO.class);
        AES aes = new AES(PASSWORD_KEY.getBytes());
        sysUserDO.setPassword(aes.encryptBase64(reqVO.getPassword()));
        sysUserDO.setCreateTime(LocalDateTime.now());
        sysUserDO.setUpdateTime(LocalDateTime.now());
        userMapper.insert(sysUserDO);
        if(CollUtil.isNotEmpty(reqVO.getRoleIds())) {
            userRoleMapper.insert(reqVO.getRoleIds(), sysUserDO.getId());
        }
    }

    @Override
    public void update(UserSaveReqVO reqVO) {
        SysUserDO sysUserDO = BeanUtil.toBean(reqVO, SysUserDO.class);
        sysUserDO.setUpdateTime(LocalDateTime.now());
        userMapper.updateById(sysUserDO);
        userRoleMapper.deleteByUserId(sysUserDO.getId());
        if(CollUtil.isNotEmpty(reqVO.getRoleIds())) {
            userRoleMapper.insert(reqVO.getRoleIds(), sysUserDO.getId());
        }
    }

}
