package com.springmvc.thread;

import com.springmvc.blservice.ChartInfoBLService;
import com.springmvc.config.FutureConstant;
import com.springmvc.config.UrlConstant;
import com.springmvc.dao.FutureInfoDao;
import com.springmvc.dataservice.CapitourDataService;
import com.springmvc.dataservice.FutureInfoDataService;
import com.springmvc.dataservice.TransacDataService;
import com.springmvc.dataservice.UserDataService;
import com.springmvc.entities.AutdInfoEntity;
import com.springmvc.entities.CapitourEntity;
import com.springmvc.entities.TraderEntity;
import com.springmvc.utils.NetUtil;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by guhan on 16/9/8.
 */

@Service
public class DataGrabber extends Thread {

    @Resource
    ChartInfoBLService service;
    @Resource
    TransacDataService transacDataService;
    @Resource
    UserDataService userDataService;
    @Resource
    CapitourDataService capitourDataService;

    public void run() {
        long interval = 0;
        long start = Calendar.getInstance().getTimeInMillis();
        while (!this.isInterrupted()) {// 线程未中断执行循环
            interval += Calendar.getInstance().getTimeInMillis() - start;
            start = Calendar.getInstance().getTimeInMillis();
            //时间超过24小时,check所有withdraw的用户
            if(interval >= 1000*60*60*24){
                Date date = new Date();
                Calendar cal = new GregorianCalendar();
                cal.setTime(date);
                cal.add(cal.DATE, -1);          //昨天的时间
                date = cal.getTime();
                String end = date.getYear() + "-" + date.getMonth() + "-" + date.getDay();
                List<CapitourEntity> capitourEntities = capitourDataService.selectCapitour(end);
                if(capitourEntities.size()>0) {
                    for (CapitourEntity entity : capitourEntities) {
                        TraderEntity trader = userDataService.getAccount(entity.getAccount(),entity.getUserName());
                        double deposit = trader.getDiposit();
                        double profit = entity.getMoney() * capitourDataService.getDailyRatio(end);
                        trader.setDiposit(deposit + profit);
                        userDataService.updateAccount(trader);
                        double money = capitourDataService.getMoney();
                        capitourDataService.updateMoney(money-profit);
                    }
                }
            }
            try {
                Thread.sleep(60000); //每隔2000ms执行一次
                System.out.println(service.getMInLineData("AU1612"));

                String s=NetUtil.httpGet(UrlConstant.NOWTIME_URL +"hf_AUTD");
                String[] arr=s.split(",");
                double nowPrice = Double.parseDouble(arr[0].substring(arr[0].indexOf("\"")+1));
                if (FutureConstant.AutdMinDataList.size()==FutureConstant.MAX_DATA_SIZE){
                    FutureConstant.AutdMinDataList.remove(FutureConstant.AutdMinDataList.size()-1);
                }
                FutureConstant.AutdMinDataList.add(nowPrice);
                System.out.println(nowPrice);
                System.out.println(FutureConstant.AutdMinDataList.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

//           ------------------ 开始执行 ---------------------------
//            System.out.println("____DataGrabber____:" + System.currentTimeMillis());
        }
    }


}
