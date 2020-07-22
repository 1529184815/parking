package com.hx.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hx.bean.Fixed;
import com.hx.bean.PageParam;
import com.hx.bean.Result;
import com.hx.service.FixedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

@RestController
@RequestMapping("/fixed")
public class FixedController {
    @Autowired
    private FixedService fixedService;
    @PostMapping("/getFixedPage")
    public Result getFixedPage(@RequestBody PageParam<Fixed> pageParam){
        Result result = new Result();
        result.setStatus(1);
        result.setMessage("查询成功");
        result.setInfo(fixedService.getAllFixed(pageParam));
        return result;
    }

    @PostMapping("deleteFixed")
    public Result deleteFixed(@RequestBody Fixed fixed){
        Result result = new Result();
        Integer res = fixedService.deleteFixed(fixed.getFixedId());
        result.setStatus(1);
        result.setMessage("删除成功");
        if(res != 1 ){
            result.setStatus(0);
            result.setMessage("删除失败");
        }
        return result;
    }
    @PostMapping("getFixed")
    public Result getFixed(@RequestBody Fixed fixed){
        Result result = new Result();
        result.setStatus(1);
        result.setMessage("获取成功");
        result.setInfo(fixedService.getFixed(fixed.getFixedId()));
        return result;
    }

    @PostMapping("payMoney")
    public Result payMoney(@RequestBody Fixed fixed){
        Result result = new Result();
        Integer res = fixedService.payMoney(fixed.getFixedId(),fixed.getMoney());
        result.setStatus(1);
        result.setMessage("结算成功");
        if(res != 1 ){
            result.setStatus(0);
            result.setMessage("结算失败");
        }
        return result;
    }
}
