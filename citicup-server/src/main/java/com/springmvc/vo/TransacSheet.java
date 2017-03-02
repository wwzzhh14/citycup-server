package com.springmvc.vo;

/**
 * Created by guhan on 16/9/11.
 */
public class TransacSheet {
    private String bullName;        //多头
    private String bearName;        //空头
    private double bullAmount;
    private double bearAmount;
    private double bullPrice;
    private double bearPrice;
    private double cost;            //手续费
    private double margin;          //保证金

    public TransacSheet(){

    }

    public TransacSheet(String bullName, String bearName, double bullAmount, double bearAmount, double bullPrice, double bearPrice, double cost, double margin) {
        this.bullName = bullName;
        this.bearName = bearName;
        this.bullAmount = bullAmount;
        this.bearAmount = bearAmount;
        this.bullPrice = bullPrice;
        this.bearPrice = bearPrice;
        this.cost = cost;
        this.margin = margin;
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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getMargin() {
        return margin;
    }

    public void setMargin(double margin) {
        this.margin = margin;
    }
}
