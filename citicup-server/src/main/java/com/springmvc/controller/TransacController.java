package com.springmvc.controller;

import com.springmvc.blservice.TransacBLService;
import com.springmvc.config.CombiType;
import com.springmvc.config.Msg;
import com.springmvc.vo.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by guhan on 16/9/8.
 */

@Controller
public class TransacController {
    @Resource
    TransacBLService transacBLService;


    //要先调用init, 才能调用其它
    @RequestMapping(value = "/transac/init")
    @ResponseBody
    public Msg init(){
        return transacBLService.init();
    }

    @RequestMapping(value = "/transac/combinationName")
    @ResponseBody
    public String getCombiName(CombiType type){
        return transacBLService.getCombiName(type);
    }

    @RequestMapping(value = "/transac/closeOut", method = RequestMethod.POST)
    @ResponseBody
    public TransacMsgVO closeOut(String account, String userName, CombiType combinationType){
        return transacBLService.closeOut(account, userName, combinationType);
    }
    @RequestMapping(value = "/transac/placeOrder", method = RequestMethod.GET)
    @ResponseBody
    public TransacMsgVO placeOrder(String account, String userName, CombiType combinationType, String bullAmount, String bullPrice, String bearAmount, String bearPrice){
        System.out.println(account+" "+userName+" "+combinationType.toString()+" "+bullAmount+" "+bullPrice);
        return transacBLService.placeOrder(account, userName, combinationType, Double.parseDouble(bullAmount), Double.parseDouble(bullPrice),Double.parseDouble( bearAmount), Double.parseDouble(bearPrice));
    }

    @RequestMapping(value = "/transac/setUp", method = RequestMethod.POST)
    @ResponseBody
    public TransacMsgVO setUp(String account, String userName, CombiType combinationType, String bullName, String bearName, double bullAmount, double bullPrice, double bearAmount, double bearPrice){
        return transacBLService.setUp(account, userName, combinationType, bullName, bearName, bullAmount, bullPrice, bearAmount, bearPrice);
    }


    @RequestMapping(value = "/transac/showHold", method = RequestMethod.GET)
    @ResponseBody
    public HoldInfoVO getHold(String account, String userName, CombiType combinationType){
        return transacBLService.getHold(account, userName, combinationType);
    }

    @RequestMapping(value = "/transac/history", method = RequestMethod.GET)
    @ResponseBody
    public List<TransacRecordVO> getTransacRecordByAccount(String account, String userName){
        return transacBLService.getTransacRecordByAccount(account, userName);
    }
}
