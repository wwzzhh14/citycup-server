package com.springmvc.vo;

/**
 * Created by guhan on 16/9/11.
 */
public class HoldInfoVO {
    //多头名
    private String bullName;
    //多头手数
    private double bullAmount;
    //空头名
    private String bearName;
    //空头手数
    private double bearAmount;

    public HoldInfoVO(String bullName, double bullAmount, String bearName, double bearAmount){
        this.bullName = bullName;
        this.bullAmount = bullAmount;
        this.bearName = bearName;
        this.bearAmount = bearAmount;
    }

    public String getBullName() {
        return bullName;
    }

    public void setBullName(String bullName) {
        this.bullName = bullName;
    }

    public double getBullAmount() {
        return bullAmount;
    }

    public void setBullAmount(double bullAmount) {
        this.bullAmount = bullAmount;
    }

    public String getBearName() {
        return bearName;
    }

    public void setBearName(String bearName) {
        this.bearName = bearName;
    }

    public double getBearAmount() {
        return bearAmount;
    }

    public void setBearAmount(double bearAmount) {
        this.bearAmount = bearAmount;
    }
}
