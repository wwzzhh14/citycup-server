package com.springmvc.controller;

import com.springmvc.config.FutureConstant;
import com.springmvc.config.UserInfo;
import com.springmvc.dataservice.FutureInfoDataService;
import com.springmvc.dataservice.UserDataService;

import com.springmvc.entities.StaticFutureInfoEntity;
import com.springmvc.thread.DataGrabber;
import com.springmvc.utils.CrawlerUtil;
import com.springmvc.utils.NetUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by wzh on 16/8/6.
 */



@Controller
public class MainController {

    @Resource
    UserDataService userDataService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndex(HttpSession session){


        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        if (userInfo==null){
            return "login";
        }else {
            if (userInfo.isLogin()){
                return "menu";
            }else {
                return "login";
            }
        }

    }

}
