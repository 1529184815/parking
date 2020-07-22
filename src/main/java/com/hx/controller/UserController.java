package com.hx.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hx.bean.PageParam;
import com.hx.bean.Result;
import com.hx.bean.User;
import com.hx.dao.UserDao;
import com.hx.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

@RestController
@RequestMapping("/user")
@SessionAttributes(value = {"user_id","user_name","role_id","totlPages","selectUser"})
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    UserDao userDao;
    /**
     * 登陆
     * @param userId
     * @param userPwd
     * @return
     */
    @PostMapping("/login")
    public String Login(Model model, @RequestParam("user_id")String userId, @RequestParam("user_pwd")String userPwd){
        User user = userService.getUser(userId,userPwd);
        if(user!=null){
            model.addAttribute("user_id",user.getUserId());
            model.addAttribute("user_name",user.getUserName());
            model.addAttribute("role_id",user.getRoleId());
            return "forward:/Index.jsp";
        }
        return "forward:/Admin/_Error.jsp";
    }
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
    @GetMapping("/getUserById")
    public @ResponseBody
    ModelMap getUserById(Model model, @RequestParam("userId") String userId){
        ModelMap modelMap = new ModelMap();
        User user = userService.getUserBykey(userId);
        if(user!=null){
            model.addAttribute("selectUser",user);
            modelMap.put("ok",true);
            modelMap.put("msg","查询成功");
        }else{
            modelMap.put("ok",true);
            modelMap.put("msg","查询失败");
        }
        return modelMap;
    }

    @PostMapping("/changePwd")
    public @ResponseBody
    ModelMap changePwd(ModelMap model, String oldPwd, String newPwd){
        ModelMap modelMap = new ModelMap();
        String userId = (String) model.get("user_id");
        User user = userService.getUser(userId, oldPwd);
        if(user != null){
            user.setUserPwd(newPwd);
            Integer count = userService.changePwd(user);
            if(count==1){
                modelMap.put("ok",true);
                modelMap.put("msg","密码修改成功");
            }else{
                modelMap.put("ok",true);
                modelMap.put("msg","密码修改失败");
            }
        }
        return modelMap;
    }
}
