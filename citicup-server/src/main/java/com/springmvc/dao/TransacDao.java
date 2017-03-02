package com.springmvc.dao;

import com.springmvc.config.Msg;
import com.springmvc.dataservice.TransacDataService;
import com.springmvc.entities.*;
import com.springmvc.vo.AlarmVO;
import com.springmvc.vo.HoldInfoVO;
import com.springmvc.vo.TransacRecordVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by wzh on 16/8/28.
 */

@Service
public class TransacDao implements TransacDataService {

    @Resource BaseDao baseDao;


    public List<TransacRecordVO> getAllTransactions(String account, String userName) {
        String properties[]={"account","userName"};
        String values[]={account,userName};
        List<TransacRecordEntity> entities = (List<TransacRecordEntity>) baseDao.findByPropertys(TransacRecordEntity.class,properties,values);
        List<TransacRecordVO> voList = new ArrayList<TransacRecordVO>();

        for (int i =0 ;i<200;i++){
            if (i>entities.size()-1){
                break;
            }
            TransacRecordEntity entity = entities.get(i);
            voList.add(new TransacRecordVO(entity.getTime(),entity.getCombiName(),entity.getBullName(),entity.getBearName(),entity.getBullAmount(),
                    entity.getBearAmount(),entity.getBullPrice(),entity.getBearPrice()));
        }
        return voList;
    }

    public Msg addTransacRecord(String account, String userName, TransacRecordVO vo, int isEnded) {
        if (vo!=null){
            return baseDao.save(new TransacRecordEntity(account,userName,vo.getTime(),vo.getCombiName(),vo.getBullName(),vo.getBearName(),
                    vo.getBullAmount(),vo.getBearAmount(),vo.getBullPrice(),vo.getBearPrice(), isEnded));
        }
        return Msg.FAIL;
    }

    public HoldInfoVO getPosition(String account, String userName, int type) {
        String properties[]={"account","userName","type"};
        Object values[]={account,userName,type};
        double bullAmount = 0;
        double bearAmount = 0;

        List<CombinationOnEntity> entities = (List<CombinationOnEntity>) baseDao.findByPropertys(CombinationOnEntity.class,properties,values);
        if(entities.size()==0){
            return null;
        }
        for (CombinationOnEntity entity:entities){
            bullAmount+=entity.getBullAmount();
            bearAmount+=entity.getBearAmount();
        }
        return new HoldInfoVO(entities.get(0).getBullName(),bullAmount,entities.get(0).getBearName(),bearAmount);
    }

    public List<CombinationOnEntity> getCurrentPosition(String account, String userName, int type) {
        String properties[]={"account","userName","type"};
        Object values[]={account,userName,type};
        return (List<CombinationOnEntity>) baseDao.findByPropertys(CombinationOnEntity.class,properties,values);
    }

    public Msg addOnRecord(CombinationOnEntity entity) {

        return baseDao.save(entity);
    }

    public Msg removeOnRecord(String account, String userName, int type) {
        String properties[]={"account","userName","type"};
        Object values[]={account,userName,type};
        return baseDao.deleteByProperties(CombinationOnEntity.class,properties,values);
    }


    public Msg addAlarmRecord(String userName, AlarmVO vo) {
        return baseDao.save(new AlarmEntity(vo.getAccount(),userName,vo.getTime(),vo.getCombinationName(),vo.getMsg(),vo.getIsRead()));
    }

    public List<AlarmVO> getAlarm(String userName) {
        List<AlarmEntity> entities = (List<AlarmEntity>) baseDao.findByProperty(AlarmEntity.class,"userName",userName);
        List<AlarmVO> voList = new ArrayList<AlarmVO>();

        for (int i =0 ;i<200;i++){
            if (i>entities.size()-1){
                break;
            }
            AlarmEntity entity = entities.get(i);
            voList.add(new AlarmVO(entity.getAccount(),entity.getTime(),entity.getCombinationName(),entity.getMsg(),entity.getIsRead()));
        }
        return voList;
    }

    public List<AlarmVO> getUnread(String userName) {
        String properties[]={"isRead","userName"};
        Object values[]={0,userName};
        List<AlarmEntity> entities = (List<AlarmEntity>) baseDao.findByPropertys(AlarmEntity.class,properties,values);
        List<AlarmVO> voList = new ArrayList<AlarmVO>();

        for (int i =0 ;i<200;i++){
            if (i>entities.size()-1){
                break;
            }
            AlarmEntity entity = entities.get(i);
            voList.add(new AlarmVO(entity.getAccount(),entity.getTime(),entity.getCombinationName(),entity.getMsg(),entity.getIsRead()));
        }
        return voList;
    }

    public Msg markReadAll(String userName) {
        String properties[]={"userName"};
        Object values[]={userName};
        String updateProperties[]={"isRead"};
        Object updateValues[]={1};
       return baseDao.updateByProperties(AlarmEntity.class,properties,values,updateProperties,updateValues);
    }


}
