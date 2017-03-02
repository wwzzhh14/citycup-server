package com.springmvc.bl;

import com.springmvc.bl.info.HorizontalService;
import com.springmvc.blservice.FutureInfoBLService;
import com.springmvc.config.FutureConstant;
import com.springmvc.config.Msg;
import com.springmvc.config.UrlConstant;
import com.springmvc.dataservice.FutureInfoDataService;
import com.springmvc.entities.StaticFutureInfoEntity;
import com.springmvc.utils.CrawlerUtil;
import com.springmvc.utils.NetUtil;
import com.springmvc.vo.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by wzh on 16/8/13.
 */

@Service
public class FutureInfoImp implements FutureInfoBLService, HorizontalService {


    private static final int SHFE_START_INDEX = 326;
    private static final int CMX_START_INDEX = 180;


    Hashtable<String,StaticFutureInfo> table = FutureConstant.getFutureInfoHashtable();;
    String[] futureCodes= FutureConstant.getFutureCodes();
    Hashtable<String,String> codeTable=FutureConstant.getFutureCodeTable();

    public List<FutureSummaryVO> getFutureSummaryInfo() {
        List<FutureSummaryVO> list=new ArrayList<FutureSummaryVO>();

        if(table==null){
            return list;
        }
        for (String code:futureCodes){
//            code = codeTable.get(code);
            StaticFutureInfo staticFutureInfo=table.get(code);
            DynamicFutureInfo dynamicFutureInfo=getDynamicFutureInfo(code);
            list.add(new FutureSummaryVO(dynamicFutureInfo,staticFutureInfo));
        }
        return list;
    }

    public Msg initStaticInfo() {
        if(table!=null){
             return Msg.SUCCESS;
        }
        return Msg.FAIL;
    }

    public FutureInfoVO getFutureByCode(String code) {
        code = codeTable.get(code);
        double price=getDynamicFutureInfo(code).getNowPrice();
        StaticFutureInfo staticFutureInfo=table.get(code);
        if(staticFutureInfo!=null){
            return new FutureInfoVO(price,staticFutureInfo.getMinMarginRatio(),staticFutureInfo.getDeliveryMonth());

        }
        return null;
    }

    //回溯backward长度的分钟数据
    public List<Double> getPreMin(String code, int backward) {
        return null;
    }

    public FutureComputedVO getComputedValues(String code1, String code2) {
        List<Double> minHistory1 = getMinHistory(code1);
        List<Double> minHistory2 = getMinHistory(code2);

        if (minHistory1.size()==0||minHistory2.size()==0){
            return null;
        }
        System.out.println("min1:"+minHistory1.size() + " min2:"+minHistory2.size());
//        if(minHistory1.size() > minHistory2.size()){
//            return new FutureComputedVO(Msg.FAIL, "长度异常");
//        }
        double mean = 0.0;                            //差价均值
        double diff = 0;
        double maxDiff = Double.MIN_VALUE;          //最高
        double minDiff = Double.MAX_VALUE;          //最低
        List<Double> minHistory = new ArrayList<Double>();
        int minLen = (minHistory1.size() > minHistory2.size()) ? minHistory2.size() : minHistory1.size();
        for(int i=0; i< minLen; i++){
            diff = minHistory1.get(i) - minHistory2.get(i);
            if(maxDiff<diff)
                maxDiff = diff;
            if(minDiff>diff)
                minDiff = diff;
            minHistory.add(diff);
            mean += diff;
        }
        System.out.println(mean);
        System.out.println(minHistory1.size());
        mean /= minHistory1.size()*1.0;
        System.out.println(mean);
        return new FutureComputedVO(Msg.SUCCESS, "", Math.round(mean*100)*0.01, Math.round(maxDiff*100)*0.01, Math.round(minDiff*100)*0.01, minHistory);
    }

    public FutureComputedVO getSingleValues(String code) {
        List<Double> minHistory = getMinHistory(code);
        double mean = 0;
        double temp = minHistory.get(0);
        double max = Double.MIN_VALUE;
        double min = Double.MAX_VALUE;
        for(int i=0; i<minHistory.size(); i++){
            if(max < temp)
                max = temp;
            if(min > temp)
                min = temp;
            mean += temp;
        }
        mean /= minHistory.size()*1.0;
        return new FutureComputedVO(Msg.SUCCESS, "", Math.round(mean*100)*0.01, Math.round(max*100)*0.01, Math.round(min*100)*0.01, minHistory);
    }

