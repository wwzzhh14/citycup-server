package com.springmvc.controller;

import com.springmvc.blservice.CapitourBLService;
import com.springmvc.vo.ResultMessageVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by guhan on 16/9/16.
 */
@Controller
public class CapitourController {

    @Resource
    CapitourBLService capitourBLService;

    @RequestMapping(value = "/chart/init", method = RequestMethod.GET)
    @ResponseBody
    public void init(){
        capitourBLService.init();
    }

    @RequestMapping(value = "/pair/delta", method = RequestMethod.GET)
    @ResponseBody
    public double[] getPairDelta(){
        return capitourBLService.getPairDelta();
    }

    @RequestMapping(value = "/pair/yield", method = RequestMethod.GET)
    @ResponseBody
    public double[] getPairYield(){
        return capitourBLService.getPairYield();
    }

    @RequestMapping(value = "/pair/date", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getPairDate(){
        return capitourBLService.getPairDate();
    }

    @RequestMapping(value = "/capitour/start", method = RequestMethod.POST)
    @ResponseBody
    public ResultMessageVO startCapitour(String account, String userName, Date start, String end, double money){
        return capitourBLService.startCapitour(account, userName, start, end, money);
    }

    @RequestMapping(value = "/capitour/viewProfit", method = RequestMethod.GET)
    @ResponseBody
    public List<Double> viewProfit(String account, String userName, Date start){
        return capitourBLService.viewProfit(account, userName, start);
    }

}
