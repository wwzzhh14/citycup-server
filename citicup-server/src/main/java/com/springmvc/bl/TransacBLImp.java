package com.springmvc.bl;

import com.springmvc.bl.info.HorizontalService;
import com.springmvc.blservice.FutureInfoBLService;
import com.springmvc.blservice.TransacBLService;
import com.springmvc.config.CombiType;
import com.springmvc.config.Msg;
import com.springmvc.config.TypeDefine;
import com.springmvc.dataservice.TransacDataService;
import com.springmvc.dataservice.UserDataService;
import com.springmvc.entities.*;
import com.springmvc.vo.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by guhan on 16/8/28.
 */
@Service
public class TransacBLImp implements TransacBLService {
    //每手数量
    private static HashMap<String, Integer> quantityMap = null;

    //手续费
    private static HashMap<String, Double> costMap = null;

    @Resource
    TransacDataService transacDataService;
    @Resource
    UserDataService userDataService;
    @Resource
    HorizontalService horizontalService;
    @Resource
    FutureInfoBLService futureInfoBLService;


    public Msg init() {
        if(quantityMap == null) {
            quantityMap = new HashMap<String, Integer>();
            quantityMap.put("AU1612", 1000);        //克
            quantityMap.put("AU1706", 1000);
            quantityMap.put("AUTD", 1000);
            quantityMap.put("GC", 100);             //盎司
        }

        if(costMap == null){
            costMap = new HashMap<String, Double>();
            costMap.put("AU1612", 0.0002);            //交易金额的万分之2
            costMap.put("AU1706", 0.0002);
            costMap.put("AUTD", 0.0008);
            costMap.put("GC", 4.0);             //4刀每手
        }

        return Msg.SUCCESS;
    }

    public String getCombiName(CombiType type) {
        return TypeDefine.getCombiTypeToString(type);
    }

    public double computeProfit(double[] amountA, double[] amountB, double[] priceA, double[] priceB) {
        return 0;
    }

    //界面要判断一下如果两个手数都为0, 则不调用这个方法
    public TransacMsgVO closeOut(String account, String userName, CombiType combinationType) {
        int type_int = TypeDefine.getCombiTypeToInt(combinationType);
        String combinationName = TypeDefine.getCombiTypeToString(combinationType);
        Date today = new Date();
        TraderEntity trader = userDataService.getAccount(account, userName);
        List<CombinationOnEntity> list = transacDataService.getCurrentPosition(account, userName, type_int);
        HoldInfoVO vo = transacDataService.getPosition(account,userName,type_int);
        double bullProfit = 0;
        double bearProfit = 0;
        String bullName = list.get(0).getBullName();
        String bearName = list.get(0).getBearName();
        double bullPrice = horizontalService.getFutureByCode(bullName).getNowPrice();
        double bearPrice = horizontalService.getFutureByCode(bearName).getNowPrice();
        for(CombinationOnEntity entity: list){
            bullProfit += (bullPrice - entity.getBullPrice()) * entity.getBullAmount();
            bearProfit += (bearPrice - entity.getBearPrice()) * entity.getBearAmount();
        }
        double cost = computeCost(bullName, bullPrice, vo.getBullAmount()) + computeCost(bearName, bearPrice, vo.getBearAmount());
        double pureProfit = bullProfit - bearProfit - cost;
        double bullRatio = horizontalService.getFutureByCode(bullName).getMinRatio();
        double bullMargin = computeFrozen(bullName, bullPrice, vo.getBullAmount(), bullRatio);
        double bearRatio = horizontalService.getFutureByCode(bearName).getMinRatio();
        double bearMargin = computeFrozen(bearName, bearPrice, vo.getBearAmount(), bearRatio);
        double totalMargin = bullMargin + bearMargin;
        double frozen = trader.getFrozen();
        trader.setFrozen(frozen - totalMargin);
        double diposit = trader.getDiposit();
        trader.setDiposit(diposit + pureProfit);
        //收益+手续费,释放保证金
        userDataService.updateAccount(trader);
        //删除本次仓位信息
        transacDataService.removeOnRecord(account,userName,type_int);
        //存储交易记录
        transacDataService.addTransacRecord(account, userName, new TransacRecordVO(today, combinationName, bullName,bearName,vo.getBullAmount(),vo.getBearAmount(),bullPrice,bearPrice), 1);
        return new TransacMsgVO(Msg.SUCCESS,"",new TransacSheet(bullName, bearName,-vo.getBullAmount(),-vo.getBearAmount(),bullPrice,bearPrice,cost,totalMargin));
    }

