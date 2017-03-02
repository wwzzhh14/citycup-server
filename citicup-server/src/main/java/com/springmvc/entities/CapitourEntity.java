package com.springmvc.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by guhan on 16/9/14.
 */

@Entity
@Table(name = "tb_capitours")
public class CapitourEntity implements Serializable{
    @Id
    private String account;
    @Id
    private String userName;
    @Id
    private Date start;
    private String end; //2016-9-14
    private double money;

    public CapitourEntity(String account, String userName, Date start, String end, double money) {
        this.account = account;
        this.userName = userName;
        this.start = start;
        this.end = end;
        this.money = money;
    }

    public CapitourEntity() {
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

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
