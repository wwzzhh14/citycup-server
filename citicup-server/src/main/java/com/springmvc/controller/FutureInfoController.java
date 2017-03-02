package com.springmvc.controller;

import com.springmvc.blservice.FutureInfoBLService;
import com.springmvc.blservice.UserBLService;
import com.springmvc.config.Msg;
import com.springmvc.vo.FutureComputedVO;
import com.springmvc.vo.FutureSummaryVO;
import com.springmvc.vo.LoginMsgVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wzh on 16/8/17.
 */

@Controller
public class FutureInfoController {


    @Resource
    FutureInfoBLService futureInfoBLService;

    @RequestMapping(value = "/FutureInfo/Summary", method = RequestMethod.GET)
    @ResponseBody
    public List<FutureSummaryVO> getSummaryInfo(){
        return futureInfoBLService.getFutureSummaryInfo();
    }

    @RequestMapping(value = "/FutureInfo/init", method = RequestMethod.GET)
    @ResponseBody
    public Msg initStaticInfo(){
        return futureInfoBLService.initStaticInfo();
    }

    @RequestMapping(value = "/FutureInfo/combine", method = RequestMethod.GET)
    @ResponseBody
    public FutureComputedVO getComputedValues(String code1, String code2){
        return futureInfoBLService.getComputedValues(code1, code2);
    }

    @RequestMapping(value = "/FutureInfo/single", method = RequestMethod.GET)
    @ResponseBody
    public FutureComputedVO getSingleValues(String code){
        return futureInfoBLService.getSingleValues(code);
    }
}
