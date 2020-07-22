package com.hx.service;

import com.github.pagehelper.PageInfo;
import com.hx.bean.Card;
import com.hx.bean.PageParam;

import java.util.List;

public interface CardService {
    /**
     * 获得所有Ic卡
     */
    PageInfo<Card> getAllCard(PageParam<Card> pageParam);
    /**
     * 添加ic卡
     */
    Integer addCard(Card card);
    /**
     * 根据id删除卡
     */
    Integer deleteCard(String cardId);
    /**
     * 根据id获得卡
     */
    Card getCard(String cardId);
    /**
     * 更新card
     */
    Integer updateCard(Card card);
}
