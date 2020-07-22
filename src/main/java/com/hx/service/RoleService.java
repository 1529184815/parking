package com.hx.service;


import com.github.pagehelper.PageInfo;
import com.hx.bean.PageParam;
import com.hx.bean.Role;

import java.util.List;

public interface RoleService {
    /**
     * 添加角色
     * @param role
     * @return
     */
    int addRole(Role role);
    /**
     * 获得当前页面的角色
     */
    PageInfo<Role> getAllRole(PageParam<Role> pageParam);
    /**
     * 查询一个角色
     */
    Role getRole(String roleId);
    /**
     * 修改角色
     */
    int updateRole(Role role);
    /**
     * 根据id删除角色
     */
    int deleteRole(String roleId);
    Role getRoleByName(String roleName);

    List<Role> getRoleList();
}
