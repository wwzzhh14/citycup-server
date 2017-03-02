package com.springmvc.vo;

import java.util.Date;

/**
 * Created by guhan on 16/9/12.
 */
public class TransacRecordVO {
    private Date time;

    private String combiName;           //"AU1612-AU1706","AU-GC","AU-AU(T+D)"
    //多头名称
    private String bullName;
    //空头名称
    private String bearName;
    //多头成交手数
    private double bullAmount;
    //空头成交手数
    private double bearAmount;
    //多头成交价
    private double bullPrice;
    //空头成交价
    private double bearPrice;

    public TransacRecordVO(Date time, String combiName, String bullName, String bearName, double bullAmount, double bearAmount, double bullPrice, double bearPrice) {
        this.time = time;
        this.combiName = combiName;
        this.bullName = bullName;
        this.bearName = bearName;
        this.bullAmount = bullAmount;
        this.bearAmount = bearAmount;
        this.bullPrice = bullPrice;
        this.bearPrice = bearPrice;
    }


    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getCombiName() {
        return combiName;
    }

    public void setCombiName(String combiName) {
        this.combiName = combiName;
    }

    public String getBullName() {
        return bullName;
    }

    public void setBullName(String bullName) {
        this.bullName = bullName;
    }

    public String getBearName() {
        return bearName;
    }

    public void setBearName(String bearName) {
        this.bearName = bearName;
    }

    public double getBullAmount() {
        return bullAmount;
    }

    public void setBullAmount(double bullAmount) {
        this.bullAmount = bullAmount;
    }

    public double getBearAmount() {
        return bearAmount;
    }

    public void setBearAmount(double bearAmount) {
        this.bearAmount = bearAmount;
    }

    public double getBullPrice() {
        return bullPrice;
    }

    public void setBullPrice(double bullPrice) {
        this.bullPrice = bullPrice;
    }

    public double getBearPrice() {
        return bearPrice;
    }

    public void setBearPrice(double bearPrice) {
        this.bearPrice = bearPrice;
    }
}
