package com.hx.service;

import com.github.pagehelper.PageInfo;
import com.hx.bean.Fixed;
import com.hx.bean.PageParam;

import java.util.List;

public interface FixedService {
    /**
     * 获得所有出入场信息
     */
    PageInfo<Fixed> getAllFixed(PageParam<Fixed> pageParam);

    Integer deleteFixed(String fixedId);

    Fixed getFixed(String fixedId);

    Integer payMoney(String fixedId, Integer money);
}
