package com.springmvc.config;

import com.springmvc.utils.TimeUtil;
import com.springmvc.vo.StaticFutureInfo;

import java.util.*;

/**
 * Created by wzh on 16/8/13.
 */


public class FutureConstant {

    public static final int MAX_DATA_SIZE = 180;

    public static List<Double> AutdMinDataList = new ArrayList<Double>();

    private static final String[] futureCodes={"AU1612","AU1706",
            "hf_GC","hf_AUTD"};
//            "SGE_AU99_99","SGE_AU995","SGE_AGTD","SGE_AG9999","SGE_AUTD",每标准手保证金为1000美元
//            "SGE_AUTN1","SGE_AUTN2","SGE_AU100","SGE_AU50G","SGE_AU9995","SGE_AU9999","SGE_IAU100G","SGE_IAU99_5","SGE_IAU99_99",
//            "SGE_MAUTD","SGE_PT9995"};
//    private static final String[] futureSuffix={"0","1608","1609","1610","1611","1612",
//    "1701","1702","1703","1704","1705","1706","1707"};
    private static Hashtable<String,StaticFutureInfo> futureInfoHashtable = new Hashtable<String, StaticFutureInfo>();
    private static Hashtable<String,String> futureCodeTable = new Hashtable<String, String>();

    public static Hashtable<String, String> getFutureCodeTable() {
        futureCodeTable.put("AU1612","AU1612");
        futureCodeTable.put("AU1706","AU1706");
        futureCodeTable.put("GC","hf_GC");
        futureCodeTable.put("AUTD","hf_AUTD");
        return futureCodeTable;
    }

    public static Hashtable<String,StaticFutureInfo> getFutureInfoHashtable(){

        Date date = new Date();
//        futureInfoHashtable.put("AU0",new StaticFutureInfo("沪金","AU0","最近三个连续月份的合约以及最近11个月以内的双月合约","上海期货交易所",0.04));
//        futureInfoHashtable.put("AU1608",new StaticFutureInfo("沪金","AU1608","最近三个连续月份的合约以及最近11个月以内的双月合约","上海期货交易所","合约价值的4%"));
//        futureInfoHashtable.put("AU1609",new StaticFutureInfo("沪金","AU1609","最近三个连续月份的合约以及最近11个月以内的双月合约","上海期货交易所","合约价值的4%"));
//        futureInfoHashtable.put("AU1610",new StaticFutureInfo("沪金","AU1610","最近三个连续月份的合约以及最近11个月以内的双月合约","上海期货交易所","合约价值的4%"));
//        futureInfoHashtable.put("AU1611",new StaticFutureInfo("沪金","AU1611","最近三个连续月份的合约以及最近11个月以内的双月合约","上海期货交易所","合约价值的4%"));
        futureInfoHashtable.put("AU1612",new StaticFutureInfo("沪金","AU1612", TimeUtil.getDate("2016-12-22"),"上海期货交易所",0.04));
//        futureInfoHashtable.put("AU1701",new StaticFutureInfo("沪金","AU1701","最近三个连续月份的合约以及最近11个月以内的双月合约","上海期货交易所","合约价值的4%"));
//        futureInfoHashtable.put("AU1702",new StaticFutureInfo("沪金","AU1702","最近三个连续月份的合约以及最近11个月以内的双月合约","上海期货交易所","合约价值的4%"));
//        futureInfoHashtable.put("AU1703",new StaticFutureInfo("沪金","AU1703","最近三个连续月份的合约以及最近11个月以内的双月合约","上海期货交易所","合约价值的4%"));
//        futureInfoHashtable.put("AU1704",new StaticFutureInfo("沪金","AU1704","最近三个连续月份的合约以及最近11个月以内的双月合约","上海期货交易所","合约价值的4%"));
//        futureInfoHashtable.put("AU1705",new StaticFutureInfo("沪金","AU1705","最近三个连续月份的合约以及最近11个月以内的双月合约","上海期货交易所","合约价值的4%"));
        futureInfoHashtable.put("AU1706",new StaticFutureInfo("沪金","AU1706",TimeUtil.getDate("2017-06-22"),"上海期货交易所",0.04));
//        futureInfoHashtable.put("AU1707",new StaticFutureInfo("沪金","AU1707","最近三个连续月份的合约以及最近11个月以内的双月合约","上海期货交易所","合约价值的4%"));

        futureInfoHashtable.put("hf_GC",new StaticFutureInfo("comex黄金","GC",TimeUtil.getDate("2016-12-22"),"纽约商品交易所",5400));
        futureInfoHashtable.put("hf_AUTD",new StaticFutureInfo("AU T+D","AUTD",null,"上海黄金交易所",0.7));
//        futureInfoHashtable.put("hf_XAU",new StaticFutureInfo("伦敦金","XAU","无","银行间柜台撮合交易",1000));


        return futureInfoHashtable;
    }
    public static String[] getFutureCodes(){
        return futureCodes;
    }

//    public static String[] getFutureSuffix() {
//        return futureSuffix;
//    }

    public static double ALARM_STANDARD = 0.9;
}

