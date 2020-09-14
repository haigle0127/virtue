package cn.haigle.around.admin.login.dao;

import cn.haigle.around.admin.login.entity.bo.AdminPaiBO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 验证码存储
 * @author haigle
 * @date 2019/6/3 16:51
 */
@Mapper
@Repository
public interface AdminPaiDao {

    /**
     * 保存缓存
     * @param type key
     * @param label value
     * @author haigle
     * @date 2019/3/6 18:36
     */
    @Insert("INSERT INTO sys_pai (type, label) VALUES(#{type}, #{label})")
    void save(String type, String label);

    /**
     * 删除缓存
     * @param type key
     * @author haigle
     * @date 2019/3/6 18:37
     */
    @Delete("DELETE FROM sys_pai WHERE type = #{type}")
    void delete(String type);

    /**
     * 获取缓存
     * @param type key
     * @return PaiEntity 值
     * @author haigle
     * @date 2019/3/6 18:37
     */
    @Select("SELECT * FROM sys_pai WHERE type = #{type}")
    AdminPaiBO getPaiPo(String type);

}