    //下单, 调整本仓手数; 有可能反向建仓导致平仓
    public TransacMsgVO placeOrder(String account, String userName, CombiType combinationType, double bullAmount, double bullPrice, double bearAmount, double bearPrice) {
        int type_int = TypeDefine.getCombiTypeToInt(combinationType);   //组合类型
        String combinationName = TypeDefine.getCombiTypeToString(combinationType);
        Date today = new Date();    //本次交易时间
        double realBullAmount = 0;
        double realBearAmount = 0;
        String bullName = null;
        String bearName = null;
        HoldInfoVO vo = transacDataService.getPosition(account, userName, type_int);    //目前持仓情况
        if(vo!=null){
            bullName = vo.getBullName();
            bearName = vo.getBearName();
        }else{
            return new TransacMsgVO(Msg.FAIL,"wrong invoke",null);
        }
        TraderEntity trader = userDataService.getAccount(account, userName);        //资金账户
        double diposit = trader.getDiposit();

        double cost = computeCost(bullName,bullPrice,bullAmount) + computeCost(bearName,bearPrice,bearAmount);  //手续费
        trader.setDiposit(diposit - cost);
        if(bullAmount <= vo.getBullAmount() && bearAmount <= vo.getBearAmount()){
            //正常调整手数
            transacDataService.addOnRecord(new CombinationOnEntity(account,userName,type_int,today,bullName,bearName,bullAmount,bearAmount,bullPrice,bearPrice));
            //扣除手续费
            userDataService.updateAccount(trader);
            //存储交易记录
            transacDataService.addTransacRecord(account, userName, new TransacRecordVO(today, combinationName,bullName,bearName,bullAmount,bearAmount,bullPrice,bearPrice), -1);
            return new TransacMsgVO(Msg.SUCCESS, "", new TransacSheet(bullName,bearName,bullAmount,bearAmount,bullPrice,bearPrice,cost,0));     //不平仓不需要保证金
        }else{
            //强制平仓, 反向建仓
            if(bullAmount > vo.getBullAmount() && bearAmount < vo.getBearAmount()){
                return new TransacMsgVO(Msg.FAIL, "交易失败= =", null);
            }else if(bullAmount < vo.getBullAmount() && bearAmount > vo.getBearAmount()){
                return new TransacMsgVO(Msg.FAIL, "交易失败= =", null);
            }else{
                //强制平仓
                closeOut(account, userName, combinationType);
                //反向建新仓, 多头空头名字、价格反转
                realBearAmount += bullAmount - vo.getBullAmount();
                realBullAmount += bearAmount - vo.getBearAmount();
                CombinationOnEntity onEntity = new CombinationOnEntity(account,userName,type_int,today,bearName,bullName,realBullAmount,realBearAmount,bearPrice,bullPrice);
                transacDataService.addOnRecord(onEntity);
                //扣除手续费
                userDataService.updateAccount(trader);
                return new TransacMsgVO(Msg.SUCCESS, "", new TransacSheet());
            }
        }
    }

    //建仓时的手数可正可负, 界面要判断一下如果获取的两手数都是0,则不调用这个方法 ******************
    public TransacMsgVO setUp(String account, String userName, CombiType combinationType, String bullName, String bearName, double bullAmount, double bullPrice, double bearAmount, double bearPrice) {
        int type_int = TypeDefine.getCombiTypeToInt(combinationType);
        String combinationName = TypeDefine.getCombiTypeToString(combinationType);
        Date today  = new Date();
        double realBullAmount = 0;
        double realBearAmount = 0;
        if(bullAmount >=0)                          //正手在本方向建仓
            realBullAmount += bullAmount;
        else                                        //负手在反方向建仓
            realBearAmount += bullAmount;
        if(bearAmount >=0)
            realBearAmount += bearAmount;
        else
            realBullAmount += bearAmount;
        //买入时要看最低保证金是否达到
        TraderEntity trader = userDataService.getAccount(account, userName);
        double diposit = trader.getDiposit();
        double frozen = trader.getFrozen();
        double bullRatio = horizontalService.getFutureByCode(bullName).getMinRatio();
        double bullMargin = computeFrozen(bullName, bullPrice, bullAmount, bullRatio);
        double bearRatio = horizontalService.getFutureByCode(bearName).getMinRatio();
        double bearMargin = computeFrozen(bearName, bearPrice, bearAmount, bearRatio);
        double totalMargin = bullMargin + bearMargin;
        if(diposit-frozen < totalMargin)
            return new TransacMsgVO(Msg.FAIL,"lower than min", null);
        double cost = computeCost(bullName, bullPrice, realBullAmount) + computeCost(bearName, bearPrice, realBearAmount);
        //冻结保证金
        trader.setFrozen(frozen + totalMargin);
        //扣除手续费
        trader.setDiposit(diposit - cost);
        //更新资金账户信息
        userDataService.updateAccount(trader);
        //新建仓位
        transacDataService.addOnRecord(new CombinationOnEntity(account, userName, type_int, today, bullName, bearName, realBullAmount, realBearAmount, bullPrice, bearPrice));
        //存储购买记录
        transacDataService.addTransacRecord(account, userName, new TransacRecordVO(today, combinationName, bullName, bearName, realBullAmount, realBearAmount, bullPrice, bearPrice), 0);
        return new TransacMsgVO(Msg.SUCCESS, "", new TransacSheet(bullName, bearName, realBullAmount, realBearAmount, bullPrice, bearPrice, cost, totalMargin));
    }

    public HoldInfoVO getHold(String account, String userName, CombiType combinationType) {
        HoldInfoVO vo = transacDataService.getPosition(account, userName, TypeDefine.getCombiTypeToInt(combinationType));
        if(vo==null){
            String combinationName = TypeDefine.getCombiTypeToString(combinationType);
            String[] split = combinationName.split("-");
            String bullName = split[0];
            String bearName = split[1];
            return new HoldInfoVO(bullName,0,bearName,0);
        }
        return new HoldInfoVO(vo.getBullName(), vo.getBullAmount(), vo.getBearName(), vo.getBearAmount());
    }

    public List<TransacRecordVO> getTransacRecordByAccount(String account, String userName) {
        return transacDataService.getAllTransactions(account, userName);
    }











    //自动计算出的保证金
    private double computeFrozen(String code, double price, double amount, double minRatio){
        if(code.equals("GC")){
            return minRatio*amount*futureInfoBLService.getExchangeRate();                         //5400刀每手
        }
        else{
            return price * amount * minRatio * quantityMap.get(code);
        }
    }



    //自动计算出的手续费,正的
    private double computeCost(String code, double price, double amount){

        if(code.equals("GC")){
            return costMap.get(code)*Math.abs(amount);                //constant
        }
        else{
            return costMap.get(code)*price*Math.abs(amount);          //ratio
        }
    }



}
