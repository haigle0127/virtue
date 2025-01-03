package cn.haigle.virtue.service.user;

import cn.haigle.virtue.common.pojo.PageResult;
import cn.haigle.virtue.controller.admin.role.vo.RoleSaveReqVO;
import cn.haigle.virtue.controller.admin.user.vo.*;

/**
 * 用户接口
 * @author haigle
 * @date 2019-06-06 21:12
 */
public interface UserService {

    UserInfoVO getInfo(Long userId);

    PageResult<UserPageRespVO> page(UserPageReqVO reqVO);

    UserRespVO detail(Long userId);

    void add(UserSaveReqVO reqVO);

    void update(UserSaveReqVO reqVO);

}
