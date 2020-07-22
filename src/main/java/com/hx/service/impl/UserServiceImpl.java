package com.hx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hx.bean.PageParam;
import com.hx.bean.User;
import com.hx.dao.RoleDao;
import com.hx.dao.UserDao;
import com.hx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    /**
     * 登陆
     *
     * @param userId
     * @param userPwd
     * @return
     */
    @Override
    public User getUser(String userId, String userPwd) {
        User user = userDao.getUser(userId, userPwd);
        return user;
    }

    @Override
    public int addUser(User user) {
        return userDao.insert(user);
    }

    @Override
    public PageInfo<User> getAllUser(PageParam<User> pageParam) {
        PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
        PageInfo pageInfo = new PageInfo(userDao.getAllUser(pageParam.getKeyWorld()));
        return pageInfo;
    }

    @Override
    public int deleteUser(String userId) {
        return userDao.deleteByPrimaryKey(userId);
    }

    @Override
    public User getUserBykey(String userId) {
        return userDao.selectByPrimaryKey(userId);
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateByPrimaryKey(user);
    }

    @Override
    public Integer changePwd(User user) {
        return userDao.updateByPrimaryKey(user);
    }

}
