package com.springmvc.bl;

import com.springmvc.blservice.ChartInfoBLService;
import com.springmvc.config.UrlConstant;
import com.springmvc.utils.NetUtil;
import org.springframework.stereotype.Service;

/**
 * Created by wzh on 16/9/10.
 */

@Service
public class ChartInfoImp implements ChartInfoBLService {

    public String getKLineData(String code) {
        if (code.equals("AU1612")||code.equals("AU1706"))
            return NetUtil.httpGet(UrlConstant.SHFE_DAILYKLINE_URL+code);
        if (code.equals("GC"))
            return NetUtil.httpGet(UrlConstant.CMX_DAILYKLINE_URL+code);
        return "";
    }

    public String getMInLineData(String code) {
        if (code.equals("AU1612")||code.equals("AU1706"))
            return NetUtil.httpGet(UrlConstant.SHFE_MINLINE_URL+code);
        if (code.equals("GC"))
            return NetUtil.httpGet(UrlConstant.CMX_MINLINE_URL+code);
        return "";
    }
}
