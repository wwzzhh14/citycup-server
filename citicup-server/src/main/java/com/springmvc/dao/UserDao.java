package com.springmvc.dao;

import com.springmvc.config.Msg;
import com.springmvc.dataservice.UserDataService;
import com.springmvc.entities.TraderEntity;
import com.springmvc.entities.UserEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wzh on 16/8/7.
 */

@Service
public class UserDao implements UserDataService {

    @Resource
    private BaseDao baseDao;


    public UserEntity getUserByName(String username) {
        List<UserEntity> userList = (List<UserEntity>)baseDao.findByProperty(UserEntity.class,"userName",username);


        if (userList.size()==0) {
            return null;
        }
        else{
            return userList.get(0);
        }
    }

    public Msg saveUser(UserEntity vo) {
        return baseDao.save(vo);
    }

    public Msg updateUser(UserEntity entity) {
        return baseDao.update(entity);
    }


    public TraderEntity getAccount(String account, String userName) {
        String properties[]={"traderAccount","userName"};
        String values[]={account,userName};
        List<TraderEntity> list = (List<TraderEntity>) baseDao.findByPropertys(TraderEntity.class,properties,values);
        if (list.size()>0){
            return list.get(0);
        }
        return null;
    }

    public Msg saveAccount(TraderEntity entity) {
        return baseDao.save(entity);
    }

    public Msg updateAccount(TraderEntity entity) {

        return baseDao.update(entity);
    }

    public List<TraderEntity> getAllAccount(String userName) {
        return (List<TraderEntity>) baseDao.findByProperty(TraderEntity.class,"userName",userName);
    }


}
