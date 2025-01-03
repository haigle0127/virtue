package cn.haigle.virtue.service.dept;

import cn.haigle.virtue.controller.admin.dept.vo.*;
import cn.haigle.virtue.controller.admin.menu.vo.RouterVO;

import java.util.List;

/**
 * 菜单、权限接口
 * @author haigle
 * @date 2024/11/16 12:55
 */
public interface DeptService {

    List<DeptRespVO> list(DeptReqVO reqVO);

    DeptRespVO detail(Long menuId);

    void add(DeptSaveReqVO reqVO);

    void update(DeptSaveReqVO reqVO);

    void delete(Long menuId);

    List<DeptRespVO> selectTreeByUserId(Long userId);

    List<RouterVO> buildDepts(List<DeptRespVO> menus);

    List<DeptTreeDTO> userDeptTree(Long userId);

    RoleDeptTreeRespVO roleDeptTree(Long roleId);


}
