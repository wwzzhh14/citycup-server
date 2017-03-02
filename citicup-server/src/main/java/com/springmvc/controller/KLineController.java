package com.springmvc.controller;
import com.springmvc.blservice.ChartInfoBLService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by zs14 on 2016/9/6.
 */
@Controller
public class KLineController {


    @Resource
    ChartInfoBLService chartInfoBLService;

    @RequestMapping(value = "/klinedata",method = RequestMethod.GET)
    @ResponseBody
    public String kline(String code){

        return chartInfoBLService.getKLineData(code);
    }

    @RequestMapping(value = "/minlinedata",method = RequestMethod.GET)
    @ResponseBody
    public String minLine(String code){

        return chartInfoBLService.getMInLineData(code);
    }
    @RequestMapping(value = "/kline")
    public String k(String code){
        return "kLine";
    }
    @RequestMapping(value = "/kDoubleLine")
    public String kDouble( String code1,String code2){
        return "kDoubleLine";
    }

    @RequestMapping(value = "/bar")
    public String bar(String code){
        return "bar";
    }
    @RequestMapping(value = "/line11")
    public String line11(){
        return "line11";
    }
    @RequestMapping(value = "/line12")
    public String line12(){
        return "line12";
    }
}
