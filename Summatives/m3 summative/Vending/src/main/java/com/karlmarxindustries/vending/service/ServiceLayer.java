/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karlmarxindustries.vending.service;

import com.karlmarxindustries.vending.dto.Change;
import com.karlmarxindustries.vending.dto.ChangeAndOutcome;
import com.karlmarxindustries.vending.dto.Snack;
import com.karlmarxindustries.vending.exception.FilePersistenceException;
import com.karlmarxindustries.vending.exception.InsufficientFundsException;
import com.karlmarxindustries.vending.exception.ItemSoldOutException;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author karlmarx
 */
public interface ServiceLayer {
    public void updateMoneyInside(BigDecimal moneyIn) throws FilePersistenceException;
    public List<Snack> getAllSnacksInStock() throws FilePersistenceException;
    public Snack  getOneItem(String vendingSlot)  throws FilePersistenceException ;
    public ChangeAndOutcome purchaseItem(String vendingSlot, BigDecimal money)
        throws InsufficientFundsException, 
               ItemSoldOutException, 
               FilePersistenceException;
    public BigDecimal deductPriceFromBalance(BigDecimal moneyOut);
    public void loadInventory() throws FilePersistenceException;
    public void writeInventoryOnExit(List<Snack> allSnacks) throws FilePersistenceException;
    public BigDecimal checkCurrentBalance();
    public void addToMoneyInside(BigDecimal moneyInputFromUser) throws FilePersistenceException;
}
