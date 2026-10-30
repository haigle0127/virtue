package cn.haigle.virtue.service.role;

import cn.haigle.virtue.common.pojo.PageResult;
import cn.haigle.virtue.controller.admin.role.vo.*;

import java.util.List;

/**
 * 角色接口
 * @author haigle
 * @date 2026/4/29 15:10
 */
public interface RoleService {

    PageResult<RolePageRespVO> page(RolePageReqVO reqVO);

    RoleRespVO detail(Long roleId);

    void add(RoleSaveReqVO reqVO);

    void update(RoleSaveReqVO reqVO);

    void delete(Long roleId);

    List<RoleRespVO> simpleList();

}
