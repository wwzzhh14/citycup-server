package com.springmvc.dataservice;

import com.springmvc.config.Msg;
import com.springmvc.entities.AutdInfoEntity;
import com.springmvc.entities.StaticFutureInfoEntity;


/**
 * Created by wzh on 16/8/16.
 */
public interface FutureInfoDataService {

    public Msg saveAUTDInfo(AutdInfoEntity entity);
}
