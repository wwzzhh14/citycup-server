package com.springmvc.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by wzh on 16/9/9.
 */

@Entity
@Table(name = "tb_autdminprice")
public class AutdInfoEntity {

    @Id
    private Date date;
    private double price;

    public AutdInfoEntity() {
    }

    public AutdInfoEntity(Date date, double price) {
        this.date = date;
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
