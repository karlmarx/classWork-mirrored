/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karlmarxindustries.vending.dto;

import java.math.BigDecimal;

/**
 *
 * @author karlmarx
 */
public class CoinPurse {

    public CoinPurse(BigDecimal moneyInside) {
        this.moneyInside = moneyInside;
    }

    public BigDecimal getMoneyInside() {
        return moneyInside;
    }

    public void setMoneyInside(BigDecimal moneyInside) {
        this.moneyInside = moneyInside;
    }
    private BigDecimal moneyInside;
    
}
