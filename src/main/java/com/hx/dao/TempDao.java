package com.hx.dao;

import com.hx.bean.Temp;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface TempDao extends Mapper<Temp>{
    List<Temp> getAllTemp(@Param("keyWorld") String keyWorld);

    Integer calculator(@Param("tempId") String tempId,@Param("money") Integer money);

    Temp getTempById(@Param("tempId") String tempId);

    Integer updateTemp(Temp temp);
}
