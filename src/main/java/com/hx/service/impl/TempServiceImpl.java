package com.hx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hx.bean.Card;
import com.hx.bean.PageParam;
import com.hx.bean.Temp;
import com.hx.dao.TempDao;
import com.hx.service.TempService;
import com.hx.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;


@Service("tempService")
public class TempServiceImpl implements TempService {
    @Autowired
    private TempDao tempDao;

    @Override
    public PageInfo<Temp> getAllTemp(PageParam<Temp> pageParam) {
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        List<Temp> allTemp = tempDao.getAllTemp(pageParam.getKeyWorld());
        PageInfo<Temp> pageInfo = new PageInfo(allTemp);
        return pageInfo;
    }

    @Override
    @Transactional
    public Integer addTemp(Temp temp) {
        Date date = new Date();
        String str = DateUtil.timeStrap2String(date, "yyyyMMddHHmmss");
        if (str != null) {
            temp.setTempId(str);
            temp.setCardId(str);
        }
        if (date != null) {
            temp.setEntryTime(date);
        }
        temp.setState(0);
        return tempDao.insert(temp);
    }

    @Override
    public Integer deletTemp(String id) {
        return tempDao.deleteByPrimaryKey(id);
    }

    @Override
    public Temp getTemp(String id) {
        return tempDao.selectByPrimaryKey(id);
    }

    @Override
    public Integer updateTemp(Temp temp) {
        return tempDao.updateByPrimaryKey(temp);
    }

    @Override
    @Transactional
    public Integer calculator(Temp temp) {
        Temp temp1 = tempDao.getTempById(temp.getTempId());
        //更新离场时间
        temp1.setOutTime(new Date());
        tempDao.updateByPrimaryKey(temp1);
        //计算价格
        Integer res = tempDao.calculator(temp.getTempId(), temp.getMoney());
        if(res!=null && res>=0){
            temp1.setTempMoney(res);
        }
        temp1.setState(1);
        return tempDao.updateByPrimaryKey(temp1);
    }
}
