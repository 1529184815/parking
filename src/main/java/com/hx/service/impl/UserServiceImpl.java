package com.hx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hx.bean.PageParam;
import com.hx.bean.User;
import com.hx.dao.RoleDao;
import com.hx.dao.UserDao;
import com.hx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

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

    @Override
    public Integer updatePassword(User user) {
        //判断密码
        User oldUser = userDao.getUser(user.getUserName(),user.getOldPassword());
        if (oldUser!=null) {
            return userDao.updatePassword(user.getUserName(), user.getNewPassword());
        }
        return -1;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getUserByUsername(username);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String userPwd = user.getUserPwd();
        if (userPwd!=null){
            String encodePassword = bCryptPasswordEncoder.encode(userPwd);//密码加密
            user.setUserPwd(encodePassword);
        }
        Set authoritiesSet = new HashSet();
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");
        authoritiesSet.add(authority);
        user.setAuthorities(authoritiesSet);
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return user;
    }
}
