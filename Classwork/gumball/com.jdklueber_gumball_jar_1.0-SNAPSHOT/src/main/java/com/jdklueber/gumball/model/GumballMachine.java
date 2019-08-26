/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdklueber.gumball.model;

/**
 *
 * @author jason
 */
public class GumballMachine {
    public static final int READY_FOR_COIN = 0;
    public static final int HAS_COIN = 1;
    
    public static final int SUCCESS = 1;
    public static final int FAILURE = 0;
    boolean coinInside = false;
    boolean readyForCoin = !coinInside;
    public GumballMachine() {
        
    }

    public boolean isCoinInside() {
        return coinInside;
    }

    public void setCoinInside(boolean coinInside) {
        this.coinInside = coinInside;
    }

    public boolean isReadyForCoin() {
        return readyForCoin;
    }

    public void setReadyForCoin(boolean readyForCoin) {
        this.readyForCoin = readyForCoin;
    }

    
    /* 
    *  Should return READY_FOR_COIN if the machine is ready for a coin
    *  Should return HAS_COIN if the machine has had a coin added
    */
    public int getState() {
        int stateCheck = -1;
        if (readyForCoin) {
            stateCheck = READY_FOR_COIN;
        } 
        if (coinInside){
            stateCheck = HAS_COIN; 
        }
        return stateCheck;
    }
    
    /*
    *  Should return SUCCESS if the machine is ready for a coin
    *  Should return FAILURE otherwise
    */
    public int addCoin() {
        int coinAddable = -1;
        if (readyForCoin) {
            coinAddable = SUCCESS;
            coinInside = true;
            readyForCoin = false;
        } else {
            coinAddable = FAILURE;
        }
        return coinAddable;
    }
    
    /* Should print "Have a gumball!" and return SUCCESS if the machine has a coin
    *  Should return FAILURE otherwise
    */
    public int turnHandle() {
        int didItSucceed = -1;
       
        if (coinInside){
            System.out.println("Have a gumball");
            coinInside = false;
            readyForCoin = true;
            didItSucceed = 1;
        } else {
            didItSucceed = 0;
        }
        return didItSucceed;
    }
}
