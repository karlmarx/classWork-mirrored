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
    
    enum Coins {
        PENNY, NICKEL, DIME, QUARTER
    }   
    int numPennies;
    int numNickels;
    int numDimes;
    int numQuarters;
    BigDecimal moneyInside;
//    BigDecimal moneyInside = new BigDecimal("0"); //is this right?
 //   BigDecimal moneyInsideScale = moneyInside.setScale(2, RoundingMode.HALF_UP);

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

//    public BigDecimal getMoneyInsideScale() {
//        return moneyInsideScale;
//    }
//
//    public void setMoneyInsideScale(BigDecimal moneyInsideScale) {
//        this.moneyInsideScale = moneyInsideScale;
//    }
    
    
}
