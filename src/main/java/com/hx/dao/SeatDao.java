package com.hx.dao;

import com.hx.bean.Seat;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface SeatDao extends Mapper<Seat> {
    @Select("select * from seat where seat_num=#{seatNum}")
    @Results(id = "seatMapper",value = {
            @Result(column = "seat_id",property = "seatId"),
            @Result(column = "seat_num",property = "seatNum"),
            @Result(column = "seat_section",property = "seatSection"),
            @Result(column = "seat_state",property = "seatState"),
            @Result(column = "seat_tag",property = "seatTag"),
    })
    Seat getSeatBySeatName(@Param("seatNum") String seatNum);
}
