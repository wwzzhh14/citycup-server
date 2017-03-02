package com.springmvc.bl;

import com.springmvc.blservice.UserBLService;
import com.springmvc.config.Msg;
import com.springmvc.dataservice.TransacDataService;
import com.springmvc.entities.CapitourEntity;
import com.springmvc.entities.TraderEntity;
import com.springmvc.entities.UserEntity;
import com.springmvc.dataservice.UserDataService;
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
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by wzh on 16/8/10.
 */

@Service
public class UserBLImp implements UserBLService {

    @Resource
    UserDataService userDataService;
    @Resource
    TransacDataService transacDataService;

    public LoginMsgVO login(String userName, String password) {
        UserEntity userEntity = userDataService.getUserByName(userName);
        if(userEntity==null) {
            return new LoginMsgVO(Msg.FAIL, "");
        }
        else {
            if(!userEntity.getPassword().equals(password))
                return new LoginMsgVO(Msg.FAIL, "");
            return new LoginMsgVO(Msg.SUCCESS, userEntity.getName());
        }
    }

    public RegisterMsgVO register(String name, String email, String telNumber, String userName, String password, String question, String answer) {
        if(nameExist(userName)){
            return new RegisterMsgVO(Msg.DULICATE_KEY, name+" has existed!");
        }
        if(question.length()==0 || answer.length()==0){
            return new RegisterMsgVO(Msg.FAIL, "empty field!");
        }
        UserEntity user = new UserEntity();
        user.setName(name);
        user.setEmail(email);
        user.setTelNumber(telNumber);
        user.setUserName(userName);     //This is the primary key
        user.setPassword(password);
        user.setQuestion(question);
        user.setAnswer(answer);
        return new RegisterMsgVO(userDataService.saveUser(user), "");
    }

    public Msg uploadHeader(String userName, MultipartFile file, HttpSession session) {
        try {
            if (!file.isEmpty()){
                //使用StreamsAPI方式拷贝文件
                String relativelyPath=session.getServletContext().getRealPath("/")+"/headers";

                File pathFile = new File(relativelyPath);

                if (!pathFile.exists()) {
                    //文件夹不存 创建文件
                    pathFile.mkdirs();
                }
                Streams.copy(file.getInputStream(),new FileOutputStream("headers/"+userName+".png"),true);
                return Msg.SUCCESS;
            }
        } catch (IOException e) {
            System.out.println("文件上传失败");
            return Msg.FAIL;
//            e.printStackTrace();
        };
        return null;
    }

    public ResponseEntity<byte[]> getHeader(String username) throws IOException {
        //                指定文件,必须是绝对路径
        String relativelyPath=System.getProperty("user.dir")+"/headers/"+username+".png";

        File file= new File(relativelyPath);
//                下载浏览器响应的那个文件名
        String dfileName = username+".png";
//                下面开始设置HttpHeaders,使得浏览器响应下载
        HttpHeaders headers = new HttpHeaders();
//                设置响应方式
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//                设置响应文件
        headers.setContentDispositionFormData("attachment", dfileName);
//                把文件以二进制形式写回
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);

    }

    public UserVO getUserById(String username) {

        System.out.println(username);
        UserVO vo = new UserVO();
        UserEntity entity = userDataService.getUserByName(username);

        if (entity!=null){
            vo.setUserName(entity.getUserName());
            vo.setName(entity.getName());
            vo.setEmail(entity.getEmail());
            vo.setTelNumber(entity.getTelNumber());
            vo.setQuestion(entity.getQuestion());
            vo.setAnswer(entity.getAnswer());
        }
        return vo;
    }

    public FindPwMsgVO findPw(String username, String answer, String name) {
        UserEntity userEntity = userDataService.getUserByName(username);
        if(userEntity.getAnswer().equals(answer) && userEntity.getName().equals(name)){
            return new FindPwMsgVO(Msg.SUCCESS, userEntity.getPassword());
        }
        return new FindPwMsgVO();
    }

    public Msg modifyUserInfo(String username, String email, String telNumber, String question, String answer) {
        if(question.length()==0 || answer.length()==0){
            return Msg.FAIL;
        }
        UserEntity entity = userDataService.getUserByName(username);
        entity.setEmail(email);
        entity.setTelNumber(telNumber);
        entity.setQuestion(question);
        entity.setAnswer(answer);
        return userDataService.updateUser(entity);
    }

    public Msg modifyPw(String username, String newpw) {
        UserEntity entity = userDataService.getUserByName(username);
        entity.setPassword(newpw);
        return userDataService.updateUser(entity);
    }

    public TraderEntity getAccountById(String account, String userName) {
        return userDataService.getAccount(account, userName);
    }

    public RegisterMsgVO registerAccount(String userName, String account, Date createAt, double diposit) {
        TraderEntity entity = new TraderEntity(userName, account, createAt, diposit );
        return new RegisterMsgVO(userDataService.saveAccount(entity), account);
    }

    public Msg modifySetting(String account, String userName, double sp, double sl, double single,
                             boolean isCrossMarketAutoClosed, boolean isFutureGoodsAutoClosed, boolean isCrossTermAutoClosed) {
        TraderEntity entity = userDataService.getAccount(account, userName);
        entity.setSP(sp);
        entity.setSL(sl);
        entity.setSingleInvestment(single);
        entity.setCrossMarketAutoClosed(isCrossMarketAutoClosed);
        entity.setFutureGoodsAutoClosed(isFutureGoodsAutoClosed);
        entity.setCrossTermAutoClosed(isCrossTermAutoClosed);
        return userDataService.updateAccount(entity);
    }

    public List<TraderEntity> getAllAccount(String userName) {
        return userDataService.getAllAccount(userName);
    }


    private boolean nameExist(String userName){
        UserEntity vo = userDataService.getUserByName(userName);
        if(vo!=null)
            return true;
        return false;
    }
}
