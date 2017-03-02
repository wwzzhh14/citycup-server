package com.springmvc.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by wzh on 16/8/13.
 */

@Entity
@Table(name = "tb_staticfutureinfo")
public class StaticFutureInfoEntity {


    @Id
    private String code;
    private String name;

    //交割月份
    private String deliveryMonth;
    //上市交易所
    private String place;
    //最低保证金比率
    private String minMarginRatio;

    public StaticFutureInfoEntity() {
    }

    public StaticFutureInfoEntity(String name, String code, String deliveryMonth, String place, String minMarginRatio) {
        this.name = name;
        this.code = code;
        this.deliveryMonth = deliveryMonth;
        this.place = place;
        this.minMarginRatio = minMarginRatio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDeliveryMonth() {
        return deliveryMonth;
    }

    public void setDeliveryMonth(String deliveryMonth) {
        this.deliveryMonth = deliveryMonth;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getMinMarginRatio() {
        return minMarginRatio;
    }

    public void setMinMarginRatio(String minMarginRatio) {
        this.minMarginRatio = minMarginRatio;
    }
}
