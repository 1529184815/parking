package com.hx.dao;

import com.hx.bean.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface UserDao extends Mapper<User> {

    @Results(id = "user" ,value = {
            @Result(column = "user_id",property = "userId"),
            @Result(column = "role_id",property = "roleId"),
            @Result(column = "user_name",property = "userName"),
            @Result(column = "real_name",property = "realName"),
            @Result(column = "user_pwd",property = "userPwd"),
            @Result(column = "user_phone",property = "userPhone")
    })
    @Select("select * from user where user_id=#{userId}")
    User getUserByUsername(@Param("userId") String userId);

    @Results(id = "users" ,value = {
            @Result(column = "user_id",property = "userId"),
            @Result(column = "role_id",property = "roleId"),
            @Result(column = "user_name",property = "userName"),
            @Result(column = "real_name",property = "realName"),
            @Result(column = "user_pwd",property = "userPwd"),
            @Result(column = "user_phone",property = "userPhone"),
            @Result(column = "role_name",property = "roleName")
    })
    @Select("select * from user u inner join role r on u.role_id=r.role_id where user_id like '%${keyWorld}%'")
    List<User> getAllUser(@Param("keyWorld") String keyWorld);

    @Update("update user set user_pwd = #{newPassword} where user_id = #{userId}" )
    Integer updatePassword(@Param("userId") String userId,@Param("newPassword")String newPassword);

    @ResultMap("user")
    @Select("select * from user where user_id=#{userId} and user_pwd = #{password}")
    User getUser(@Param("userId")String username,@Param("password")String password);
}
