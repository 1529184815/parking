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
import com.hx.service.CardService;
import com.hx.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("cardService")
public class CardServiceImpl implements CardService {
    @Autowired
    private CardDao cardDao;
    @Autowired
    private SeatService seatService;
    @Autowired
    private SeatDao seatDao;
    @Autowired
    private FixedDao fixedDao;
    @Override
    public PageInfo<Card> getAllCard(PageParam<Card> pageParam) {
        Example example = new Example(Card.class);
        Example.Criteria criteria = example.createCriteria();
        String keyWorld = pageParam.getKeyWorld();
        if(keyWorld != null && keyWorld != ""){
            criteria.andLike("userName","%"+keyWorld+"%");
        }
        PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
        List<Card> cards = cardDao.selectByExample(example);

        PageInfo<Card> pageInfo = new PageInfo(cards);
        return pageInfo;
    }

    @Override
    @Transactional
    public Integer addCard(Card card) {
        //先创建IC卡 一张卡一个固定车主
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        card.setCardId(dateFormat.format(new Date()));
        String seatId = card.getSeatId();
        Seat seat = seatDao.selectByPrimaryKey(seatId);
        seat.setSeatState(1);
        seatDao.updateByPrimaryKey(seat);
        //增加固定车主
        Fixed fixed = new Fixed();
        fixed.setFixedId(dateFormat.format(new Date()));
        fixed.setCardId(card.getCardId());
        fixed.setStartTime(new Date());
        fixedDao.insert(fixed);
        return cardDao.insert(card);
    }

    @Override
    @Transactional
    public Integer deleteCard(String cardId) {
        Card card = cardDao.selectByPrimaryKey(cardId);
        String seatId = card.getSeatId();
        Seat seat = seatDao.selectByPrimaryKey(seatId);
        seat.setSeatState(0);
        seatDao.updateByPrimaryKey(seat);
        //删除固定车主
        Example example = new Example(Fixed.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("cardId",cardId);
        fixedDao.deleteByExample(example);
        return cardDao.deleteByPrimaryKey(cardId);
    }

    @Override
    public Card getCard(String cardId) {
        return cardDao.selectByPrimaryKey(cardId);
    }

    @Override
    @Transactional
    public Integer updateCard(Card card) {
        String cardId = card.getCardId();//获得当前IC卡id
        String seatId = card.getSeatId();//获得新座位Id
        Seat seat = seatDao.selectByPrimaryKey(seatId);//获得新座位
        Card card1 = cardDao.selectByPrimaryKey(cardId);//获得当前IC卡
        if(seat.getSeatState()!=1){
            String seatId1 = card1.getSeatId();//获得旧位置
            Seat seat1 = seatDao.selectByPrimaryKey(seatId1);//获得旧位置
            seat1.setSeatState(0);//更改位置为空闲
            seatDao.updateByPrimaryKey(seat1);//更新旧位置
            seat.setSeatState(1);
            seatDao.updateByPrimaryKey(seat);//更新新座位被占用
            card1.setSeatId(seatId);//设置新的座位id
        }
        return cardDao.updateByPrimaryKey(card);
    }
}
