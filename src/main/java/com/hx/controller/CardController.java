package com.hx.controller;

import com.hx.bean.Card;
import com.hx.bean.PageParam;
import com.hx.bean.Result;
import com.hx.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    private CardService cardService;
    /**
     * 获得IC卡信息
     */
    @ResponseBody
    @PostMapping("/getCardPage")
    public Result getAllCard(@RequestBody PageParam<Card> pageParam){
        Result result = new Result();
        result.setStatus(1);
        result.setMessage("查询成功");
        result.setInfo(cardService.getAllCard(pageParam));
        return result;
    }

    @PostMapping("/addCard")
    public Result addCard(@RequestBody Card card){
        Result result = new Result();
        result.setStatus(1);
        result.setMessage("添加成功");
        Integer res = cardService.addCard(card);
        if(res != 1){
            result.setStatus(0);
            result.setMessage("添加失败");
        }
        return result;
    }

    @PostMapping("/deleteCard")
    public Result deleteCard(@RequestBody Card card){
        Result result = new Result();
        Integer res = cardService.deleteCard(card.getCardId());
        result.setStatus(1);
        result.setMessage("删除成功");
        if(res != 1 ){
            result.setStatus(0);
            result.setMessage("删除失败");
        }
        return result;
    }

    @PostMapping("/getCard")
    public Result getCard(@RequestBody Card card){
        Result result = new Result();
        result.setInfo(1);
        result.setMessage("获取成功");
        result.setInfo(cardService.getCard(card.getCardId()));
        return result;
    }

    @PostMapping("/updateCard")
    public Result updateCard(@RequestBody Card card){
        Result result = new Result();
        result.setStatus(1);
        result.setMessage("更新成功");
        Integer res = cardService.updateCard(card);
        if(res!=1){
            result.setStatus(0);
            result.setMessage("更新失败");
        }
        return result;
    }
}
