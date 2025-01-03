package cn.haigle.virtue.mapper.mysql.dept;

import cn.haigle.virtue.common.mybatis.mapper.BaseMapperX;
import cn.haigle.virtue.common.mybatis.query.LambdaQueryWrapperX;
import cn.haigle.virtue.controller.admin.dept.vo.DeptReqVO;
import cn.haigle.virtue.mapper.dataobject.dept.SysDeptDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 部门Mapper
 * @author haigle
 * @date 2024-11-16 12:41
 */
@Mapper
public interface DeptMapper extends BaseMapperX<SysDeptDO> {

    default List<SysDeptDO> selectList(DeptReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<SysDeptDO>()
                .likeIfPresent(SysDeptDO::getName, reqVO.getName())
                .eqIfPresent(SysDeptDO::getState, reqVO.getState()));
    }

    /**
     * 查询部门树信息
     * @param roleId 角色ID
     * @param deptCheckStrictly 部门树选择项是否关联显示
     * @return 选中部门列表
     */
    List<Long> selectDeptListByRoleId(@Param("roleId") Long roleId, @Param("deptCheckStrictly") boolean deptCheckStrictly);


}
