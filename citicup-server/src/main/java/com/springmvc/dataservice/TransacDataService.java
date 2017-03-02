package com.springmvc.dataservice;

import com.springmvc.config.Msg;
import com.springmvc.entities.CapitourEntity;
import com.springmvc.entities.CombinationOnEntity;
import com.springmvc.vo.AlarmVO;
import com.springmvc.vo.HoldInfoVO;
import com.springmvc.vo.TransacRecordVO;

import java.util.Date;
import java.util.List;

/**
 * Created by guhan on 16/8/28.
 */
public interface TransacDataService {





    //+获得套利组合的交易记录, 按时间排序,最近发生的在前
    public List<TransacRecordVO> getAllTransactions(String account, String userName);

    //+添加组合交易记录
    public Msg addTransacRecord(String account, String userName, TransacRecordVO vo, int isEnded);




    //+获得套利组合的持仓情况, 3种组合,每种组合的多头是哪只,空头是哪只,手数分别是多少
    public HoldInfoVO getPosition(String account, String userName, int type);

    //+返回尚未结束的交易记录
    public List<CombinationOnEntity> getCurrentPosition(String account, String userName, int type);

    //+增加一条尚未结束的仓位的交易记录
    public Msg addOnRecord(CombinationOnEntity entity);

    //+平仓,删除该账户该组合下的所有交易记录
    public Msg removeOnRecord(String account, String userName, int type);

    //添加一条AlarmEntity记录
    public Msg addAlarmRecord(String userName, AlarmVO vo);

    //返回所有AlarmEntity, 按时间先后,不超过200个
    public List<AlarmVO> getAlarm(String userName);

    //返回所有AlarmEntity.isRead标记为0的,按时间先后,不超过200个
    public List<AlarmVO> getUnread(String userName);

    //把所有AlarmEntity.isRead的0标记为1
    public Msg markReadAll(String userName);

    /******************************************** NEW ****************************************/


}
