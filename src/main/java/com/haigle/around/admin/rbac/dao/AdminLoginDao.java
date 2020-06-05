package com.haigle.around.admin.rbac.dao;

import com.haigle.around.admin.rbac.entity.ao.AdminLoginAo;
import com.haigle.around.admin.rbac.entity.po.AdminRegisterPo;
import com.haigle.around.admin.rbac.entity.po.AdminUserLoginPo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

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
     * @param adminLoginAo name->email
     * @return AdminUserLoginPo 用户所有信息
     * @author haigle
     * @date 2019/3/6 10:39
     */
    @Select("select * from sys_user where email = #{account}")
    AdminUserLoginPo getUserByEmail(AdminLoginAo adminLoginAo);

    /**
     * 根据邮箱获取信息
     * @param adminLoginAo name->phone
     * @return AdminUserLoginPo 用户所有信息
     * @author haigle
     * @date 2019/9/4 13:01
     */
    @Select("select * from sys_user where phone = #{account}")
    AdminUserLoginPo getUserByPhone(AdminLoginAo adminLoginAo);

    /**
     * 根据邮箱获取信息
     * @param adminLoginAo name
     * @return AdminUserLoginPo 用户所有信息
     * @author haigle
     * @date 2019/9/4 13:01
     */
    @Select("select * from sys_user where username = #{account}")
    AdminUserLoginPo getUserByUserName(AdminLoginAo adminLoginAo);

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
     * 保存用户
     * @param adminRegisterPo RegisterPo
     * @author haigle
     * @date 2019/6/20 16:37
     */
    @Insert("INSERT INTO `sys_user` (id, email, `password`, salt, create_by, create_time, update_by, update_time) VALUES(#{id}, #{email}, #{password}, #{salt}, #{id}, now(), #{id}, now())")
    void save(AdminRegisterPo adminRegisterPo);

}
