/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karlmarxindustries.vending.service;

import com.karlmarxindustries.vending.dao.AuditDaoImpl;
import com.karlmarxindustries.vending.dao.VendingDaoFileImpl;
import com.karlmarxindustries.vending.dto.Snack;
import com.karlmarxindustries.vending.exception.FilePersistenceException;
import com.karlmarxindustries.vending.exception.InsufficientFundsException;
import com.karlmarxindustries.vending.exception.ItemSoldOutException;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author karlmarx
 */
public class ServiceLayerImplTest {
    
    ServiceLayerImpl testServiceLayer;
    AuditDaoImpl auditDao ;
    VendingDaoFileImpl dao;
        
    
    public ServiceLayerImplTest () {
        testServiceLayer = new ServiceLayerImpl(dao, auditDao);
    }

    @Test
    public void testSomeMethod() {
    }
    @Test 
    public void testGetAllSnacksInStock() throws FilePersistenceException {
        List<Snack> allSnacks = testServiceLayer.getAllSnacksInStock();
       
        List <Snack> newSnacks = allSnacks;
        newSnacks.get(0).setQuantity(0);
        List <Snack> gottenNewSnacks = testServiceLayer.getAllSnacksInStock();
        Assertions.assertEquals(allSnacks.size() - 1, gottenNewSnacks.size(), "Should have one less object after setting quantity to zero");
    }
    @Test
    public void testGetOneItem() throws FilePersistenceException {
        Snack snackTest = new Snack();
        String keyToTest = "anything";
        dao.snacks.put(keyToTest, snackTest);
        Snack snackFromMethod = testServiceLayer.getOneItem(keyToTest);
        
        Assertions.assertEquals(snackTest, snackFromMethod, "The result from get one item should be the same as what is the value to the key parameter");
    }

    @Test
    public void testPurchaseItemQuantity() throws InsufficientFundsException, FilePersistenceException, ItemSoldOutException {
        testServiceLayer.loadInventory();
        int quantityBeforeSale = testServiceLayer.getOneItem("A1").getQuantity();
        testServiceLayer.purchaseItem("A1", new BigDecimal("59.99"));
        int quantityAfterSale = testServiceLayer.getOneItem("A1").getQuantity();
        
        Assertions.assertEquals(quantityBeforeSale -1, quantityAfterSale, "Quantity should be 1 less after sale.");
    }
    @Test
    public void testPurchaseItemQuantityZero() throws InsufficientFundsException, FilePersistenceException, ItemSoldOutException {
        boolean itemSoldOutExceptionThrown = false;
        testServiceLayer.loadInventory();
        testServiceLayer.getOneItem("A1").setQuantity(0);
        try {
            testServiceLayer.purchaseItem("A1", new BigDecimal("59.99"));
        } catch (ItemSoldOutException e ){
            itemSoldOutExceptionThrown = true; 
        }       
        int quantityAfterSale = testServiceLayer.getOneItem("A1").getQuantity();
        Assertions.assertEquals(0, quantityAfterSale, "Quantity should remain 0 after sale.");
        Assertions.assertEquals(true, itemSoldOutExceptionThrown, "The Item sold out exception should be thrown when quantity of item is 0.");
    }
    @Test
    public void testAddToMoney() throws FilePersistenceException {
        BigDecimal beginningBalance = testServiceLayer.getBalance();
        testServiceLayer.addToMoneyInside(new BigDecimal("1.00"));
        BigDecimal endBalance = testServiceLayer.getBalance();
        int scaleOfEndBalance = endBalance.scale();
        Assertions.assertEquals(endBalance.subtract(beginningBalance), new BigDecimal("1.00"), "difference b/w beginning and end balance should be $1.00"); 
        Assertions.assertEquals(2,scaleOfEndBalance, "scale should be 2");
    }
}
