package com.springmvc.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by wzh on 16/9/14.
 */
@Entity
@Table(name = "tb_investment")
public class InvestmentEntity {
    @Id
    private int id;
    private double investment;

    public InvestmentEntity() {
    }

    public InvestmentEntity(double investment) {
        this.investment = investment;
    }

    public double getInvestment() {
        return investment;
    }

    public void setInvestment(double investment) {
        this.investment = investment;
    }
}
