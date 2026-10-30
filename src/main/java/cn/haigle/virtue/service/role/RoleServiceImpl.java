package cn.haigle.virtue.service.role;

import cn.haigle.virtue.common.constants.StateEnum;
import cn.haigle.virtue.common.pojo.PageResult;
import cn.haigle.virtue.controller.admin.role.vo.RolePageReqVO;
import cn.haigle.virtue.controller.admin.role.vo.RolePageRespVO;
import cn.haigle.virtue.controller.admin.role.vo.RoleRespVO;
import cn.haigle.virtue.controller.admin.role.vo.RoleSaveReqVO;
import cn.haigle.virtue.mapper.dataobject.role.SysRoleDO;
import cn.haigle.virtue.mapper.mysql.role.RoleMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 角色接口实现
 *
 * @author haigle
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public PageResult<RolePageRespVO> page(RolePageReqVO reqVO) {
        PageResult<SysRoleDO> pageResult = roleMapper.selectPage(reqVO);
        List<RolePageRespVO> list = pageResult.getList().stream().map(role -> {
            RolePageRespVO vo = new RolePageRespVO();
            BeanUtils.copyProperties(role, vo);
            fillFrontFields(vo);
            return vo;
        }).toList();
        return new PageResult<>(list, pageResult.getTotal(), pageResult.getPageNo());
    }

    @Override
    public RoleRespVO detail(Long roleId) {
        SysRoleDO role = roleMapper.selectById(roleId);
        if (role == null) {
            return null;
        }
        RoleRespVO vo = new RoleRespVO();
        BeanUtils.copyProperties(role, vo);
        fillFrontFields(vo);
        return vo;
    }

    @Override
    public void add(RoleSaveReqVO reqVO) {
        SysRoleDO role = toDO(reqVO);
        roleMapper.insert(role);
    }

    @Override
    public void update(RoleSaveReqVO reqVO) {
        roleMapper.updateById(toDO(reqVO));
    }

    /**
     * code / remark / status 映射到 mark / description / state
     */
    private SysRoleDO toDO(RoleSaveReqVO reqVO) {
        SysRoleDO role = new SysRoleDO();
        BeanUtils.copyProperties(reqVO, role);
        if (StringUtils.hasText(reqVO.getCode())) {
            role.setMark(reqVO.getCode());
        }
        if (StringUtils.hasText(reqVO.getRemark())) {
            role.setDescription(reqVO.getRemark());
        }
        if (reqVO.getStatus() != null) {
            role.setState(reqVO.getStatus().toString());
        }
        if (reqVO.getSort() != null) {
            role.setSort(reqVO.getSort().toString());
        }
        return role;
    }

    @Override
    public void delete(Long roleId) {
        roleMapper.deleteById(roleId);
    }

    @Override
    public List<RoleRespVO> simpleList() {
        List<SysRoleDO> roleDOList = roleMapper.selectList(SysRoleDO::getState, StateEnum.ZERO.value());
        return roleDOList.stream().map(role -> {
            RoleRespVO vo = new RoleRespVO();
            BeanUtils.copyProperties(role, vo);
            fillFrontFields(vo);
            return vo;
        }).toList();
    }

    private void fillFrontFields(RolePageRespVO vo) {
        vo.setCode(vo.getMark());
        vo.setRemark(vo.getDescription());
        vo.setStatus(parseState(vo.getState()));
    }

    private void fillFrontFields(RoleRespVO vo) {
        vo.setCode(vo.getMark());
        vo.setRemark(vo.getDescription());
        vo.setStatus(parseState(vo.getState()));
    }

    private Integer parseState(String state) {
        if (state == null || state.isBlank()) {
            return null;
        }
        return Integer.valueOf(state);
    }

}
