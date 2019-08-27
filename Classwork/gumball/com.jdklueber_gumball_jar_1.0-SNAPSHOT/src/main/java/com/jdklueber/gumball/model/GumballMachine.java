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
        
    public int state = READY_FOR_COIN;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
    
    public GumballMachine() {
    }
    
    /* 
    *  Should return READY_FOR_COIN if the machine is ready for a coin
    *  Should return HAS_COIN if the machine has had a coin added
    */
    
}
