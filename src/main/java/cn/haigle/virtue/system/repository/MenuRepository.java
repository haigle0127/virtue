package cn.haigle.virtue.system.repository;

import cn.haigle.virtue.common.base.repository.BaseRepository;
import cn.haigle.virtue.system.entity.SysMenuEntity;

import java.util.Optional;

public interface MenuRepository extends BaseRepository<SysMenuEntity> {

    /**
     * 查询菜单
     * @param menuType 菜单类型
     * @return Optional<SysMenuEntity>
     * @author haigle
     * @date 2021/12/3 16:49
     */
    Optional<SysMenuEntity> findByMenuType(String menuType);

}
