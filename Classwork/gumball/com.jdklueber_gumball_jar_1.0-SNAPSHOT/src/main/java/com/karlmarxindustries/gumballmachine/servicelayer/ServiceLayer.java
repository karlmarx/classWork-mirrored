/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karlmarxindustries.gumballmachine.servicelayer;

import com.jdklueber.gumball.model.GumballMachine;
import static com.jdklueber.gumball.model.GumballMachine.FAILURE;
import static com.jdklueber.gumball.model.GumballMachine.HAS_COIN;
import static com.jdklueber.gumball.model.GumballMachine.READY_FOR_COIN;
import static com.jdklueber.gumball.model.GumballMachine.SUCCESS;
import com.karlmarxindustries.dvdlibrary.dto.DVD;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author karlmarx
 */
public class ServiceLayer  {
    GumballMachine machine = new GumballMachine();


   public int getState() {
        return machine.state;
    }
    
    /*
    *  Should return SUCCESS if the machine is ready for a coin
    *  Should return FAILURE otherwise
    */
    public int addCoin() {
        if (machine.state == READY_FOR_COIN) {
            machine.setState( HAS_COIN);
            return SUCCESS;
        } 

        return FAILURE;
    }
    
    /* Should print "Have a gumball!" and return SUCCESS if the machine has a coin
    *  Should return FAILURE otherwise
    */
    public int turnHandle() {
        if (machine.state == HAS_COIN) {
            machine.setState(READY_FOR_COIN);
            return SUCCESS;
        } 

        return FAILURE;
    }
}
