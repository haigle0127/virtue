package cn.haigle.virtue.service.role;

import cn.haigle.virtue.common.pojo.PageResult;
import cn.haigle.virtue.controller.admin.role.vo.*;

public interface RoleService {

    PageResult<RolePageRespVO> page(RolePageReqVO reqVO);

    RoleRespVO detail(Long roleId);

    void add(RoleSaveReqVO reqVO);

    void update(RoleSaveReqVO reqVO);

    void updateDataScope(RoleSaveReqVO reqVO);

    void updateState(RoleUpdateStateReqVO reqVO);

    void delete(Long menuId);

}
