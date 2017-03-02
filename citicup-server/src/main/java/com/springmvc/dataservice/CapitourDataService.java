package com.springmvc.dataservice;

import com.springmvc.config.Msg;
import com.springmvc.entities.CapitourEntity;

import java.util.Date;
import java.util.List;

/**
 * Created by guhan on 16/9/15.
 */
public interface CapitourDataService {

    public Msg saveDailyRatio(String date, double yieldRatio);

    public double getDailyRatio(String date);

    //添加这个entity
    public Msg addCapitour(CapitourEntity entity);

    public CapitourEntity getCapitour(String account, String userName, Date start);

    //获取所有托管的信息列表
    public List<CapitourEntity> getCapitourList(String account, String userName);

    //选出撤资时间匹配的entity
    public List<CapitourEntity> selectCapitour( String end);


    public Msg updateMoney(double investment);

    //返回虚拟账户总共金额
    public double getMoney();

}
