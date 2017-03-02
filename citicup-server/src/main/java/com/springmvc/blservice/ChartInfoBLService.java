package com.springmvc.blservice;

/**
 * Created by wzh on 16/9/10.
 */
public interface ChartInfoBLService {
    public String getKLineData(String code);
    public String getMInLineData(String code);
}
