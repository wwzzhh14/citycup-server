package com.springmvc.vo;

/**
 * Created by wzh on 16/8/13.
 */
public class DynamicFutureInfo {
    //当前价格
    private double nowPrice;
    //昨收
    private double yesClose;
    //今开
    private double todOpen;
    //最高
    private double max;
    //最低
    private double min;
    //涨跌幅
    private double rate;

    public DynamicFutureInfo() {
    }

    public DynamicFutureInfo(double nowPrice, double yesClose, double todOpen, double max, double min, double rate) {
        this.nowPrice = nowPrice;
        this.yesClose = yesClose;
        this.todOpen = todOpen;
        this.max = max;
        this.min = min;
        this.rate = rate;
    }

    public double getNowPrice() {
        return nowPrice;
    }

    public void setNowPrice(double nowPrice) {
        this.nowPrice = nowPrice;
    }

    public double getYesClose() {
        return yesClose;
    }

    public void setYesClose(double yesClose) {
        this.yesClose = yesClose;
    }

    public double getTodOpen() {
        return todOpen;
    }

    public void setTodOpen(double todOpen) {
        this.todOpen = todOpen;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
