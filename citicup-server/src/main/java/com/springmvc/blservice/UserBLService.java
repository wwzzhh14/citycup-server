package com.springmvc.blservice;

import com.springmvc.config.Msg;
import com.springmvc.entities.TraderEntity;
import com.springmvc.entities.UserEntity;
import com.springmvc.vo.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by wzh on 16/8/10.
 */
public interface UserBLService {


    /**
     *
     * @param userName 账户名
     * @param password
     * @return 登录成功,显示姓名name
     */
    public LoginMsgVO login(String userName ,String password);

    /**
     * @return 注册,密保问题和回答不能为空
     */
    public RegisterMsgVO register(String name, String email, String telNumber, String userName, String password,
                                  String question, String answer);

    /*上传头像*/
    public Msg uploadHeader(String userName, MultipartFile file, HttpSession session);

    /*获取头像*/
    public ResponseEntity<byte[]> getHeader(String username) throws IOException;

    public UserVO getUserById(String username);

     /* 用法:1.用户找回密码时调用 2.用户忘记密保问题答案时调用, 其中answer = "" */
    public FindPwMsgVO findPw(String username, String answer, String name);

    public Msg modifyUserInfo(String username, String email, String telNumber, String question, String answer);

    public Msg modifyPw(String username, String newpw);

    public TraderEntity getAccountById(String account, String userName);

    public RegisterMsgVO registerAccount(String userName, String account, Date createAt, double diposit);

    public Msg modifySetting(String account, String userName, double sp, double sl, double single,
                             boolean isCrossMarketAutoClosed, boolean isFutureGoodsAutoClosed, boolean isCrossTermAutoClosed);


    public List<TraderEntity> getAllAccount(String userName);
}
