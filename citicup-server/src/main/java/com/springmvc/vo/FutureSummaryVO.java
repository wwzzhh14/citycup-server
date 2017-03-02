package com.springmvc.vo;

/**
 * Created by wzh on 16/8/13.
 */
public class FutureSummaryVO {

    private DynamicFutureInfo dynamicFutureInfo;
    private StaticFutureInfo staticFutureInfo;

    public FutureSummaryVO() {
    }

    public FutureSummaryVO(DynamicFutureInfo dynamicFutureInfo, StaticFutureInfo staticFutureInfo) {
        this.dynamicFutureInfo = dynamicFutureInfo;
        this.staticFutureInfo = staticFutureInfo;
    }

    public DynamicFutureInfo getDynamicFutureInfo() {
        return dynamicFutureInfo;
    }

    public void setDynamicFutureInfo(DynamicFutureInfo dynamicFutureInfo) {
        this.dynamicFutureInfo = dynamicFutureInfo;
    }

    public StaticFutureInfo getStaticFutureInfo() {
        return staticFutureInfo;
    }

    public void setStaticFutureInfo(StaticFutureInfo staticFutureInfo) {
        this.staticFutureInfo = staticFutureInfo;
    }
}
