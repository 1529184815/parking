package com.hx.service;


import com.github.pagehelper.PageInfo;
import com.hx.bean.PageParam;
import com.hx.bean.Seat;

import java.util.List;

public interface SeatService {
    /**
     * 获得空位置
     * @return
     */
    List<Seat> getEnableSeat();
    /**
     * 根据删除位置
     */
    int deleteSeat(String seatId);
    /**
     * 添加位置
     * @param seat
     * @return
     */
    int addSeat(Seat seat);

    /**
     * 根据id获得位置
     * @param seatId
     * @return
     */
    Seat getSeat(String seatId);
    /**
     * 更新位置
     */
    int updateSeat(Seat seat);
    /**
     * 根据座位号获得座位
     */
    Seat getSeatBySeatNum(String seatNum);

    List<Seat> getAllSeatList();

    /**
     * 查询所有分页
     * @return
     */
    PageInfo<Seat> getSeatPage(PageParam pageParam);
}
