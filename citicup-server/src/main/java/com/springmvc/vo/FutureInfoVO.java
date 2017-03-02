package com.springmvc.vo;

import java.util.Date;

/**
 * Created by guhan on 16/8/30.
 */
public class FutureInfoVO {
    /*现价*/
    double nowPrice;
    /*最低保证金比率*/
    double minRatio;
    /*交割月份*/
    Date deliveryMonth;

    public FutureInfoVO(double nowPrice, double minRatio, Date deliveryMonth){
        this.nowPrice = nowPrice;
        this.minRatio = minRatio;
        this.deliveryMonth = deliveryMonth;
    }

    public double getNowPrice() {
        return nowPrice;
    }

    public void setNowPrice(double nowPrice) {
        this.nowPrice = nowPrice;
    }

    public double getMinRatio() {
        return minRatio;
    }

    public void setMinRatio(double minRatio) {
        this.minRatio = minRatio;
    }

    public Date getDeliveryMonth() {
        return deliveryMonth;
    }

    public void setDeliveryMonth(Date deliveryMonth) {
        this.deliveryMonth = deliveryMonth;
    }


}
