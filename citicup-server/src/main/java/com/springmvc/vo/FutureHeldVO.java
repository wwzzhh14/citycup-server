package com.springmvc.vo;

/**
 * Created by guhan on 16/9/4.
 */
public class FutureHeldVO {

    private String code;

    /*这支期货的总手数*/
    private double amount;


    public FutureHeldVO(){}

    public FutureHeldVO(String code, double amount){
        this.code = code;
        this.amount = amount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }



}
