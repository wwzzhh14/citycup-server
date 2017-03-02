package com.springmvc.blservice;

import com.springmvc.config.CombiType;
import com.springmvc.vo.AlarmVO;
import com.springmvc.vo.ResultMessageVO;

import java.util.Date;
import java.util.List;

/**
 * Created by guhan on 16/8/30.
 */
public interface MonitorBLService {
    //用户定时发送请求, 查看是否达到预警值
    public ResultMessageVO alarm(String account, String userName, CombiType type);

    public List<AlarmVO> getAlarmHistory(String userName);

    public List<AlarmVO> getUnread(String userName);

    public ResultMessageVO markRead(String userName);
}
