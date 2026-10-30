package cn.haigle.virtue.service.user;

import cn.haigle.virtue.common.constants.StateEnum;
import cn.haigle.virtue.common.exception.ServiceException;
import cn.haigle.virtue.common.pojo.PageResult;
import cn.haigle.virtue.common.web.Constant;
import cn.haigle.virtue.controller.admin.user.vo.UserInfoVO;
import cn.haigle.virtue.controller.admin.user.vo.UserPageReqVO;
import cn.haigle.virtue.controller.admin.user.vo.UserPageRespVO;
import cn.haigle.virtue.controller.admin.user.vo.UserRespVO;
import cn.haigle.virtue.controller.admin.user.vo.UserRoleRespVO;
import cn.haigle.virtue.controller.admin.user.vo.UserSaveReqVO;
import cn.haigle.virtue.mapper.dataobject.role.SysRoleDO;
import cn.haigle.virtue.mapper.dataobject.user.SysUserDO;
import cn.haigle.virtue.mapper.dataobject.user.SysUserRoleDO;
import cn.haigle.virtue.mapper.mysql.role.RoleMapper;
import cn.haigle.virtue.mapper.mysql.user.UserMapper;
import cn.haigle.virtue.mapper.mysql.user.UserRoleMapper;
import cn.haigle.virtue.service.auth.RefreshTokenService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 用户接口实现
 *
 * @author haigle
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private RefreshTokenService refreshTokenService;

    @Override
    public UserInfoVO getInfo(Long userId) {
        SysUserDO user = userMapper.selectById(userId);
        if (user == null) {
            throw new ServiceException("用户不存在");
        }
        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtils.copyProperties(user, userInfoVO);
        userInfoVO.setRoles(roleMapper.selectRoleMarkByUserId(userId));
        return userInfoVO;
    }

    @Override
    public PageResult<UserPageRespVO> page(UserPageReqVO reqVO) {
        PageResult<SysUserDO> pageResult = userMapper.selectPage(reqVO);
        List<UserPageRespVO> list = pageResult.getList().stream().map(user -> {
            UserPageRespVO vo = new UserPageRespVO();
            BeanUtils.copyProperties(user, vo);
            vo.setStatus(parseState(user.getState()));
            vo.setMobile(user.getPhone());
            return vo;
        }).toList();
        return new PageResult<>(list, pageResult.getTotal(), pageResult.getPageNo());
    }

    @Override
    public UserRespVO detail(Long userId) {
        UserRespVO respVO = new UserRespVO();
        if (userId != null) {
            SysUserDO user = userMapper.selectById(userId);
            if (user != null) {
                BeanUtils.copyProperties(user, respVO);
                respVO.setStatus(parseState(user.getState()));
                respVO.setMobile(user.getPhone());
                List<Long> roleIds = userRoleMapper.selectList(SysUserRoleDO::getUserId, userId).stream()
                        .map(SysUserRoleDO::getRoleId)
                        .toList();
                respVO.setRoleIds(roleIds);
            }
        }
        List<SysRoleDO> roleDOList = roleMapper.selectList(SysRoleDO::getState, StateEnum.ZERO.value());
        respVO.setRoles(roleDOList.stream().map(this::toRoleOption).toList());
        return respVO;
    }

    private UserRoleRespVO toRoleOption(SysRoleDO role) {
        UserRoleRespVO vo = new UserRoleRespVO();
        vo.setId(role.getId());
        vo.setName(role.getName());
        return vo;
    }

    @Override
    public void add(UserSaveReqVO reqVO) {
        SysUserDO user = toDO(reqVO);
        user.setState(reqVO.getStatus() != null ? reqVO.getStatus().toString() : StateEnum.ZERO.value());
        String rawPassword = StringUtils.hasText(reqVO.getPassword())
                ? reqVO.getPassword() : Constant.DEFAULT_PASSWORD;
        user.setPassword(passwordEncoder.encode(rawPassword));
        userMapper.insert(user);
        if (reqVO.getRoleIds() != null && !reqVO.getRoleIds().isEmpty()) {
            userRoleMapper.insert(reqVO.getRoleIds(), user.getId());
        }
    }

    @Override
    public void update(UserSaveReqVO reqVO) {
        SysUserDO user = toDO(reqVO);
        // 修改用户不在这里改密码，避免明文覆盖 BCrypt 哈希
        user.setPassword(null);
        if (reqVO.getStatus() != null) {
            user.setState(reqVO.getStatus().toString());
        }
        userMapper.updateById(user);
        userRoleMapper.deleteByUserId(user.getId());
        if (reqVO.getRoleIds() != null && !reqVO.getRoleIds().isEmpty()) {
            userRoleMapper.insert(reqVO.getRoleIds(), user.getId());
        }
        // 停用后立即失效
        if (reqVO.getStatus() != null && !StateEnum.ZERO.value().equals(String.valueOf(reqVO.getStatus()))) {
            refreshTokenService.revokeAll(user.getId());
        }
    }

    private SysUserDO toDO(UserSaveReqVO reqVO) {
        SysUserDO user = new SysUserDO();
        BeanUtils.copyProperties(reqVO, user);
        if (!StringUtils.hasText(user.getPhone())) {
            user.setPhone(reqVO.getMobile());
        }
        return user;
    }

    private Integer parseState(String state) {
        if (state == null || state.isBlank()) {
            return null;
        }
        return Integer.valueOf(state);
    }

    @Override
    public void delete(Long userId) {
        userMapper.deleteById(userId);
        userRoleMapper.deleteByUserId(userId);
        refreshTokenService.revokeAll(userId);
    }

    @Override
    public void deleteList(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return;
        }
        ids.forEach(this::delete);
    }

    @Override
    public void updatePassword(Long userId, String password) {
        SysUserDO user = new SysUserDO();
        user.setId(userId);
        user.setPassword(passwordEncoder.encode(password));
        userMapper.updateById(user);
        // 改密后强制重新登录
        refreshTokenService.revokeAll(userId);
    }

    @Override
    public void updateStatus(Long userId, Integer status) {
        SysUserDO user = new SysUserDO();
        user.setId(userId);
        user.setState(status.toString());
        userMapper.updateById(user);
        refreshTokenService.revokeAll(userId);
    }

    @Override
    public List<UserRespVO> simpleList() {
        List<SysUserDO> userDOList = userMapper.selectList(SysUserDO::getState, StateEnum.ZERO.value());
        return userDOList.stream().map(user -> {
            UserRespVO vo = new UserRespVO();
            BeanUtils.copyProperties(user, vo);
            vo.setStatus(parseState(user.getState()));
            vo.setMobile(user.getPhone());
            return vo;
        }).toList();
    }

}
