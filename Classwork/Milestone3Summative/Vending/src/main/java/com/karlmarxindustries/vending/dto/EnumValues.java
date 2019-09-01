/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karlmarxindustries.vending.dto;

/**
 *
 * @author karlmarx
 */
public enum EnumValues {
        PENNY(1), NICKEL(5), DIME(10), QUARTER(25);
        private int value;
        
        private EnumValues(int value) {
            this.value = value;
        }
    }   
