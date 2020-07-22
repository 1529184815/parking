package com.hx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hx.bean.PageParam;
import com.hx.bean.Role;
import com.hx.bean.User;
import com.hx.dao.RoleDao;
import com.hx.dao.UserDao;
import com.hx.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserDao userDao;

    @Override
    public int addRole(Role role) {
        return roleDao.insert(role);
    }

    @Override
    public PageInfo<Role> getAllRole(PageParam<Role> pageParam) {
        Example example = new Example(Role.class);
        Example.Criteria criteria = example.createCriteria();
        if(pageParam.getKeyWorld() != null && pageParam.getKeyWorld() != ""){
            criteria.andLike("roleName","%"+pageParam.getKeyWorld()+"%");
        }
        PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
        PageInfo pageInfo = new PageInfo(roleDao.selectByExample(example));
        return pageInfo;
    }

    @Override
    public Role getRole(String roleId) {
        return roleDao.selectByPrimaryKey(roleId);
    }

    @Override
    @Transactional
    public int updateRole(Role role) {
        return roleDao.updateByPrimaryKey(role);
    }

    @Override
    @Transactional
    public int deleteRole(String roleId) {
        //判断当前角色有无用户
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roleId",roleId);
        List<User> users = userDao.selectByExample(example);
        if(users.size() == 0){
          return roleDao.deleteByPrimaryKey(roleId);
        }
        return -1;
    }

    @Override
    public Role getRoleByName(String roleName) {
        return roleDao.getRoleByName(roleName);
    }

    @Override
    public List<Role> getRoleList() {
        return roleDao.selectAll();
    }

}
