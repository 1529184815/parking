package com.hx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hx.bean.Card;
import com.hx.bean.PageParam;
import com.hx.bean.Seat;
import com.hx.dao.SeatDao;
import com.hx.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service("seatService")
public class SeatServiceImpl implements SeatService {
    @Autowired
    private SeatDao seatDao;

    @Override
    public List<Seat> getEnableSeat() {
        List<Seat> seats = seatDao.selectAll();
        Iterator<Seat> iterator = seats.iterator();
        while(iterator.hasNext()){
            Seat seat = iterator.next();
            if(seat.getSeatState() !=null && seat.getSeatState()==1){
                iterator.remove();
            }
        }
        return seats;
    }

    public int deleteSeat(String seatId){
        return seatDao.deleteByPrimaryKey(seatId);
    }

    @Override
    public int addSeat(Seat seat) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        seat.setSeatId(dateFormat.format(new Date()));
        seat.setSeatState(0);
        Seat checkSeat = seatDao.getSeatBySeatName(seat.getSeatNum());
        if(checkSeat==null){
            return seatDao.insert(seat);
        }
        return -1;
    }

    @Override
    public Seat getSeat(String seatId) {
        return seatDao.selectByPrimaryKey(seatId);
    }

    @Override
    public int updateSeat(Seat seat) {
        return seatDao.updateByPrimaryKey(seat);
    }

    @Override
    public Seat getSeatBySeatNum(String seatNum) {
        return seatDao.getSeatBySeatName(seatNum);
    }

    @Override
    public List<Seat> getAllSeatList() {
        return seatDao.selectAll();
    }

    @Override
    public PageInfo<Seat> getSeatPage(PageParam pageParam) {
        Example example = new Example(Seat.class);
        Example.Criteria criteria = example.createCriteria();
        String keyWorld = pageParam.getKeyWorld();
        if(keyWorld != null && keyWorld != ""){
            criteria.andLike("seatNum","%"+keyWorld+"%");
        }
        PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
        PageInfo pageInfo = new PageInfo(seatDao.selectByExample(example));
        return pageInfo;
    }
}
