package com.hx.service;

import com.github.pagehelper.PageInfo;
import com.hx.bean.PageParam;
import com.hx.bean.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    /**
     * 添加一个用户
     */
    int addUser(User user);
    /**
     * 获得所有用户
     */
    PageInfo<User> getAllUser(PageParam<User> pageParam);
    /**
     * 删除一个用户
     */
    int deleteUser(String userId);
    /**
     * 根据id获得一个用户
     */
    User getUserBykey(String userId);
    /**
     * 修改用户
     */
    int updateUser(User user);

    /**
     * 修改密码
     * @param user
     * @return
     */
    Integer changePwd(User user);

    Integer updatePassword(User user);
}
