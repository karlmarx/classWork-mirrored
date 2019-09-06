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
public class VendingDaoStubImpl implements VendingDao {
        private Snack firstSnack;
        private Snack secondSnack;
        private Map<String, Snack> snacks = new HashMap<>();
        
        

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.firstSnack);
        hash = 23 * hash + Objects.hashCode(this.secondSnack);
        hash = 23 * hash + Objects.hashCode(this.snacks);
        return hash;
    }

    @Override
    public String toString() {
        return "VendingDaoStubImpl{" + "firstSnack=" + firstSnack + ", secondSnack=" + secondSnack + ", snacks=" + snacks + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VendingDaoStubImpl other = (VendingDaoStubImpl) obj;
        if (!Objects.equals(this.firstSnack, other.firstSnack)) {
            return false;
        }
        if (!Objects.equals(this.secondSnack, other.secondSnack)) {
            return false;
        }
        if (!Objects.equals(this.snacks, other.snacks)) {
            return false;
        }
        return true;
    }

    public VendingDaoStubImpl(Snack firstSnack, Snack secondSnack) {
        this.firstSnack = firstSnack;
        this.secondSnack = secondSnack;
      //  this.snacks = new HashMap<>(); //is this ok?
   
    }

    public VendingDaoStubImpl() {
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

    


    
}
