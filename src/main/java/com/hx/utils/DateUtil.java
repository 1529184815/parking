package com.hx.utils;

import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author huxiao
 * @Date 2020/7/16 0016 12:27
 */
public class DateUtil {
    private Date date = null;
    private String parttern = null;
    public static String timeStrap2String(Date date,String parttern){
        SimpleDateFormat dtf = new SimpleDateFormat(parttern);
        return dtf.format(date);
    }
}
