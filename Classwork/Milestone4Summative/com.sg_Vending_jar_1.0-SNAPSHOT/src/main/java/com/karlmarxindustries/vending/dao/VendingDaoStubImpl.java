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

/**
 *
 * @author karlmarx
 */
public class VendingDaoStubImpl implements VendingDao {
        private Snack onlySnack;
        private Map<String, Snack> snacks = new HashMap<>();

    public VendingDaoStubImpl() {
        onlySnack = new Snack("T1", "Generic", new BigDecimal("1.00"), 10);
    }


    public Snack getOnlySnack() {
        return onlySnack;
    }

    public void setOnlySnack(Snack onlySnack) {
        this.onlySnack = onlySnack;
    }

    

    public void setSnacks(Map<String, Snack> snacks) {
        this.snacks = snacks;
    }

    public VendingDaoStubImpl(Snack onlySnack) {
        onlySnack = new Snack("T1", "Name", new BigDecimal("1.50"), 10);
        snacks.put(onlySnack.getSlot(),onlySnack);
    }
    
    
    
    @Override
    public List<Snack> getAllSnacks() throws FilePersistenceException {
        return new ArrayList<Snack>(snacks.values());
    }

    @Override
    public Snack getSnack(String slot) throws FilePersistenceException {
        if (slot.equals(onlySnack.getSlot())) {
            return onlySnack;
        } else {
            return null;
        }
    }

    @Override
    public void updateMoneyInside(BigDecimal moneyIn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateQuantity(String slot, Snack snack) throws FilePersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadInventory(String libraryFile) throws FilePersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getProductionFile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getTestFile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeInventory(List<Snack> snackList, String libraryFile) throws FilePersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void print(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double readDouble(String prompt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float readFloat(String prompt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int readInt(String prompt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long readLong(String prompt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String readString(String prompt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, Snack> getSnacks() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
//    public Snack unmarshallSnack(String inventoryAsText) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public Snack marshallSnack(String inventoryAsText) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }


    
}
