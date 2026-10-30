package cn.haigle.virtue.service.user;

import cn.haigle.virtue.common.pojo.PageResult;
import cn.haigle.virtue.controller.admin.user.vo.*;

import java.util.List;

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

    void delete(Long userId);

    void deleteList(List<Long> ids);

    void updatePassword(Long userId, String password);

    void updateStatus(Long userId, Integer status);

    List<UserRespVO> simpleList();

}
