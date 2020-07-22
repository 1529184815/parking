package com.hx.dao;

import com.hx.bean.Card;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface CardDao extends Mapper<Card> {
    @Select("select * from card where card_id=#{cardId}")
    @Results(id = "cardMapping",value = {
            @Result(id = true,column = "card_id",property = "cardId"),
            @Result(column = "seat_id",property = "seatId"),
            @Result(column = "user_name",property = "userName"),
            @Result(column = "user_gender",property = "userGender"),
            @Result(column = "user_addr",property = "userAddr"),
            @Result(column = "car_num",property = "carNum")
    })
    Card getCardById(@Param("cardId") String cardId);
}
