package com.springmvc.vo;

import java.util.Date;

/**
 * Created by guhan on 16/9/13.
 */
public class AlarmVO {

    private String account;

    private Date time;

    private String combinationName;

    private String msg;



    //标记是否已读, 0未读,1已读
    private int isRead;

    public AlarmVO(String account, Date time, String combinationName, String msg, int isRead) {
        this.account = account;
        this.time = time;
        this.combinationName = combinationName;
        this.msg = msg;
        this.isRead = isRead;
    }


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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
