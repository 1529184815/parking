package com.hx.bean;

/**
 * @Author huxiao
 * @Date 2020/7/9 0009 16:12
 */
public class Result<T> {
    //返回消息状态码 0：异常，1：成功
    private Integer status;
    //返回消息提示信息
    private String message;
    //返回数据内容
    private T info;

    public Result(Integer status) {
        this.status = status;
    }

    public Result() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}