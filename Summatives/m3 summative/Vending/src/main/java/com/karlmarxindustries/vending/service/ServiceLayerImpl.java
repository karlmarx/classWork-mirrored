/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karlmarxindustries.vending.service;

import com.karlmarxindustries.vending.dao.AuditDao;
import com.karlmarxindustries.vending.dao.VendingDao;
import com.karlmarxindustries.vending.dto.Change;
import com.karlmarxindustries.vending.dto.ChangeAndOutcome;
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
    
    private VendingDao dao;
    private AuditDao auditDao;
    Change change = new Change(convertToBigAndScale("0.00"));
    ChangeAndOutcome changeAndOutcome = new ChangeAndOutcome(change);
    public ServiceLayerImpl(VendingDao vendingDao, AuditDao auditDao) {
        this.dao = vendingDao;
        this.auditDao = auditDao;
    }
    @Override
    public void loadInventory() throws FilePersistenceException {
        try { 
                dao.loadInventory(dao.getProductionFile());
        } catch (FilePersistenceException e) {
                auditDao.writeAuditEntry("The inventory could not be loaded due to an exception in File Persistence."); 
                return;
        }
        List<Snack> allSnacks = dao.getAllSnacks();
        StringBuilder str = new StringBuilder();
        str.append("Loaded Inventory: <Item=").append(allSnacks.get(0).getName()).append(" , Quantity=").append(allSnacks.get(0).getQuantity());
        str.append(">; <Item=").append(allSnacks.get(1).getName()).append(" , Quantity=").append(allSnacks.get(1).getQuantity());
        str.append(">; <Item=").append(allSnacks.get(2).getName()).append(" , Quantity=").append(allSnacks.get(2).getQuantity());
        str.append(">; <Item=").append(allSnacks.get(3).getName()).append(" , Quantity=").append(allSnacks.get(3).getQuantity());
        str.append(">; <Item=").append(allSnacks.get(4).getName()).append(" , Quantity=").append(allSnacks.get(4).getQuantity());
        str.append(">; <Item=").append(allSnacks.get(5).getName()).append(" , Quantity=").append(allSnacks.get(5).getQuantity()).append(">.");
        String auditLog = str.toString();
        auditDao.writeAuditEntry(auditLog);
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
    }
    @Override
    public ChangeAndOutcome purchaseItem(String vendingSlot, BigDecimal snackPrice) throws InsufficientFundsException, ItemSoldOutException, FilePersistenceException {
        boolean didItSucceed = false;
        int numQuarters = 0;
        int numDimes = 0;
        int numNickels = 0;
        int numPennies = 0;
        int priceInCents = snackPrice.multiply(new BigDecimal("100")).intValue();
        int balanceInCents = changeAndOutcome.getChange().getMoneyInside().multiply(new BigDecimal("100")).intValue();
        int changeInCents = balanceInCents - priceInCents;
        if (dao.getSnack(vendingSlot).getQuantity() < 1){
            auditDao.writeAuditEntry("Exception thrown: Item sold out. User attempted to purchase " + dao.getSnack(vendingSlot) + ", but the remaining quantity was zero.");
            throw new ItemSoldOutException("That item is sold out.  Please choose something else.");
        }
        else if (changeInCents < 0) {
            auditDao.writeAuditEntry("Exception thrown: Insufficient funds. User attempted to purchase " + dao.getSnack(vendingSlot).getName() + "(Price: $" + dao.getSnack(vendingSlot).getPrice() + "), but they only had $" + checkCurrentBalance() +".");
            throw new InsufficientFundsException("Insufficient Funds.  Please add more money, and try again."); ///add loop to do the other stuff
        } else {
            didItSucceed = true;
            dao.getSnack(vendingSlot).setQuantity(dao.getSnack(vendingSlot).getQuantity() - 1);
        }
        if (changeInCents >= 25) {
            numQuarters = changeInCents / 25;
            changeInCents = changeInCents % 25;
        }   
        if (changeInCents >= 10) {
            numDimes = changeInCents / 10;
            changeInCents = changeInCents % 10;
        }   
        if (changeInCents >= 5) {
            numNickels = changeInCents / 5;
            changeInCents = changeInCents % 5;
        }   
        if (changeInCents >= 1) {
            numPennies = changeInCents / 1;
            changeInCents = changeInCents % 1;
        }   
        changeAndOutcome.getChange().setNumPennies(numPennies);
        changeAndOutcome.getChange().setNumNickels(numNickels);
        changeAndOutcome.getChange().setNumDimes(numDimes);
        changeAndOutcome.getChange().setNumQuarters(numQuarters);
        changeAndOutcome.setOutcomeSuccess(didItSucceed);
         if (changeAndOutcome.getOutcomeSuccess()) {
             auditDao.writeAuditEntry(dao.getSnack(vendingSlot).getName() + " was purchased for $" + snackPrice + ".");
             auditDao.writeAuditEntry("The following amount of change was dispensed: $" + checkCurrentBalance() + "."); //convert to string?
         }
         return changeAndOutcome;
    }
    public static BigDecimal convertToBigAndScale(String toConvert) {
        BigDecimal converted = new BigDecimal(toConvert);
        BigDecimal scaledBigDecimal = converted.setScale(2, RoundingMode.HALF_UP);
        return scaledBigDecimal;
    }
    @Override
    public void updateMoneyInside(BigDecimal amount) throws FilePersistenceException {
        changeAndOutcome.getChange().setMoneyInside(amount);
    }
    @Override
    public BigDecimal deductPriceFromBalance(BigDecimal moneyOut) {
        changeAndOutcome.getChange().setMoneyInside(changeAndOutcome.getChange().getMoneyInside().subtract(moneyOut));
        return changeAndOutcome.getChange().getMoneyInside();
    }
    @Override
    public void writeInventoryOnExit(List<Snack> allSnacks) throws FilePersistenceException {
        try { 
                dao.writeInventory(getAllSnacksInStock(), dao.getProductionFile());
        } catch (FilePersistenceException e) {
                auditDao.writeAuditEntry("The inventory could not be written due to an exception in File Persistence."); 
                return;
        }
        auditDao.writeAuditEntry("User exited and inventory file was saved.");
    }
    @Override
    public BigDecimal checkCurrentBalance() {
        return changeAndOutcome.getChange().getMoneyInside();
    }
   
    @Override
    public void addToMoneyInside(BigDecimal moneyInputFromUser) throws FilePersistenceException {
        moneyInputFromUser.setScale(2, RoundingMode.HALF_UP);
        changeAndOutcome.getChange().setMoneyInside(moneyInputFromUser.add(changeAndOutcome.getChange().getMoneyInside()));
        auditDao.writeAuditEntry("The user inserted $" + String.valueOf(moneyInputFromUser) + " into the machine."); 
    }
}