    public List<Double> getMinHistory(String code) {

        List<Double> resultList = new ArrayList<Double>();

        if(code.equals("AUTD")){
            return FutureConstant.AutdMinDataList;
        }else if (code.equals("GC")){
            String resultString = NetUtil.httpGet(UrlConstant.CMX_MINLINE_URL+code);
            resultString = resultString.substring(1,resultString.length()-1);
            JSONObject jsonObject = JSONObject.fromObject(resultString);
            JSONArray jsonArray = jsonObject.getJSONArray("minLine_1d");

            if (jsonArray.size()<FutureConstant.AutdMinDataList.size()){
                return resultList;
            }

            for (int i=jsonArray.size()-FutureConstant.AutdMinDataList.size();i<jsonArray.size();i++){
                JSONArray subArray = JSONArray.fromObject(jsonArray.get(i));
                resultList.add(Double.parseDouble((String)subArray.get(1)));

//                System.out.println(subArray.get(0));
            }

        }else {

            String resultString = NetUtil.httpGet(UrlConstant.SHFE_MINLINE_URL+code);
            resultString = resultString.substring(1,resultString.length()-1);
            JSONObject jsonObject = JSONObject.fromObject(resultString);
            JSONArray jsonArray = jsonObject.getJSONArray("minLine_1d");

            if (jsonArray.size()<FutureConstant.AutdMinDataList.size()){
                return resultList;
            }
            for (int i=jsonArray.size()-FutureConstant.AutdMinDataList.size();i<jsonArray.size();i++){
                JSONArray subArray = JSONArray.fromObject(jsonArray.get(i));
                resultList.add(Double.parseDouble((String)subArray.get(0)));

            }

        }
        return resultList;
    }

    public List<Double> getMin(String code1, String code2,int backward) {
        List<Double> resultList = new ArrayList<Double>();
        List<Double> list1 = getMinHistory(code1);
        List<Double> list2 = getMinHistory(code2);
        double exchangeRate = getExchangeRate();

        if (list1.size()==0||list2.size()==0){
            return resultList;
        }
        if(code1=="GC") {
            for (int i = 0; i < backward; i++) {
                resultList.add(exchangeRate * list1.get(list1.size() - i - 1) - 3 * list2.get(list2.size() - i - 1));
            }
        }else if (code2=="GC"){
            for (int i = 0; i < backward; i++) {
                resultList.add(exchangeRate * list2.get(list1.size() - i - 1) - 3 * list1.get(list1.size() - i - 1));
            }
        }else {
            for (int i = 0; i < backward; i++) {
                resultList.add(list1.get(list1.size() - i - 1) -  list2.get(list2.size() - i - 1));
            }
        }
        return resultList;
    }


    private DynamicFutureInfo getDynamicFutureInfo(String code){
        String s=NetUtil.httpGet(UrlConstant.NOWTIME_URL +code);;
        String[] arr=s.split(",");
//        System.out.println(arr.length);
        if(arr.length<9){
            return new DynamicFutureInfo(0,0,0,0,0,0);
        }else {
            double nowPrice;
            double yesClose;
            double todOpen;
            double max;
            double min;
            double rate=0;
            if(code.contains("hf")){
                nowPrice = Double.parseDouble(arr[0].substring(arr[0].indexOf("\"")+1));
                yesClose = Double.parseDouble(arr[7]);
                todOpen = Double.parseDouble(arr[8]);
                max = Double.parseDouble(arr[4]);
                min = Double.parseDouble(arr[5]);
                rate = Double.parseDouble(arr[1]);

            }else {
                 nowPrice=Double.parseDouble(arr[8]);
                 yesClose=Double.parseDouble(arr[5]);
                 todOpen=Double.parseDouble(arr[2]);
                 max=Double.parseDouble(arr[3]);
                 min=Double.parseDouble(arr[4]);
                if(todOpen!=0){
                    rate=(nowPrice-todOpen)/todOpen;
                }
            }

            return new DynamicFutureInfo(nowPrice,yesClose,todOpen,max,min,rate);

        }

    }

    public double getExchangeRate(){
        String s = NetUtil.httpGet(UrlConstant.DOLLAR_EXCHANGERATE_URL);
        String[] arr = s.split(",");
        return Double.parseDouble(arr[1]);
    }


    private double getMean(double[] a){
        double mean = 0.0;
        int length = a.length;
        for(int i=0; i<length; i++){
            mean += a[i];
        }
        mean /= length*1.0;
        return mean;
    }


}
