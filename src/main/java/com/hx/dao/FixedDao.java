package com.hx.dao;

import com.hx.bean.Fixed;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface FixedDao extends Mapper<Fixed> {
    List<Fixed> getAllFixed(String keyWorld);

    Fixed getFixedById(String fixedId);

    Integer payMoney(@Param("fixedId") String fixedId,@Param("money") Integer money);
}
