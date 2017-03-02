package com.springmvc.thread;

import com.springmvc.dataservice.TransacDataService;
import com.springmvc.dataservice.UserDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by guhan on 16/9/15.
 */
@Service
public class YieldUpdater extends Thread {
    @Resource
    UserDataService userDataService;
    @Resource
    TransacDataService transacDataService;

    public void run(){

    }
}
