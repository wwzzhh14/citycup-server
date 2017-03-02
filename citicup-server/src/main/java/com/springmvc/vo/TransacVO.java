package com.springmvc.vo;

import java.util.Date;

/**
 * Created by guhan on 16/8/26.
 */
public class TransacVO {
    /*期货代码*/
    private String code;
    /*最低保证金比率*/
    private double minMarginRatio;
    /*交易时价格*/
    private double price;
    /*交易手数*/
    private double amount;
    /*交易时间, 格式为"2016-08-27 07:21:59"*/
    private Date time;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    /*组合类型, Futures, FutureGoods*/
    private int type;

    public TransacVO(){}

    public TransacVO(String code, double minMarginRatio, double price, double amount, Date time, int type) {
        this.code = code;
        this.minMarginRatio = minMarginRatio;
        this.price = price;
        this.amount = amount;
        this.time = time;
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public double getMinMarginRatio() {
        return minMarginRatio;
    }

    public void setMinMarginRatio(double minMarginRatio) {
        this.minMarginRatio = minMarginRatio;
    }


}
