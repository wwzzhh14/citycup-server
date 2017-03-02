package com.springmvc.config;

import java.util.Date;

/**
 * Created by wzh on 16/9/6.
 */
public class TypeDefine {

    public static int getCombiTypeToInt(CombiType type){
        if(type==CombiType.CROSS_MARKET){
            return 0;
        }else if(type==CombiType.FUTURE_GOODS){
            return 1;
        }else{
            return 2;
        }
    }

    public static String getCombiTypeToString(CombiType type){
        String combinationName = "";
        Date date = new Date();
        if(type.equals(CombiType.CROSS_TERM))
            return "AU1612-AU1706";
        if((date.getYear()==2016 && date.getMonth()==12 && date.getDay()>22) || date.getYear()==2017){
            if(type.equals(CombiType.FUTURE_GOODS))
                return "AU1706-AUTD";
            else
                return "GC-AU1706";
        }
        else{
            if(type.equals(CombiType.FUTURE_GOODS))
                return "AU1612-AUTD";
            else
                return "GC-AU1612";
        }
    }


}
