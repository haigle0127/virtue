package cn.haigle.around.admin.login.dao;

import cn.haigle.around.admin.login.entity.bo.AdminRegisterBO;
import cn.haigle.around.admin.login.entity.bo.AdminUserInfoBO;
import cn.haigle.around.admin.login.entity.bo.AdminUserLoginBO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * 用户登录DAO
 * @author haigle
 * @date 2019/6/20 16:36
 */
@Mapper
@Repository
public interface AdminLoginDao {

    /**
     * 根据邮箱获取信息
     * @param account->email
     * @return AdminUserLoginPo 用户所有信息
     * @author haigle
     * @date 2019/3/6 10:39
     */
    @Select("select * from sys_user where email = #{account}")
    AdminUserLoginBO getUserByEmail(String account);

    /**
     * 根据邮箱获取信息
     * @param account->phone
     * @return AdminUserLoginPo 用户所有信息
     * @author haigle
     * @date 2019/9/4 13:01
     */
    @Select("select * from sys_user where phone = #{account}")
    AdminUserLoginBO getUserByPhone(String account);

    /**
     * 根据账号获取信息
     * @param account String
     * @return AdminUserLoginPo 用户所有信息
     * @author haigle
     * @date 2019/9/4 13:01
     */
    @Select("select * from sys_user where account = #{account}")
    AdminUserLoginBO getUserByAccount(String account);

    /**
     * 获取用户登陆信息
     * @param uid 用户ID
     * @author haigle
     * @date 2020/11/29 21:09
     */
    @Select("select username, email, phone, avatar, introduction, create_time birth from sys_user where id = #{uid}")
    AdminUserInfoBO getAdminUserInfo(Long uid);

    /**
     * 判断是否有用户
     * @param email 邮箱
     * @return Long
     * @author haigle
     * @date 2019/6/21 9:41
     */
    @Select("select IFNULL(NULL, 0) from sys_user where email = #{email}")
    Long getIsEmail(String email);

    /**
     * 查询用户菜单权限
     * @param uid 用户ID
     * @return List<String> 权限 power
     * @author haigle
     * @date 2019-06-09 17:51
     */
    @Select("SELECT sm.power FROM sys_menu sm " +
            "JOIN sys_role_menu srm ON srm.menu_id = sm.id " +
            "JOIN sys_user_role sur ON sur.role_id = srm.role_id " +
            "WHERE sur.user_id = #{uid} ")
    Set<String> findPermissionsById(Long uid);

    /**
     * 查询用户角色
     * @param uid 用户ID
     * @return Set<String>
     * @author haigle
     * @date 2021/3/8 14:48
     */
    @Select("SELECT `name` FROM sys_role sr " +
            "LEFT JOIN sys_user_role sur ON sur.role_id = sr.id " +
            "WHERE sur.user_id = #{uid}")
    Set<String> findRolesById(Long uid);

    /**
     * 保存用户
     * @param adminRegisterPo RegisterPo
     * @author haigle
     * @date 2019/6/20 16:37
     */
    @Insert("INSERT INTO `sys_user` (id, email, `password`, salt, create_by, create_time, update_by, update_time) VALUES(#{id}, #{email}, #{password}, #{salt}, #{id}, now(), #{id}, now())")
    void save(AdminRegisterBO adminRegisterPo);

}
