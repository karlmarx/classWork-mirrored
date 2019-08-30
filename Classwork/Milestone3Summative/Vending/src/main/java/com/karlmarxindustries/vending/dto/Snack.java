/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karlmarxindustries.vending.dto;

import com.karlmarxindustries.vending.dto.EnumValues.Slot;
import java.math.BigDecimal;

/**
 *
 * @author karlmarx
 */
public class Snack {
    private String slot;
    private String name;
    private BigDecimal price; //MMDDYYYY format
    private int quantity;

    //is this the right place for Slot enum

    
    public Snack(String slot, String name, BigDecimal price, int quantity) {
        this.slot = slot;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Snack() {
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
}
