package com.springmvc.blservice;

import com.springmvc.vo.ResultMessageVO;

import java.util.Date;
import java.util.List;

/**
 * Created by guhan on 16/9/8.
 */
public interface CapitourBLService {

    //托管开始
    public ResultMessageVO startCapitour(String account, String userName, Date start, String end, double money);


    //实时收益率
    public List<Double> viewProfit(String account, String userName, Date start);




    //*************************************** 策略作图 ***************************************************
    public void init();

    //价差图
    public double[] getPairDelta();

    //收益率
    public double[] getPairYield();

    //时间
    public List<String> getPairDate();

}
