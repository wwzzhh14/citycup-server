package com.springmvc.vo;

import java.util.Date;

/**
 * Created by wzh on 16/8/13.
 */
public class StaticFutureInfo {
    private String name;
    private String code;
    //交割月份
    private Date deliveryMonth;
    //上市交易所
    private String place;
    //最低保证金比率
    private double minMarginRatio;

    public StaticFutureInfo() {
    }

    public StaticFutureInfo(String name, String code, Date deliveryMonth, String place, double minMarginRatio) {
        this.name = name;
        this.code = code;
        this.deliveryMonth = deliveryMonth;
        this.place = place;
        this.minMarginRatio = minMarginRatio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDeliveryMonth() {
        return deliveryMonth;
    }

    public void setDeliveryMonth(Date deliveryMonth) {
        this.deliveryMonth = deliveryMonth;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public double getMinMarginRatio() {
        return minMarginRatio;
    }

    public void setMinMarginRatio(double minMarginRatio) {
        this.minMarginRatio = minMarginRatio;
    }
}
