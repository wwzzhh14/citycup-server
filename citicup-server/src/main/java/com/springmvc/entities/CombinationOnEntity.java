package com.springmvc.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by guhan on 16/9/12.
 */

//存储尚未平仓的历史交易记录

    @Entity
    @Table(name = "tb_combinations")
public class CombinationOnEntity implements Serializable{
    @Id
    private String account;
    @Id
    private String userName;
    @Id
    private int type;
    @Id
    private Date time;              //交易时间, 用来排序


    private String bullName;        //多头名
    private String bearName;        //空头名
    private double bullAmount;
    private double bearAmount;
    private double bullPrice;             //买入价
    private double bearPrice;

    public CombinationOnEntity(){}

    public CombinationOnEntity(String account, String userName, int type, Date time, String bullName, String bearName, double bullAmount, double bearAmount, double bullPrice, double bearPrice) {
        this.account = account;
        this.userName = userName;
        this.type = type;
        this.time = time;
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
