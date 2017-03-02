package com.springmvc.entities;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by guhan on 16/9/11.
 */
@Entity
@Table(name = "tb_transacrecords")
public class TransacRecordEntity implements Serializable{
    @Id
    private String account;
    @Id
    private String userName;
    @Id
    private Date time;
    @Id
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
    //结束位, 0表示上一次建仓尚未平仓, 1表示上一次建仓已平
    private int isEnded;

    public TransacRecordEntity(String account, String userName, Date time, String combiName, String bullName, String bearName, double bullAmount, double bearAmount, double bullPrice, double bearPrice, int isEnded){
        this.account = account;
        this.userName =  userName;
        this.time = time;
        this.combiName = combiName;
        this.bullName = bullName;
        this.bearName = bearName;
        this.bullAmount = bullAmount;
        this.bearAmount = bearAmount;
        this.bullPrice = bullPrice;
        this.bearPrice = bearPrice;
        this.isEnded = isEnded;
    }

    public TransacRecordEntity() {
    }

    public  String getAccount() {
        return account;
    }

    public void   setAccount(String account) {
        this.account = account;
    }

    public String getUserName() {
        return userName;
    }

    public void   setUserName(String userName) {
        this.userName = userName;
    }

    public Date   getTime() {
        return time;
    }

    public void   setTime(Date time) {
        this.time = time;
    }

    public String getCombiName() {
        return combiName;
    }

    public void   setCombiName(String combiName) {
        this.combiName = combiName;
    }

    public String getBullName() {
        return bullName;
    }

    public void   setBullName(String bullName) {
        this.bullName = bullName;
    }

    public String getBearName() {
        return bearName;
    }

    public void   setBearName(String bearName) {
        this.bearName = bearName;
    }

    public double getBullAmount() {
        return bullAmount;
    }

    public void   setBullAmount(double bullAmount) {
        this.bullAmount = bullAmount;
    }

    public double getBearAmount() {
        return bearAmount;
    }

    public void   setBearAmount(double bearAmount) {
        this.bearAmount = bearAmount;
    }

    public double getBullPrice() {
        return bullPrice;
    }

    public void   setBullPrice(double bullPrice) {
        this.bullPrice = bullPrice;
    }

    public double getBearPrice() {
        return bearPrice;
    }

    public void   setBearPrice(double bearPrice) {
        this.bearPrice = bearPrice;
    }

    public int getIsEnded(){return isEnded;}

    public void setIsEnded(int isEnded){this.isEnded = isEnded;}
}
