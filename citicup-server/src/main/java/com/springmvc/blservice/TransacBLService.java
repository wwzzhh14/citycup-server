package com.springmvc.blservice;

import com.springmvc.config.CombiType;
import com.springmvc.config.Msg;
import com.springmvc.vo.*;

import java.util.List;

/**
 * Created by guhan on 16/8/27.
 */
public interface TransacBLService {

    //先实例化属性
    public Msg init();

    //
    public String getCombiName(CombiType type);

    //+计算平仓收益
    public double computeProfit(double amountA[], double amountB[], double priceA[], double priceB[]);

    //+平仓, 结束上一仓
    public TransacMsgVO closeOut(String account, String userName, CombiType combinationType);

    //+下单, 调整本仓手数; 有可能反向建仓导致平仓
    public TransacMsgVO placeOrder(String account, String userName, CombiType combinationType, double bullAmount, double bullPrice, double bearAmount, double bearPrice);

    //+建仓
    public TransacMsgVO setUp(String account, String userName, CombiType combinationType, String bullName, String bearName, double bullAmount, double bullPrice, double bearAmount, double bearPrice);

    //+获取套利组合持仓信息, type为"CROSS_MARKET", "FUTURE_GOODS", "CROSS_TERM"
    public HoldInfoVO getHold(String account, String userName, CombiType combinationType);

    //+获取交易记录
    public List<TransacRecordVO> getTransacRecordByAccount(String account, String userName);


}
