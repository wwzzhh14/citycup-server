package com.springmvc.controller;

import com.springmvc.blservice.MonitorBLService;
import com.springmvc.config.CombiType;
import com.springmvc.vo.AlarmVO;
import com.springmvc.vo.ResultMessageVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by guhan on 16/9/13.
 */
@Controller
public class MonitorController {
    @Resource
    MonitorBLService monitorBLService;

    @RequestMapping(value = "/monitor/alarm", method = RequestMethod.GET)
    @ResponseBody
    public ResultMessageVO alarm(String account, String userName, CombiType type){
        return monitorBLService.alarm(account, userName, type);
    }

    @RequestMapping(value = "/monitor/alarmHistory", method = RequestMethod.GET)
    @ResponseBody
    public List<AlarmVO> getAlarmHistory(String userName){
        return monitorBLService.getAlarmHistory(userName);
    }

    @RequestMapping(value = "/monitor/unread", method = RequestMethod.GET)
    @ResponseBody
    public List<AlarmVO> getUnread(String userName){
        return monitorBLService.getUnread(userName);
    }

    @RequestMapping(value = "/monitor/markRead", method = RequestMethod.POST)
    @ResponseBody
    public ResultMessageVO markRead(String userName){
        return monitorBLService.markRead(userName);
    }
}
