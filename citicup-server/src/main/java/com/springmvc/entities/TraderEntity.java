package com.springmvc.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by guhan on 16/8/22.
 */

@Entity
@Table(name = "tb_traders")
public class TraderEntity implements Serializable{
    /*用户名*/
    @Id
    private String userName;
    /*金融账户, 申请时用户输入*/
    @Id
    private String traderAccount;
    /*创建账户的时间*/
    private Date createAt;
    /*转入资金账户的钱*/
    private double diposit;
    /*冻结的金额*/
    private double frozen;
    /*止盈点stop-profit*/
    private double SP;
    /*止损点stop-loss*/
    private double SL;
    /*单笔投资额*/
    private double singleInvestment;


    /*是否允许跨市套利自动平仓*/
    private boolean isCrossMarketAutoClosed;
    /*是否允许期现套利自动平仓*/
    private boolean isFutureGoodsAutoClosed;
    /*是否允许跨期套利自动平仓*/
    private boolean isCrossTermAutoClosed;


    public TraderEntity() {
    }

    //创建账户时只调用一次
    public TraderEntity(String userName, String traderAccount, Date createAt, double diposit) {
        this.userName = userName;
        this.traderAccount = traderAccount;
        this.createAt = createAt;
//        if(createAt==null){this.createAt = createAt;}               //一旦设定不可改变
        this.diposit = diposit;
        //初始化
        this.frozen = 0;
        this.SP = 0;
        this.SL = 0;
        this.singleInvestment = 0;

        this.isCrossMarketAutoClosed = false;
        this.isFutureGoodsAutoClosed = false;
        this.isCrossTermAutoClosed = false;

    }

    public String getUserName(){return  userName;}

    public String getTraderAccount() {
        return traderAccount;
    }

    public void setTraderAccount(String traderAccount) {
        this.traderAccount = traderAccount;
    }

    public Date getCreateAt(){return this.createAt;}

    public double getDiposit(){return this.diposit;}

    public void setDiposit(double money){this.diposit = money;}

    public double getSP() {
        return SP;
    }

    public void setSP(double SP) {
        this.SP = SP;
    }

    public double getSL() {
        return SL;
    }

    public void setSL(double SL) {
        this.SL = SL;
    }

    public double getSingleInvestment() {
        return singleInvestment;
    }

    public void setSingleInvestment(double singleInvestment) {
        this.singleInvestment = singleInvestment;
    }

    public double getFrozen(){return frozen;}

    public void setFrozen(double frozen){this.frozen = frozen;}



    public boolean isCrossMarketAutoClosed() {
        return isCrossMarketAutoClosed;
    }

    public void setCrossMarketAutoClosed(boolean crossMarketAutoClosed) {
        isCrossMarketAutoClosed = crossMarketAutoClosed;
    }

    public boolean isFutureGoodsAutoClosed() {
        return isFutureGoodsAutoClosed;
    }

    public void setFutureGoodsAutoClosed(boolean futureGoodsAutoClosed) {
        isFutureGoodsAutoClosed = futureGoodsAutoClosed;
    }

    public boolean isCrossTermAutoClosed() {
        return isCrossTermAutoClosed;
    }

    public void setCrossTermAutoClosed(boolean crossTermAutoClosed) {
        isCrossTermAutoClosed = crossTermAutoClosed;
    }



}
