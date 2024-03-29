/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karlmarxindustries.vending.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author karlmarx
 */
public class Change {
    
    int numPennies;
    int numNickels;
    int numDimes;
    int numQuarters;
    BigDecimal moneyInside;
    
    public Change(BigDecimal moneyInside) {
        this.moneyInside = moneyInside;
    }
    public Change(int numPennies, int numNickels, int numDimes, int numQuarters) {
        this.numPennies = numPennies;
        this.numNickels = numNickels;
        this.numDimes = numDimes;
        this.numQuarters = numQuarters;
    }
    

    public Change() {
    }

    public int getNumPennies() {
        return numPennies;
    }

    public void setNumPennies(int numPennies) {
        this.numPennies = numPennies;
    }

    public int getNumNickels() {
        return numNickels;
    }

    public void setNumNickels(int numNickels) {
        this.numNickels = numNickels;
    }

    public int getNumDimes() {
        return numDimes;
    }

    public void setNumDimes(int numDimes) {
        this.numDimes = numDimes;
    }

    public int getNumQuarters() {
        return numQuarters;
    }

    public void setNumQuarters(int numQuarters) {
        this.numQuarters = numQuarters;
    }

    public BigDecimal getMoneyInside() {
        return moneyInside;
    }

    public void setMoneyInside(BigDecimal moneyInside) {
        this.moneyInside = moneyInside;
    }  
}
