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
public enum Coins {
        PENNY(new BigDecimal("0.01")), NICKEL(new BigDecimal("0.5")), DIME(new BigDecimal("0.10")), QUARTER(new BigDecimal("0.25"));
        public BigDecimal value;
        
        
        private Coins(BigDecimal value) {
            this.value = value;
        }
    }   //change input to allow coin insertion of each coin instead of dollars and cents
///// IDEA : type Q and enter to simulate adding each quarter