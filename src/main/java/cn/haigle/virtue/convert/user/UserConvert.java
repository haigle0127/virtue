package cn.haigle.virtue.convert.user;

import cn.haigle.virtue.common.pojo.PageResult;
import cn.haigle.virtue.controller.admin.user.vo.UserPageRespVO;
import cn.haigle.virtue.mapper.dataobject.user.SysUserDO;
import cn.hutool.core.bean.BeanUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户 Convert
 *
 * @author haigle
 */
public class UserConvert {

    public static PageResult<UserPageRespVO> convertPage(PageResult<SysUserDO> page) {
        PageResult<UserPageRespVO> result = new PageResult<>(page.getTotal(), page.getPageNo());
        List<UserPageRespVO> list = new ArrayList<>();
        page.getList().forEach(item -> list.add(BeanUtil.toBean(item, UserPageRespVO.class)));
        result.setList(list);
        return result;
    }

}
