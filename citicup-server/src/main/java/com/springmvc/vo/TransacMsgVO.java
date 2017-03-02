package com.springmvc.vo;

import com.springmvc.config.Msg;

/**
 * Created by guhan on 16/9/12.
 */
public class TransacMsgVO {
    private Msg status;
    private String reason;
    private TransacSheet sheet;

    public TransacMsgVO(Msg status, String reason, TransacSheet sheet) {
        this.status = status;
        this.reason = reason;
        this.sheet = sheet;
    }


    public Msg getStatus() {
        return status;
    }

    public void setStatus(Msg status) {
        this.status = status;
    }

    public String getReason(){return reason;}

    public void setReason(String reason){this.reason = reason;}

    public TransacSheet getSheet() {
        return sheet;
    }

    public void setSheet(TransacSheet sheet) {
        this.sheet = sheet;
    }
}
