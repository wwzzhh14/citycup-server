package com.springmvc.entities;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by guhan on 16/9/4.
 */

@Entity
@Table(name = "tb_preferences")
public class PreferenceEntity implements Serializable {
    /*用户名*/
    @Id
    private String userName;
    /*金融账户, 申请时用户输入*/
    @Id
    private String traderAccount;
    /*是否设置不再在每笔交易前提醒止盈止损点的设置*/
    private boolean isRemindedSettingSpsl;
    //还有一大波布尔值。。。。


    public PreferenceEntity() {
    }

    public PreferenceEntity(String userName, String traderAccount, boolean isRemindedSettingSpsl) {
        this.userName = userName;
        this.traderAccount = traderAccount;
        this.isRemindedSettingSpsl = isRemindedSettingSpsl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTraderAccount() {
        return traderAccount;
    }

    public void setTraderAccount(String traderAccount) {
        this.traderAccount = traderAccount;
    }

    public boolean isRemindedSettingSpsl() {
        return isRemindedSettingSpsl;
    }

    public void setRemindedSettingSpsl(boolean remindedSettingSpsl) {
        isRemindedSettingSpsl = remindedSettingSpsl;
    }
}
