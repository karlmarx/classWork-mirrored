/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karlmarxindustries.vending.dao;

import com.karlmarxindustries.vending.dto.Snack;
import com.karlmarxindustries.vending.exception.FilePersistenceException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author karlmarx
 */
public class VendingDaoEmptyStubImpl implements VendingDao {
        private Snack firstSnack;
        private Snack secondSnack;
        private Map<String, Snack> snacks = new HashMap<>();
        


    public VendingDaoEmptyStubImpl(Snack firstSnack, Snack secondSnack) {
        this.firstSnack = firstSnack;
        this.secondSnack = secondSnack;
    }

    public VendingDaoEmptyStubImpl() {
    }

    public Snack getFirstSnack() {
        return firstSnack;
    }

    public void setFirstSnack(Snack firstSnack) {
        this.firstSnack = firstSnack;
    }

    public Snack getSecondSnack() {
        return secondSnack;
    }

    public void setSecondSnack(Snack secondSnack) {
        this.secondSnack = secondSnack;
    }

    public Map<String, Snack> getSnacks() {
        return snacks;
    }

    public void setSnacks(Map<String, Snack> snacks) {
        this.snacks = snacks;
    }

    @Override
    public List<Snack> getAllSnacks() throws FilePersistenceException {
        List<Snack> bothSnacks = new ArrayList<>();
        bothSnacks.add(firstSnack);
        bothSnacks.add(secondSnack);
        return bothSnacks;
    }

    @Override
    public Snack getSnack(String slot) throws FilePersistenceException {
        if (slot.equals("T1")) return firstSnack;
        if (slot.equals("T2")) return secondSnack;
        else return null;
    }


    @Override
    public void loadInventory() throws FilePersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    @Override
    public void writeInventory(List<Snack> snackList) throws FilePersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   


    
}
