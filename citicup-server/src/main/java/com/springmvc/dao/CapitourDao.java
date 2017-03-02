package com.springmvc.dao;

import com.springmvc.config.Msg;
import com.springmvc.dataservice.CapitourDataService;
import com.springmvc.entities.CapitourEntity;
import com.springmvc.entities.DailyProfitEntity;
import com.springmvc.entities.InvestmentEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by guhan on 16/9/15.
 */
@Service
public class CapitourDao implements CapitourDataService {
    @Resource
    BaseDao baseDao;

    public Msg saveDailyRatio(String date, double yieldRatio) {
        return baseDao.save(new DailyProfitEntity(date,yieldRatio));
    }

    public double getDailyRatio(String date) {

        List<DailyProfitEntity> entities = (List<DailyProfitEntity>) baseDao.findByProperty(DailyProfitEntity.class,"date",date);
        if (entities.size()==0){
            return 0;
        }
        return entities.get(0).getYieldRatio();
    }




    public Msg addCapitour(CapitourEntity entity) {
        return baseDao.save(entity);
    }
//
    public CapitourEntity getCapitour(String account, String userName, Date start) {

        String properties[]={"account","userName","start"};
        Object values[]={account,userName,start};
        List<CapitourEntity> entities = (List<CapitourEntity>) baseDao.findByPropertys(CapitourEntity.class,properties,values);
        if (entities.size()==0){
            return null;
        }
        return entities.get(0);
    }

    public List<CapitourEntity> getCapitourList(String account, String userName) {
        String properties[] = {"account", "userName"};
        Object values[] = {account, userName,};
        return (List<CapitourEntity>) baseDao.findByPropertys(CapitourEntity.class, properties, values);
    }
    public List<CapitourEntity> selectCapitour(String end) {
        return (List<CapitourEntity>) baseDao.findByProperty(CapitourEntity.class,"end",end);
    }



    public Msg updateMoney(double investment) {
        InvestmentEntity entity = (InvestmentEntity) baseDao.findByProperty(InvestmentEntity.class,"id",1).get(0);
        entity.setInvestment(entity.getInvestment()+investment);
        return baseDao.update(entity);

    }

    public double getMoney() {
        InvestmentEntity entity = (InvestmentEntity) baseDao.findByProperty(InvestmentEntity.class,"id",1).get(0);
        if (entity!=null){
            return entity.getInvestment();
        }
        return 0;
    }
}
