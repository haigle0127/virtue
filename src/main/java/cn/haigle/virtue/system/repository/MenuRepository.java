package cn.haigle.virtue.system.repository;

import cn.haigle.virtue.common.base.repository.BaseRepository;
import cn.haigle.virtue.system.entity.bo.SysMenuBo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单JPA
 * @author haigle
 * @date 2021/12/3 21:54
 */
@Service("menuRepository")
public interface MenuRepository extends BaseRepository<SysMenuBo> {

    /**
     * 查询菜单
     * @param menuType 菜单类型
     * @return List<SysMenuEntity>
     * @author haigle
     * @date 2021/12/3 16:49
     */
    List<SysMenuBo> findByMenuType(String menuType);

}
