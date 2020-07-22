package com.hx.controller;

import com.hx.bean.PageParam;
import com.hx.bean.Result;
import com.hx.bean.Temp;
import com.hx.service.TempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/temp")
public class TempController {
    @Autowired
    private TempService tempService;
    @PostMapping("/getTempPage")
    public Result getTempPage(@RequestBody PageParam<Temp> pageParam){
        Result result = new Result();
        result.setStatus(1);
        result.setMessage("查询成功");
        result.setInfo(tempService.getAllTemp(pageParam));
        return result;
    }
    @PostMapping("/addTemp")
    public Result addTemp(@RequestBody Temp temp){
        Result result = new Result();
        result.setStatus(1);
        result.setMessage("添加成功");
        Integer res = tempService.addTemp(temp);
        if(res != 1){
            result.setStatus(0);
            result.setMessage("添加失败");
        }
        return result;
    }
    @PostMapping("/deleteTemp")
    public Result deleteTemp(@RequestBody Temp temp){
        Result result = new Result();
        Integer res = tempService.deletTemp(temp.getTempId());
        result.setStatus(1);
        result.setMessage("删除成功");
        if(res != 1 ){
            result.setStatus(0);
            result.setMessage("删除失败");
        }
        return result;
    }
    @PostMapping("/calculateTempMoney")
    public Result calculator(@RequestBody Temp temp){
        Result result = new Result();
        result.setStatus(1);
        result.setMessage("结算成功");
        Integer res = tempService.calculator(temp);
        if(res != 1 ){
            result.setStatus(0);
            result.setMessage("结算失败");
        }
        return result;
    }
}
