package cn.haigle.virtue.service.dept;
import cn.haigle.virtue.controller.admin.dept.vo.*;
import cn.haigle.virtue.controller.admin.menu.vo.RouterVO;
import cn.haigle.virtue.mapper.dataobject.dept.SysDeptDO;
import cn.haigle.virtue.mapper.dataobject.role.SysRoleDO;
import cn.haigle.virtue.mapper.mysql.dept.DeptMapper;
import cn.haigle.virtue.mapper.mysql.role.RoleMapper;
import cn.hutool.core.bean.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 部门接口实现
 * @author haigle
 * @date 2024/11/16 12:46
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<DeptRespVO> list(DeptReqVO reqVO) {
        List<SysDeptDO> deptDOList = deptMapper.selectList(reqVO);
        return BeanUtil.copyToList(deptDOList, DeptRespVO.class);
    }

    @Override
    public DeptRespVO detail(Long menuId) {
        SysDeptDO sysDeptDO = deptMapper.selectById(menuId);
        return BeanUtil.toBean(sysDeptDO, DeptRespVO.class);
    }

    @Override
    public void add(DeptSaveReqVO reqVO) {
        SysDeptDO sysDeptDO = BeanUtil.toBean(reqVO, SysDeptDO.class);
        sysDeptDO.setCreateTime(LocalDateTime.now());
        sysDeptDO.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(sysDeptDO);
    }

    @Override
    public void update(DeptSaveReqVO reqVO) {
        SysDeptDO sysDeptDO = BeanUtil.toBean(reqVO, SysDeptDO.class);
        sysDeptDO.setUpdateTime(LocalDateTime.now());
        deptMapper.updateById(sysDeptDO);
    }

    @Override
    public void delete(Long deptId) {
        deptMapper.deleteById(deptId);
    }

    @Override
    public List<DeptRespVO> selectTreeByUserId(Long userId) {
        return List.of();
    }

    @Override
    public List<RouterVO> buildDepts(List<DeptRespVO> menus) {
        return List.of();
    }

    @Override
    public List<DeptTreeDTO> userDeptTree(Long userId) {
        List<SysDeptDO> depts = deptMapper.selectList();
        List<DeptRespVO> deptList = BeanUtil.copyToList(depts, DeptRespVO.class);
        return buildDeptTreeSelect(deptList);
    }

    @Override
    public RoleDeptTreeRespVO roleDeptTree(Long roleId) {
        RoleDeptTreeRespVO respVO = new RoleDeptTreeRespVO();
        SysRoleDO roleDO = roleMapper.selectById(roleId);
        respVO.setCheckedKeys(deptMapper.selectDeptListByRoleId(roleId, roleDO.getDeptCheckStrictly()));
        List<SysDeptDO> depts = deptMapper.selectList();
        List<DeptRespVO> deptList = BeanUtil.copyToList(depts, DeptRespVO.class);
        respVO.setDepts(buildDeptTreeSelect(deptList));
        return respVO;
    }

    /**
     * 构建前端所需要下拉树结构
     *
     * @param deptList 部门列表
     * @return 下拉树结构列表
     */
    private List<DeptTreeDTO> buildDeptTreeSelect(List<DeptRespVO> deptList) {
        List<DeptRespVO> deptTrees = buildDeptTree(deptList);
        return deptTrees.stream().map(DeptTreeDTO::new).collect(Collectors.toList());
    }

    /**
     * 构建前端所需要树结构
     *
     * @param deptList 部门列表
     * @return 树结构列表
     */
    private List<DeptRespVO> buildDeptTree(List<DeptRespVO> deptList) {
        List<DeptRespVO> returnList = new ArrayList<>();
        List<Long> tempList = deptList.stream().map(DeptRespVO::getId).toList();
        for(DeptRespVO dept : deptList) {
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if(!tempList.contains(dept.getParentId())) {
                recursionFn(deptList, dept);
                returnList.add(dept);
            }
        }
        if(returnList.isEmpty()) {
            returnList = deptList;
        }
        return returnList;
    }

    /**
     * 递归列表
     */
    private void recursionFn(List<DeptRespVO> list, DeptRespVO t) {
        // 得到子节点列表
        List<DeptRespVO> childList = getChildList(list, t);
        t.setChildren(childList);
        for(DeptRespVO tChild : childList) {
            if(hasChild(list, tChild)) {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<DeptRespVO> getChildList(List<DeptRespVO> list, DeptRespVO t) {
        List<DeptRespVO> tlist = new ArrayList<>();
        for(DeptRespVO n : list) {
            if(n.getParentId() != null && n.getParentId().longValue() == t.getId().longValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<DeptRespVO> list, DeptRespVO t) {
        return !getChildList(list, t).isEmpty();
    }

}
