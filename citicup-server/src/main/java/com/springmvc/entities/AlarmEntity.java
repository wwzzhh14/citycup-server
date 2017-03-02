package com.springmvc.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by guhan on 16/9/13.
 */
@Entity
@Table(name = "tb_alarms")
public class AlarmEntity implements Serializable{
    @Id
    private String account;
    @Id
    private String userName;
    @Id
    private Date time;
    @Id
    private String combinationName;

    private String msg;


    //标记是否已读, 0未读,1已读
    private int isRead;

    public AlarmEntity(String account, String userName, Date time, String combinationName, String msg, int isRead) {
        this.account = account;
        this.userName = userName;
        this.time = time;
        this.combinationName = combinationName;
        this.msg = msg;
        this.isRead = isRead;
    }


    public AlarmEntity() {
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getCombinationName() {
        return combinationName;
    }

    public void setCombinationName(String combinationName) {
        this.combinationName = combinationName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }
}
