package com.hx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hx.bean.Card;
import com.hx.bean.Fixed;
import com.hx.bean.PageParam;
import com.hx.bean.Seat;
import com.hx.dao.CardDao;
import com.hx.dao.FixedDao;
import com.hx.dao.SeatDao;
import com.hx.service.FixedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("fixedService")
public class FixedServiceImpl implements FixedService {
    @Autowired
    private FixedDao fixedDao;
    @Autowired
    private CardDao cardDao;
    @Autowired
    private SeatDao seatDao;
    @Override
    public PageInfo<Fixed> getAllFixed(PageParam<Fixed> pageParam) {
        PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
        List<Fixed> allFixed = fixedDao.getAllFixed(pageParam.getKeyWorld());
        PageInfo<Fixed> pageInfo = new PageInfo(allFixed);
        return pageInfo;
    }

    @Override
    @Transactional
    public Integer deleteFixed(String fixedId){
        //先删除IC卡
        Fixed fixed = fixedDao.getFixedById(fixedId);
        //设置位置为空闲
        Card card = cardDao.selectByPrimaryKey(fixed.getCardId());
        String seatId = card.getSeatId();
        Seat seat = seatDao.selectByPrimaryKey(seatId);
        seat.setSeatState(0);
        seatDao.updateByPrimaryKey(seat);
        cardDao.deleteByPrimaryKey(fixed.getCardId());
        //再删除车主
        return fixedDao.deleteByPrimaryKey(fixedId);
    }
    @Override
    public Fixed getFixed(String fixedId){
        List<Fixed> allFixed = fixedDao.getAllFixed(null);
        for(Fixed fixed:allFixed){
            if(fixed.getFixedId().equals(fixedId)){
                return fixed;
            }
        }
        return null;
    }

    @Override
    public Integer payMoney(String fixedId, Integer money){
       return fixedDao.payMoney(fixedId,money);
    }
}
