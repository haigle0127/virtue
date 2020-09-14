package cn.haigle.around.admin.user.dao;

import cn.haigle.around.admin.user.dao.provider.AdminUserDaoSqlProvider;
import cn.haigle.around.admin.user.entity.ao.AdminUserAO;
import cn.haigle.around.admin.user.entity.dto.AdminUserDTO;
import cn.haigle.around.admin.menu.entity.bo.AdminTreeBO;
import cn.haigle.around.admin.user.entity.vo.AdminUserVo;
import cn.haigle.around.common.base.page.Page;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户
 * @author haigle
 * @date 2019-06-09 14:21
 */
@Mapper
@Repository
public interface AdminUserDao {

    /**
     * 获取管理用户列表
     * @param page 分页
     * @param name 用户名
     * @return List<AdminUserVo> 用户列表
     * @author haigle
     * @date 2019-08-14 20:34
     */
    @Results(value = {
            @Result(column = "id", property = "id"),
            @Result(id = true, property = "roles", column = "id", many = @Many(select = "getRoleList"))
    })
    @SelectProvider(type = AdminUserDaoSqlProvider.class, method = "list")
    List<AdminUserVo> list(Page<AdminUserVo> page, String name);

    /**
     * 获取管理用户总数
     * @param page 分页
     * @param name 用户名
     * @return int 用户总数
     * @author haigle
     * @date 2019-08-14 21:12
     */
    @SelectProvider(type = AdminUserDaoSqlProvider.class, method = "getTotal")
    int getTotal(Page<AdminUserVo> page, String name);

    /**
     * 新增用户
     * @param m 用户信息
     * @param uid 操作人ID
     * @author haigle
     * @date 2019/9/5 17:06
     */
    @InsertProvider(type = AdminUserDaoSqlProvider.class, method = "save")
    void save(AdminUserDTO m, Long uid);

    /**
     * 保存用户角色关联
     * @param userId 用户ID
     * @param roleList 角色ID列表
     * @author haigle
     * @date 2019/9/6 9:19
     */
    @SelectProvider(type = AdminUserDaoSqlProvider.class, method = "saveUserRole")
    void saveUserRole(Long userId, List<Long> roleList);

    /**
     * 更新用户
     * @param m 用户信息
     * @param uid 操作人ID
     * @author haigle
     * @date 2019/9/6 8:50
     */
    @UpdateProvider(type = AdminUserDaoSqlProvider.class, method = "update")
    void update(AdminUserAO m, Long uid);

    /**
     * 删除用户
     * @param id 用户ID
     * @author haigle
     * @date 2019/9/6 9:08
     */
    @Delete("DELETE FROM sys_user WHERE id=#{id}")
    void delete(Long id);

    /**
     * 删除用户角色
     * @param id 用户ID
     * @author haigle
     * @date 2019/9/6 9:09
     */
    @Delete("DELETE FROM sys_user_role WHERE user_id=#{id}")
    void deleteUserRole(Long id);

    /**
     * 查询用户信息及角色，菜单，权限
     * @param uid 用户ID
     * @return AdminUserVo 返回实体
     * @author haigle
     * @date 2019-06-09 14:22
     */
    @Results(value = {
            @Result(column = "id", property = "id"),
            @Result(id = true, property = "roles", column = "id", many = @Many(select = "getMenuList"))
    })
    @Select("SELECT id, username, email, phone, avatar, introduction, birth, school, education FROM sys_user WHERE id = #{uid}")
    AdminUserVo getUserWithRoleMenuListById(Long uid);

    /**
     * 查询用户角色
     * @param uid 用户ID
     * @return List<Role> 用户角色列表
     * @author haigle
     * @date 2019-06-09 14:32
     */
    @Select("SELECT sr.name FROM sys_role sr " +
            "LEFT JOIN sys_user_role sur ON sur.role_id = sr.id " +
            "WHERE sur.user_id = #{uid} ")
    List<String> getRoleList(Long uid);

    /**
     * 查询用户菜单权限
     * 注：GROUP BY srm.menu_id 操作去重
     * @param uid 用户ID
     * @return List<String> 权限 power
     * @author haigle
     * @date 2019-06-09 17:51
     */
    @Select("SELECT sm.power FROM sys_menu sm " +
            "LEFT JOIN sys_role_menu srm ON srm.menu_id = sm.id " +
            "LEFT JOIN sys_role sr ON sr.id = srm.role_id " +
            "LEFT JOIN sys_user_role sur ON sur.role_id = sr.id " +
            "WHERE sur.user_id = #{uid} GROUP BY sm.power ")
    List<String> getMenuList(Long uid);

    /**
     * 判断是否有邮箱
     * @param email 邮箱
     * @return Long
     * @author haigle
     * @date 2019/6/21 9:41
     */
    @Select("select IFNULL(NULL, 0) from sys_user where email = #{email}")
    Long getIsEmail(String email);

    /**
     * 判断是否有手机
     * @param phone 手机
     * @return Long
     * @author haigle
     * @date 2019/6/21 9:41
     */
    @Select("select IFNULL(NULL, 0) from sys_user where phone = #{phone}")
    Long getIsPhone(String phone);

    /**
     * 判断是否有用户名
     * @param name 用户名
     * @return Long
     * @author haigle
     * @date 2019/6/21 9:41
     */
    @Select("select IFNULL(NULL, 0) from sys_user where username = #{name}")
    Long getIsName(String name);

    /**
     * 判断是否有邮箱
     * @param email 邮箱
     * @param id 用户ID
     * @return Long
     * @author haigle
     * @date 2019/6/21 9:41
     */
    @Select("select IFNULL(NULL, 0) from sys_user where id != #{id} and email = #{email}")
    Long getIsEmailNotId(String email, Long id);

    /**
     * 判断是否有手机
     * @param phone 手机
     * @param id 用户ID
     * @return Long
     * @author haigle
     * @date 2019/6/21 9:41
     */
    @Select("select IFNULL(NULL, 0) from sys_user where id != #{id} and phone = #{phone}")
    Long getIsPhoneNotId(String phone, Long id);

    /**
     * 判断是否有用户名
     * @param name 用户名
     * @param id 用户ID
     * @return Long
     * @author haigle
     * @date 2019/6/21 9:41
     */
    @Select("select IFNULL(NULL, 0) from sys_user where id != #{id} and username = #{name}")
    Long getIsNameNotId(String name, Long id);

    /**
     * 获取所有角色
     * @return List<AdminTreeVo>
     * @author haigle
     * @date 2019/9/9 9:56
     */
    @Select("select id, name from sys_role")
    List<AdminTreeBO> getRoleAllList();

    /**
     * 用户下角色ID
     * @param uid 用户ID
     * @return List<Long> 角色ID列表
     * @author haigle
     * @date 2019/9/9 10:34
     */
    @Select("select role_id from sys_user_role where user_id = #{uid}")
    List<Long> getUserRoleList(Long uid);

}
