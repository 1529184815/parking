package com.hx.bean;

import lombok.Data;

/**
 * @Author huxiao
 * @Date 2020/7/9 0009 16:06
 */
@Data
public class PageParam<T> {
    private Integer pageNum=1;
    private Integer pageSize=10;
    private Integer total;
    private T params;
    private String keyWorld;
}
