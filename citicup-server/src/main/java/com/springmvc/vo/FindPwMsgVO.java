package com.springmvc.vo;

import com.springmvc.config.Msg;

/**
 * Created by guhan on 16/8/21.
 */
public class FindPwMsgVO {
    private Msg status;
    private String phoneNumber;
    private String password;

    //for failure situation of finding back passwords
    public FindPwMsgVO() {
        this.status = Msg.FAIL;
        this.phoneNumber = "12345678";
        this.password = "";
    }

    //when successfully verified, return passwords
    public FindPwMsgVO(Msg msg, String pw){
        this.status = msg;
        this.password = pw;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Msg getStatus() {
        return status;
    }

    public void setStatus(Msg status) {
        this.status = status;
    }




}
