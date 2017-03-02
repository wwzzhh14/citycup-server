package com.springmvc.dataservice;

import com.springmvc.config.Msg;
import com.springmvc.entities.TraderEntity;
import com.springmvc.entities.UserEntity;

import java.util.List;

/**
 * Created by wzh on 16/8/7.
 */


public interface UserDataService {

    public UserEntity getUserByName(String UserName);
    public Msg saveUser(UserEntity entity);
    public Msg updateUser(UserEntity entity);

    public TraderEntity getAccount(String account, String userName);
    public Msg saveAccount(TraderEntity entity);
    public Msg updateAccount(TraderEntity entity);

    public List<TraderEntity> getAllAccount(String userName);


}
