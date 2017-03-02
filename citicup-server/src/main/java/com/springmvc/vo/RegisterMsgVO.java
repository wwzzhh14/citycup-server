package com.springmvc.vo;

import com.springmvc.config.Msg;

/**
 * Created by wzh on 16/8/10.
 */
public class RegisterMsgVO {

    private Msg stutus;
    private  String reason;

    public RegisterMsgVO() {
    }

    public RegisterMsgVO(Msg stutus, String reason) {
        this.stutus = stutus;
        this.reason = reason;
    }

    public Msg getStutus() {
        return stutus;
    }

    public void setStutus(Msg stutus) {
        this.stutus = stutus;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
