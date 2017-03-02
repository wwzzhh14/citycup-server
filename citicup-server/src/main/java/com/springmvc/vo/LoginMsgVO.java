package com.springmvc.vo;

import com.springmvc.config.Msg;

/**
 * Created by wzh on 16/8/10.
 */
public class LoginMsgVO {

    private Msg stutus;
    private  String name;

    public LoginMsgVO(Msg stutus, String name) {
        this.stutus = stutus;
        this.name = name;
    }

    public LoginMsgVO() {

    }

    public Msg getStutus() {
        return stutus;
    }

    public void setStutus(Msg stutus) {
        this.stutus = stutus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
