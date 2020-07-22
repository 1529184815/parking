package com.hx.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hx.bean.PageParam;
import com.hx.bean.Result;
import com.hx.bean.Role;
import com.hx.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

@RestController
@RequestMapping("/role")
@SessionAttributes(value = {"role","selectRole"})
public class RoleController {
    @Autowired
    private RoleService roleService;
    @PostMapping("/addRole")
    public Result addRole(@RequestBody Role role){
        Result result = new Result();
        result.setStatus(1);
        result.setMessage("添加成功");
        Integer res = roleService.addRole(role);
        if(res != 1){
            result.setStatus(0);
            result.setMessage("添加失败");
        }
        return result;
    }
    @PostMapping("/getRolePage")
    public Result getRolePage(@RequestBody PageParam<Role> pageParam){
        Result result = new Result();
        result.setStatus(1);
        result.setMessage("查询成功");
        result.setInfo(roleService.getAllRole(pageParam));
        return result;
    }
    @PostMapping("/updateRole")
    public Result updateRole(@RequestBody Role role){
        Result result = new Result();
        result.setStatus(1);
        result.setMessage("更新成功");
        Integer res = roleService.updateRole(role);
        if(res!=1){
            result.setStatus(0);
            result.setMessage("更新失败");
        }
        return result;
    }
    @PostMapping("/deleteRole")
    public Result deleteRole(@RequestBody Role role){
        Result result = new Result();
        Integer res = roleService.deleteRole(role.getRoleId());
        result.setStatus(1);
        result.setMessage("删除成功");
        if(res == 0 ){
            result.setStatus(0);
            result.setMessage("删除失败");
        }else if(res == -1){
            result.setStatus(0);
            result.setMessage("无法删除该角色，该角色下还有用户");
        }
        return result;
    }
    @PostMapping("/getRole")
    public Result getRoleById(@RequestBody Role role){
        Result result = new Result();
        result.setInfo(1);
        result.setMessage("获取成功");
        result.setInfo(roleService.getRole(role.getRoleId()));
        return result;
    }

    @GetMapping("getRoleList")
    public Result getAllRole(){
        Result result = new Result();
        result.setInfo(1);
        result.setMessage("获取成功");
        result.setInfo(roleService.getRoleList());
        return result;
    }
}
