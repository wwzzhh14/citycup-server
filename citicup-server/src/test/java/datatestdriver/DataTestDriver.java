package datatestdriver;

/**
 * Created by wzh on 16/8/28.
 */

import com.springmvc.blservice.UserBLService;
import com.springmvc.config.CombiType;
import com.springmvc.config.Msg;
import com.springmvc.config.TypeDefine;
import com.springmvc.dataservice.TransacDataService;
import com.springmvc.dataservice.UserDataService;
import com.springmvc.entities.*;
import com.springmvc.utils.TimeUtil;
import com.springmvc.vo.AlarmVO;
import com.springmvc.vo.FutureHeldVO;
import com.springmvc.vo.TransacRecordVO;
import com.springmvc.vo.TransacVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class DataTestDriver {

    @Resource
    UserDataService userDataService;
    @Resource
    TransacDataService transacDataService;

    @Resource
    UserBLService userBLService;
    //    public UserEntity getUserByName(String UserName);
//    public Msg saveUser(UserEntity entity);
//    public Msg updateUser(UserEntity entity);
//
//    public TraderEntity getAccount(String account, String userName);
//    public Msg saveAccount(TraderEntity entity);
//    public Msg updateAccount(TraderEntity entity);
//
//
//    public List<FutureHeldEntity> getAllFuture(String account, String userName);
//    public FutureHeldEntity getFutureByCode(String account, String userName, String code);
//    public Msg addFuture(FutureHeldEntity entity);
//    public Msg removeFuture(String account, String userName, String code);
//    public Msg updateFuture(FutureHeldEntity entity);
    @Test
    public void testUserDataService() {

//        transacDataService.addAlarmRecord("aaaa",new AlarmVO("1111",new Date(),"zzz","cccc",0));
//        transacDataService.addAlarmRecord("aaaa",new AlarmVO("1111",TimeUtil.getDate("2016-09-09"),"zzz","cccc",0));

//        transacDataService.markReadAll("aaaa");

//        transacDataService.addOnRecord(new CombinationOnEntity("1111","aaaa",0,new Date(),"bbb","ccc",10,10,101,10));
//        transacDataService.addOnRecord(new CombinationOnEntity("1111","aaaa",0,TimeUtil.getDate("2016-10-01"),"bbb","ccc",10,10,101,10));

//        transacDataService.removeOnRecord("1111","aaaa",0);
//        userDataService.saveAccount(new TraderEntity("55057","6666",TimeUtil.getDate("2016-01-01"),10));

//        TraderEntity entity = userDataService.getAccount("242","aaa");
//        entity.setCrossMarketAgency(true);
//        userDataService.updateAccount(entity);

        userBLService.registerAccount("王志豪","11111",new Date(),10);
//        userBLService.modifySetting("11111","qqq",1,1,1,true,true,true,1,1,1,true,true,true);
        //        userDataService.updateAccount(new TraderEntity("aaa","242",new Date(),10));
//        System.out.println(userDataService.getUserByName("aaa").getUserName());

//       System.out.println(userDataService.getAccount("6666","5555").isCrossMarketAgency());
//        transacDataService.getTransacByAccount(String account, String userName);
//
//        transacDataService.saveTransacRec(String account, String userName, TransacVO vo);
//
//        transacDataService.getTransacByCode(String account, String userName, String code, String time);

//    //这里改成VO了。返回的list中不应该有重复的期货代码,相同代码、相同组合类型的应该把手数相加
//        List<FutureHeldVO> list=transacDataService.getAllFuture("1762", "aaa", TypeDefine.getCombiTypeToInt(CombiType.Futures));
//        for (FutureHeldVO vo:list){
//            System.out.println(vo.getAmount()+"  "+vo.getCode());
//        }
//    //单支期货手数求和
//    transacDataService.getParticularFuture(String account, String userName, CombiType type, String code);
//    //按时间先后排序,先买的在前
//        System.out.println(transacDataService.getFutureByCode("1762","iii","AU12", 1).get(0).getType());

//        transacDataService.addFuture(new FutureHeldEntity("1962","amba","AU12", TimeUtil.getDate("2016-01-01"),CombiType.FutureGoods,100,100));
//        transacDataService.removeFuture(FutureHeldEntity entity);
//        transacDataService.updateFuture(FutureHeldEntity entity);


//        userDataService.saveUser(new UserEntity("aaa", "2E2E221312", "AAA", "aaa", "aaa", "aaa", "aaa", "aaa"));
//        UserEntity entity = userDataService.getUserByName("aaa");
//        entity.setPassword("11111");
//        userDataService.updateUser(entity);

//        userDataService.saveAccount(new TraderEntity("ccc","1113",11.11,11.11,11.11,11.11,11.11,11.11));
//        TraderEntity entity = userDataService.getAccount("1113","ccc");
//        entity.setDiposit(99);
//        userDataService.updateAccount(entity);

//        userDataService.addFuture(new FutureHeldEntity("aaa","1111","sh111111",10));
//        userDataService.addFuture(new FutureHeldEntity("aaa","1111","sh111211",10));
//        userDataService.addFuture(new FutureHeldEntity("aaa","1111","sh111311",10));
//        userDataService.addFuture(new FutureHeldEntity("aaa","1111","sh111411",10));

//        System.out.println(userDataService.getAllFuture("aaa","1111").size());

//        System.out.println(userDataService.getAllFuture("hgvg","1111").get(0).getAmount());

//        List<TraderEntity> list = userDataService.getAllAccount("zs14");
//        System.out.println(list.size());

        List<TransacRecordVO> list = transacDataService.getAllTransactions("12345","zs14");
        System.out.print(list.size());
    }




}