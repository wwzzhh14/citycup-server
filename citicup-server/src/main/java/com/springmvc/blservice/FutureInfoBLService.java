package com.springmvc.blservice;

import com.springmvc.config.Msg;
import com.springmvc.vo.FutureComputedVO;
import com.springmvc.vo.FutureInfoVO;
import com.springmvc.vo.FutureSummaryVO;

import java.util.List;

/**
 * Created by wzh on 16/8/13.
 */


public interface FutureInfoBLService {

    public List<FutureSummaryVO> getFutureSummaryInfo();
    public Msg initStaticInfo();

    public List<Double> getMinHistory(String code);

    //作差后的分钟数据, 长度为3
    public List<Double> getMin(String code1, String code2,int backward);

    public FutureInfoVO getFutureByCode(String code);

    public FutureComputedVO getComputedValues(String code1, String code2);

    public FutureComputedVO getSingleValues(String code);


    public double getExchangeRate();

}
