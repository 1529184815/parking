package com.hx.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hx.ModelMapUtil;
import com.hx.bean.PageParam;
import com.hx.bean.Result;
import com.hx.bean.Seat;
import com.hx.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/seat")
public class SeatController {
    @Autowired
    private SeatService seatService;
    @PostMapping("/getSeatPage")
    public Result getSeatPage(@RequestBody PageParam<Seat> pageParam){
        Result result = new Result();
        result.setStatus(1);
        result.setMessage("查询成功");
        result.setInfo(seatService.getSeatPage(pageParam));
        return result;
    }
    @PostMapping("/deleteSeat")
    public Result deleteRole(@RequestBody Seat seat){
        Result result = new Result();
        Integer res = seatService.deleteSeat(seat.getSeatId());
        result.setStatus(1);
        result.setMessage("删除成功");
        if(res != 1 ){
            result.setStatus(0);
            result.setMessage("删除失败");
        }
        return result;
    }
    @PostMapping("/addSeat")
    public Result addUser(@RequestBody Seat seat){
        Result result = new Result();
        result.setStatus(1);
        result.setMessage("添加成功");
        Integer res = seatService.addSeat(seat);
        if(res == 0){
            result.setStatus(0);
            result.setMessage("添加失败");
        }else if(res == -1){
            result.setStatus(0);
            result.setMessage("座位号重复");
        }
        return result;
    }
    @PostMapping("/getSeat")
    public Result getUser(@RequestBody Seat seat){
        Result result = new Result();
        result.setInfo(1);
        result.setMessage("获取成功");
        result.setInfo(seatService.getSeat(seat.getSeatId()));
        return result;
    }

    @PostMapping("/updateSeat")
    public Result updateSeat(@RequestBody Seat seat){
        Result result = new Result();
        result.setStatus(1);
        result.setMessage("更新成功");
        Integer res = seatService.updateSeat(seat);
        if(res!=1){
            result.setStatus(0);
            result.setMessage("更新失败");
        }
        return result;
    }

    @GetMapping("/getEnableSeat")
    public Result getEnableSeat(){
        Result result = new Result();
        result.setStatus(1);
        result.setMessage("获取成功");
        result.setInfo(seatService.getEnableSeat());
        return result;
    }

    @GetMapping("getAllSeatList")
    public Result getAllSeatList(){
        Result result = new Result();
        result.setStatus(1);
        result.setMessage("获取成功");
        result.setInfo(seatService.getAllSeatList());
        return result;
    }
}
