/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karlmarxindustries.vending.service;

import com.karlmarxindustries.vending.dao.VendingDao;
import com.karlmarxindustries.vending.dto.Change;
import com.karlmarxindustries.vending.dto.ChangeAndOutcome;
import com.karlmarxindustries.vending.dto.CoinPurse;
import com.karlmarxindustries.vending.dto.Snack;
import com.karlmarxindustries.vending.exception.FilePersistenceException;
import com.karlmarxindustries.vending.exception.InsufficientFundsException;
import com.karlmarxindustries.vending.exception.ItemSoldOutException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author karlmarx
 */
public class ServiceLayerImpl implements ServiceLayer {
    
    VendingDao dao;
    CoinPurse coinPurse = new CoinPurse(convertToBigAndScale("0.00"));
 
    Change change = new Change(convertToBigAndScale("0.00"));
    ChangeAndOutcome changeAndOutcome = new ChangeAndOutcome(change);

    public ServiceLayerImpl(VendingDao vendingDao) {
        this.dao = vendingDao;
    }
    @Override
    public void loadInventory() throws FilePersistenceException {
        dao.loadInventory();
   }

    @Override
    public List<Snack> getAllSnacksInStock() throws FilePersistenceException {
        List<Snack> allSnacks = dao.getAllSnacks();
        List<Snack> toRemove = new ArrayList();
        for (Snack item : allSnacks) {
            if (item.getQuantity() == 0) {
                toRemove.add(item);
            }
        }
        allSnacks.removeAll(toRemove);
        return allSnacks;
    }

    @Override
    public Snack getOneItem(String vendingSlot) throws FilePersistenceException {
        return dao.getSnack(vendingSlot);
        //this is in dao, does it need to move here?
    }

    @Override
    public ChangeAndOutcome purchaseItem(String vendingSlot, BigDecimal snackPrice) throws InsufficientFundsException, ItemSoldOutException, FilePersistenceException {
        //use if statement to make sure it's enough or do that in contoller with exception
        boolean didItSucceed = false;
        int numQuarters = 0;
        int numDimes = 0;
        int numNickels = 0;
        int numPennies = 0;
        int priceInCents = snackPrice.multiply(new BigDecimal("100")).intValue();
        int balanceInCents = changeAndOutcome.change.getMoneyInside().multiply(new BigDecimal("100")).intValue();
        int changeInCents = balanceInCents - priceInCents;
        if (changeInCents < 0) {
            throw new InsufficientFundsException("InsufficientFunds"); ///add loop to do the other stuff
        } else {
            didItSucceed = true;
        }
        if (changeInCents >= 25) {//need to scale this? 
            numQuarters = changeInCents / 25;
            changeInCents = changeInCents % 25;
        }   
        if (changeInCents >= 10) {//need to scale this? 
            numDimes = changeInCents / 10;
            changeInCents = changeInCents % 10;
        }   
        if (changeInCents >= 5) {//need to scale this? 
            numNickels = changeInCents / 5;
            changeInCents = changeInCents % 5;
        }   
        if (changeInCents >= 1) {//need to scale this? 
            numPennies = changeInCents / 1;
            changeInCents = changeInCents % 1;
        }   
        
        changeAndOutcome.change.setNumPennies(numPennies);
        changeAndOutcome.change.setNumNickels(numNickels);
        changeAndOutcome.change.setNumDimes(numDimes);
        changeAndOutcome.change.setNumQuarters(numQuarters);
        changeAndOutcome.setOutcome(didItSucceed);
         
         return changeAndOutcome;
    }

   
    public static BigDecimal convertToBigAndScale(String toConvert) {
        BigDecimal converted = new BigDecimal(toConvert);
        BigDecimal scaledBigDecimal = converted.setScale(2, RoundingMode.HALF_UP);
        return scaledBigDecimal;
    }
    @Override
    public void updateMoneyInside(BigDecimal moneyIn) {
        changeAndOutcome.change.setMoneyInside(moneyIn.add(changeAndOutcome.change.getMoneyInside()));
        
    }
//     public BigDecimal getBalance() {
//         return coinPurse.getMoneyInside();
//    }

    @Override
    public BigDecimal deductPriceFromBalance(BigDecimal moneyOut) {
        coinPurse.setMoneyInside(changeAndOutcome.change.getMoneyInside().subtract(moneyOut));
        return coinPurse.getMoneyInside();
    }

    @Override
    public void writeInventory(List<Snack> allSnacks) throws FilePersistenceException {
        dao.writeInventory(getAllSnacksInStock());
    }

    @Override
    public BigDecimal checkCurrentBalance() {
        return changeAndOutcome.change.getMoneyInside();
    }

    @Override
    public BigDecimal getBalance() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
