package com.springmvc.bl.info;

import com.springmvc.vo.FutureInfoVO;

import java.util.List;

/**
 * Created by guhan on 16/9/9.
 */
public interface HorizontalService {
    public FutureInfoVO getFutureByCode(String code);

    public List<Double> getPreMin(String code, int backward);

}
