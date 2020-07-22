package com.hx.dao;

import com.hx.bean.Role;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface RoleDao extends Mapper<Role> {

    @Results(id = "role",value = {
            @Result(column = "role_id",property = "roleId"),
            @Result(column = "role_name",property = "roleName")
    })
    @Select("select * from role where role_name = #{roleName}")
    Role getRoleByName(String roleName);
}
