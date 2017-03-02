package com.springmvc.bl;

import com.springmvc.bl.info.HorizontalService;
import com.springmvc.blservice.FutureInfoBLService;
import com.springmvc.blservice.MonitorBLService;
import com.springmvc.blservice.TransacBLService;
import com.springmvc.config.CombiType;
import com.springmvc.config.FutureConstant;
import com.springmvc.config.Msg;
import com.springmvc.config.TypeDefine;
import com.springmvc.dataservice.TransacDataService;
import com.springmvc.dataservice.UserDataService;
import com.springmvc.entities.AlarmEntity;
import com.springmvc.entities.CombinationOnEntity;
import com.springmvc.entities.TraderEntity;
import com.springmvc.vo.AlarmVO;
import com.springmvc.vo.HoldInfoVO;
import com.springmvc.vo.ResultMessageVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by guhan on 16/8/31.
 */
@Service
public class MonitorBLImp implements MonitorBLService {

    @Resource
    TransacDataService transacDataService;

    @Resource
    UserDataService userDataService;

    @Resource
    HorizontalService horizontalService;

    @Resource
    TransacBLService transacBLService;

    @Resource
    FutureInfoBLService futureInfoBLService;

    //result.Msg==YES,发出预警
    public ResultMessageVO alarm(String account, String userName, CombiType type) {
        int type_int = TypeDefine.getCombiTypeToInt(type);
        String combinationName = TypeDefine.getCombiTypeToString(type);
        Date today = new Date();
        HoldInfoVO vo = transacDataService.getPosition(account,userName,type_int);
        TraderEntity trader = userDataService.getAccount(account, userName);
        double sp = trader.getSP();
        double sl = trader.getSL();
        String bullName = vo.getBullName();
        String bearName = vo.getBearName();
        double bullPrice = horizontalService.getFutureByCode(bullName).getNowPrice();
        double bearPrice = horizontalService.getFutureByCode(bearName).getNowPrice();

        //止损点
        //检查是否到达止损点,是否自动平仓
        double deposit = trader.getDiposit();
        List<CombinationOnEntity> list = transacDataService.getCurrentPosition(account,userName,type_int);
        double diff = computeProfit(list, bullPrice, bearPrice);
        if(diff < 0 && deposit * sl <= Math.abs(diff)){
            //check是否允许自动平仓
            if(checkClosePermission(trader, type)) {
                //平仓
                transacBLService.closeOut(account, userName, type);
                return new ResultMessageVO(Msg.YES, "close");
            }
            transacDataService.addAlarmRecord(userName, new AlarmVO(account, today, combinationName, "AutoClosedSL", 0));
            return new ResultMessageVO(Msg.YES, "not permitted");
        }

        //检查是否到达止损点的90%
        if(diff < 0 && deposit * sl * FutureConstant.ALARM_STANDARD <= Math.abs(diff)){
            transacDataService.addAlarmRecord(userName, new AlarmVO(account, today, combinationName, "SL", 0));
            return new ResultMessageVO(Msg.YES, "alarm");
        }

       //止盈点
        List<Double> fluctuation = futureInfoBLService.getMin(bullName,bearName,3);           //默认作差方向是SHEF-COMEX
        double delta1 = fluctuation.get(0);
        double delta2 = fluctuation.get(1);
        double delta3 = fluctuation.get(2);     //p3为当前分钟数据
        if(delta1<delta2 && delta2>delta3){
            double up = delta2 - delta1;
            double down = delta3 - delta2;
            //检查是否到达止盈点,是否自动平仓
            if(down >= up*sp){
                //check是否允许自动平仓
                if(checkClosePermission(trader, type)){
                    //平仓
                    transacBLService.closeOut(account, userName, type);
                    return new ResultMessageVO(Msg.YES, "close");
                }
                transacDataService.addAlarmRecord(userName, new AlarmVO(account, today, combinationName, "AutoClosedSP", 0));
                return new ResultMessageVO(Msg.YES, "not permitted");
            }
            //检查是否到达止盈点的90%
            if(down >= up*sp*FutureConstant.ALARM_STANDARD){
                transacDataService.addAlarmRecord(userName, new AlarmVO(account, today, combinationName, "SP", 0));
                return new ResultMessageVO(Msg.YES, "alarm");
            }
        }

        return new ResultMessageVO(Msg.NO, "");
    }

    public List<AlarmVO> getAlarmHistory(String userName) {
        return transacDataService.getAlarm(userName);
    }

    public List<AlarmVO> getUnread(String userName) {
        return transacDataService.getUnread(userName);
    }

    public ResultMessageVO markRead(String userName) {

        return new ResultMessageVO(transacDataService.markReadAll(userName),"");
    }


    //假设现在平仓,资金账户盈亏多少钱, 正为盈, 负为亏
    private double computeProfit(List<CombinationOnEntity> list, double bullPrice, double bearPrice){
        double profit = 0;
        double bullProfit = 0;
        double bearProfit = 0;
        for(CombinationOnEntity entity: list){
            bullProfit += (bullPrice - entity.getBullPrice()) * entity.getBullAmount();
            bearProfit += (bearPrice - entity.getBearPrice()) * entity.getBearAmount();
        }
        profit = bullProfit - bearProfit;
        return profit;
    }

    private boolean checkClosePermission(TraderEntity trader, CombiType type){
        if(type.equals(CombiType.CROSS_MARKET)){
            return trader.isCrossMarketAutoClosed();
        }else if(type.equals(CombiType.FUTURE_GOODS)){
            return trader.isFutureGoodsAutoClosed();
        }else{
            return trader.isCrossTermAutoClosed();
        }
    }
}
