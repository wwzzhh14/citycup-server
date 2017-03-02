package com.springmvc.controller;

import com.springmvc.blservice.UserBLService;
import com.springmvc.config.Msg;
import com.springmvc.vo.LoginMsgVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by wzh on 16/9/11.
 */

@Controller
public class PagesController {


    @Resource
    UserBLService userBLService;


    public String login(String userName,String password){

        LoginMsgVO msg = userBLService.login(userName,password);
        if (msg.getStutus()== Msg.SUCCESS){
            return "menu";
        }
        return "login";
    }

    @RequestMapping("/page/register")
    public String register(){
        return "register";
    }


}
