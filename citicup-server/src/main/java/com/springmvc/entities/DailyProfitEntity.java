package com.springmvc.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by guhan on 16/9/15.
 */

@Entity
@Table(name = "tb_dailyprofits")
public class DailyProfitEntity {
    @Id
    String date;
    double yieldRatio;

    public DailyProfitEntity(String date, double yieldRatio){
        this.date = date;
        this.yieldRatio = yieldRatio;
    }

    public DailyProfitEntity() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getYieldRatio() {
        return yieldRatio;
    }

    public void setYieldRatio(double yieldRatio) {
        this.yieldRatio = yieldRatio;
    }

}
