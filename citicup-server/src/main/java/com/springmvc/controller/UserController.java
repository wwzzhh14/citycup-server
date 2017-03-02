package com.springmvc.controller;

import com.springmvc.blservice.UserBLService;
import com.springmvc.config.Msg;
import com.springmvc.entities.TraderEntity;
import com.springmvc.entities.UserEntity;
import com.springmvc.vo.FindPwMsgVO;
import com.springmvc.vo.LoginMsgVO;
import com.springmvc.vo.RegisterMsgVO;
import com.springmvc.vo.UserVO;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
 * Created by wzh on 16/8/10.
 */

@Controller
public class UserController {

    @Resource
    UserBLService userBLService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public LoginMsgVO login(String userName,String password){
        return userBLService.login(userName,password);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public RegisterMsgVO register(String name, String email, String telNumber, String userName, String password, String question, String answer) throws UnsupportedEncodingException {
        return userBLService.register(name,email,telNumber,userName,password,question,answer);
    }

    @RequestMapping(value = "/header",method = RequestMethod.GET)
    public ResponseEntity<byte[]> getHeader(String username) throws IOException {
        return userBLService.getHeader(username);
    }

    @RequestMapping(value = "/upload/header",method = RequestMethod.POST)
    @ResponseBody
    public Msg uploadHeader(String userName,@RequestParam("file") MultipartFile file,HttpSession httpSession){

        return userBLService.uploadHeader(userName,file,httpSession);
    }

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    @ResponseBody
    public UserVO getUser(String userName){
        System.out.println(userName);
        return userBLService.getUserById(userName);
    }

    @RequestMapping(value = "/findPassword", method = RequestMethod.GET)
    @ResponseBody
    public FindPwMsgVO findPw(String username, String answer, String name){
        return userBLService.findPw(username, answer, name);
    }

    @RequestMapping(value = "/modifyInfo", method = RequestMethod.POST)
    @ResponseBody
    public  Msg modifyUserInfo(String username, String email, String telNumber, String question, String answer){
        return userBLService.modifyUserInfo(username, email, telNumber, question, answer);
    }

    @RequestMapping(value = "/modifyPassword", method = RequestMethod.POST)
    @ResponseBody
    public Msg modifyPw(String username, String newpw){
        return userBLService.modifyPw(username, newpw);
    }

    @RequestMapping(value = "/getAllAccount", method = RequestMethod.GET)
    @ResponseBody
    public List<TraderEntity> getAllAccount(String userName){
        return userBLService.getAllAccount(userName);
    }

    @RequestMapping(value = "/getAccount", method = RequestMethod.GET)
    @ResponseBody
    public TraderEntity getAccount(String account, String userName){
        return userBLService.getAccountById(account, userName);
    }

    @RequestMapping(value = "/registerAccount", method = RequestMethod.POST)
    @ResponseBody
    public RegisterMsgVO registerAccount(String userName, String account, Date createAt, double diposit) throws UnsupportedEncodingException {
        userName = new String(userName.getBytes(),"utf-8");
        return userBLService.registerAccount(userName,account, createAt, diposit);
    }

    @RequestMapping(value = "/modifyAccount", method = RequestMethod.GET)
    @ResponseBody
    public Msg modifySetting(String account, String userName, double sp, double sl, double single,
                             boolean isCrossMarketAutoClosed, boolean isFutureGoodsAutoClosed, boolean isCrossTermAutoClosed) {
        return userBLService.modifySetting(account, userName, sp, sl, single, isCrossMarketAutoClosed, isFutureGoodsAutoClosed, isCrossTermAutoClosed);
    }
}
