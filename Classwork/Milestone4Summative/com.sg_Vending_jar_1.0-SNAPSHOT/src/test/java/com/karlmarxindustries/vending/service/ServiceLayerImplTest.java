/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karlmarxindustries.vending.service;

import com.karlmarxindustries.vending.dao.VendingAuditDaoImpl;
import com.karlmarxindustries.vending.dao.VendingDao;
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
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author karlmarx
 */

public class ServiceLayerImplTest {
    private VendingServiceLayer service;
    private VendingDao dao;
     
    public ServiceLayerImplTest() {
        ApplicationContext ctx = 
            new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("serviceLayer", VendingServiceLayer.class);

    }
    @Test
    public void testSomeMethod() {
    }
    @Test 
    public void testGetAllSnacksInStock() throws FilePersistenceException {
        List<Snack> allSnacks = service.getAllSnacksInStock();
       
        List <Snack> newSnacks = allSnacks;
        newSnacks.get(0).setQuantity(0);
        List <Snack> gottenNewSnacks = service.getAllSnacksInStock();
        Assertions.assertEquals(allSnacks.size() - 1, gottenNewSnacks.size(), "Should have one less object after setting quantity to zero");
    }
    @Test
    public void testGetOneItem() throws FilePersistenceException {
        Snack snackTest = new Snack();
        String keyToTest = "anything";
      List<Snack>  blah = dao.getAllSnacks();
        Snack snackFromMethod = service.getOneItem(keyToTest);
        
        Assertions.assertEquals(snackTest, snackFromMethod, "The result from get one item should be the same as what is the value to the key parameter");
    }

    @Test
    public void testPurchaseItemQuantity() throws InsufficientFundsException, FilePersistenceException, ItemSoldOutException {
        service.loadInventory();
        int quantityBeforeSale = service.getOneItem("A1").getQuantity();
        service.purchaseItem("A1", new BigDecimal("59.99"));
        int quantityAfterSale = service.getOneItem("A1").getQuantity();
        
        Assertions.assertEquals(quantityBeforeSale -1, quantityAfterSale, "Quantity should be 1 less after sale.");
    }
    @Test
    public void testPurchaseItemQuantityZero() throws InsufficientFundsException, FilePersistenceException, ItemSoldOutException {
        boolean itemSoldOutExceptionThrown = false;
        service.loadInventory();
        service.getOneItem("A1").setQuantity(0);
        try {
            service.purchaseItem("A1", new BigDecimal("59.99"));
        } catch (ItemSoldOutException e ){
            itemSoldOutExceptionThrown = true; 
        }       
        int quantityAfterSale = service.getOneItem("A1").getQuantity();
        Assertions.assertEquals(0, quantityAfterSale, "Quantity should remain 0 after sale.");
        Assertions.assertEquals(true, itemSoldOutExceptionThrown, "The Item sold out exception should be thrown when quantity of item is 0.");
    }
    @Test
    public void testAddToMoney() throws FilePersistenceException {
        BigDecimal beginningBalance = service.checkCurrentBalance();
        service.addToMoneyInside(new BigDecimal("1.00"));
        BigDecimal endBalance = service.checkCurrentBalance();
        int scaleOfEndBalance = endBalance.scale();
        Assertions.assertEquals(endBalance.subtract(beginningBalance), new BigDecimal("1.00"), "difference b/w beginning and end balance should be $1.00"); 
        Assertions.assertEquals(2,scaleOfEndBalance, "scale should be 2");
    }
}
