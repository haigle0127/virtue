package cn.haigle.virtue.mapper.mysql.menu;

import cn.haigle.virtue.common.mybatis.mapper.BaseMapperX;
import cn.haigle.virtue.common.mybatis.query.LambdaQueryWrapperX;
import cn.haigle.virtue.controller.admin.menu.vo.MenuReqVO;
import cn.haigle.virtue.controller.admin.menu.vo.MenuRespVO;
import cn.haigle.virtue.mapper.dataobject.menu.SysMenuDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 菜单Mapper
 * @author haigle
 * @date 2019/8/23 10:16
 */
@Mapper
public interface MenuMapper extends BaseMapperX<SysMenuDO> {

    default List<SysMenuDO> selectList(MenuReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<SysMenuDO>()
                .likeIfPresent(SysMenuDO::getName, reqVO.getName())
                .eqIfPresent(SysMenuDO::getState, reqVO.getState()));
    }

    List<MenuRespVO> selectTreeAll();

    List<MenuRespVO> selectTreeByUserId(@Param("userId") Long userId);

    List<Long> selectMenuIdByRoleId(@Param("roleId") Long roleId, @Param("menuCheckStrictly") Boolean menuCheckStrictly);

    List<MenuRespVO> selectMenuList();

    List<MenuRespVO> selectMenuListByUserId(@Param("userId") Long userId);

    List<String> selectMenuPowerByUserId(@Param("userId") Long userId);

}
