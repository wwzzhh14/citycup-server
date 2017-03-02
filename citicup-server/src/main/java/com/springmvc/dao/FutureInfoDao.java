package com.springmvc.dao;

import com.springmvc.config.Msg;
import com.springmvc.dataservice.FutureInfoDataService;
import com.springmvc.entities.AutdInfoEntity;
import com.springmvc.entities.StaticFutureInfoEntity;
import com.springmvc.vo.StaticFutureInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by wzh on 16/8/16.
 */

@Service
public class FutureInfoDao implements FutureInfoDataService {
    @Resource
    private BaseDao baseDao ;


    public Msg saveAUTDInfo(AutdInfoEntity entity) {
        System.out.println(entity.getPrice());
        return baseDao.save(entity);
    }
}
