package com.hx.service;

import com.github.pagehelper.PageInfo;
import com.hx.bean.PageParam;
import com.hx.bean.Temp;

import java.util.List;

public interface TempService {
    /**
     * 查询所有零时车辆信息
     * @return
     */
    PageInfo<Temp> getAllTemp(PageParam<Temp> pageParam);
    /**
     * 添加临时车辆信息
     */
    Integer addTemp(Temp temp);
    /**
     * 删除信息
     */
    Integer deletTemp(String id);

    Temp getTemp(String id);

    Integer updateTemp(Temp temp);

    Integer calculator(Temp temp);
}
