package com.springmvc.vo;

import com.springmvc.config.Msg;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guhan on 16/9/6.
 */
public class FutureComputedVO {

    //Msg
    private Msg status;
    //error msg
    private String error;
    //目前均值
    private double mean;
    //当日最高
    private double high;
    //当日最低
    private double low;
    //当日分钟数据,拿去画频率分布图
    private List<Double> minHistory;

    //只有报错时调用此constructor
    public FutureComputedVO(Msg status, String error){
        this.status = status;
        this.error = error;
    }

    public FutureComputedVO(Msg status, String error, double mean, double high, double low, List<Double> list){
        this.status = status;
        this.error = error;
        this.mean = mean;
        this.high = high;
        this.low = low;
        this.minHistory = list;
    }

    public Msg getStatus() {
        return status;
    }

    public void setStatus(Msg status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public  double getMean() {
        return mean;
    }public void   setMean(double mean) {
        this.mean = mean;
    }public double getHigh() {
        return high;
    }public void   setHigh(double high) {
        this.high = high;
    }public double getLow() {
        return low;
    }public void   setLow(double low) {
        this.low = low;
    }

    public void setMinHistory(List<Double> minHistory){
        this.minHistory = minHistory;
    }

    public List<Double> getMinHistory(){return this.minHistory;}
}
