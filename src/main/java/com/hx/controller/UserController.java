package com.hx.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hx.bean.PageParam;
import com.hx.bean.Result;
import com.hx.bean.User;
import com.hx.dao.UserDao;
import com.hx.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    UserDao userDao;
    @PostMapping("/addUser")
    public Result addUser(@RequestBody User user){
        Result result = new Result();
        result.setStatus(1);
        result.setMessage("添加成功");
        Integer res = userService.addUser(user);
        if(res != 1){
            result.setStatus(0);
            result.setMessage("添加失败");
        }
        return result;
    }
    @PostMapping("getUserPage")
    public Result getAllUser(@RequestBody PageParam<User> pageParam){
        Result result = new Result();
        result.setStatus(1);
        result.setMessage("查询成功");
        result.setInfo(userService.getAllUser(pageParam));
        return result;
    }
    @PostMapping("/deleteUser")
    public Result deleteUser(@RequestBody User user){
        Result result = new Result();
        Integer res = userService.deleteUser(user.getUserId());
        result.setStatus(1);
        result.setMessage("删除成功");
        if(res != 1 ){
            result.setStatus(0);
            result.setMessage("删除失败");
        }
        return result;
    }
    @PostMapping("/getUser")
    public Result getUser(@RequestBody User user){
        Result result = new Result();
        result.setInfo(1);
        result.setMessage("获取成功");
        result.setInfo(userService.getUserBykey(user.getUserId()));
        return result;
    }
    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody User user){
        Result result = new Result();
        result.setStatus(1);
        result.setMessage("更新成功");
        Integer res = userService.updateUser(user);
        if(res!=1){
            result.setStatus(0);
            result.setMessage("更新失败");
        }
        return result;
    }

    @PostMapping("/updatePassword")
    public Result updatePassword(@RequestBody User user){
        Result result = new Result();
        result.setStatus(1);
        result.setMessage("更新成功");
        Integer res = userService.updatePassword(user);
        if(res == -1){
            result.setStatus(0);
            result.setMessage("密码错误");
        }
        if(res!=1){
            result.setStatus(0);
            result.setMessage("更新失败");
        }
        return result;
    }

    @GetMapping("getCurrentUser")
    public Result getCurrentUser(){
        Result result = new Result();
        result.setStatus(1);
        result.setMessage("获取成功");
        result.setInfo(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return result;
    }


}
