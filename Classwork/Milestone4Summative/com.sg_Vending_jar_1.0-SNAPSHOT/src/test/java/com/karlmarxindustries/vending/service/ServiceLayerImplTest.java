/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karlmarxindustries.vending.service;

import com.karlmarxindustries.vending.dao.VendingDaoStubImpl;
import com.karlmarxindustries.vending.dto.Snack;
import com.karlmarxindustries.vending.exception.FilePersistenceException;
import com.karlmarxindustries.vending.exception.InsufficientFundsException;
import com.karlmarxindustries.vending.exception.ItemSoldOutException;
import java.math.BigDecimal;
import java.util.ArrayList;
import static java.util.Collections.emptyList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author karlmarx
 */

public class ServiceLayerImplTest {
    private VendingServiceLayer service;
    private VendingDaoStubImpl dao; //is this okay to use? instead of VendingDao data type
    Snack testSnackOne = new Snack("T1", "More Generic", new BigDecimal("2.00"), 0); 
    Snack testSnackTwo = new Snack("T2", "Most Generic", new BigDecimal("3.00"), 1);
    
     
    public ServiceLayerImplTest() {
//        testSnackOne = new Snack("T2", "More Generic", new BigDecimal("2.00"), 2); 
//        testSnackOne = new Snack("T3", "Most Generic", new BigDecimal("3.00"), 4);

        ApplicationContext ctx = 
            new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("serviceLayer", VendingServiceLayer.class);
       

    }
    @Test
    public void testSomeMethod() {
    }
    @Test 
    public void testGetAllSnacksInStock() throws FilePersistenceException {
        Map<String, Snack> mapToSet = new HashMap<>();
        mapToSet.put("T1", testSnackOne);
        mapToSet.put("T2", testSnackTwo);
        dao.setSnacks(mapToSet);
        //where to put the above ??
        List<Snack> noSnacksYet = service.getAllSnacksInStock();
        dao.setFirstSnack(testSnackOne);
        List<Snack> allSnacksFirstOnly = service.getAllSnacksInStock();
        dao.setFirstSnack(testSnackTwo);
        List<Snack> allSnacksInStock = service.getAllSnacksInStock();
        List<Snack> arrayOf2Snacks = new ArrayList<>();
        
        Assertions.assertEquals(0, noSnacksYet.size(), "There should not be any snacks yet.");
        Assertions.assertEquals(0, allSnacksFirstOnly.size(), "There should be 0 snacks in the array list as quantity is 0.");
        Assertions.assertEquals (1, allSnacksInStock.size(), "There should be one snack in array as only one is in stock.");
        Assertions.assertEquals(testSnackOne, allSnacksFirstOnly.get(0), "this should be the only snack in array.");
        Assertions.assertFalse(allSnacksFirstOnly.contains(testSnackOne), "This array should not contain this snack as qty is 0.");
        Assertions.assertFalse(allSnacksFirstOnly.contains(testSnackTwo), "This array should not contain this yet.");  
        Assertions.assertFalse(allSnacksFirstOnly.contains(testSnackOne), "This array should contain this snack as qty is 0.");
        Assertions.assertTrue(allSnacksInStock.contains(testSnackTwo), "This array should now contain this snack .");          
    }
    @Test
    public void testGetOneItem() throws FilePersistenceException {
        dao.setFirstSnack(testSnackOne);
        dao.setSecondSnack(testSnackTwo);
        String keyToTest = "anything";
        List<Snack>  blah = dao.getAllSnacks();
        Snack shouldBeOne = service.getOneItem("T1");
        Snack shouldBeTwo = service.getOneItem("T2");
        Snack shouldBeNull = service.getOneItem("T3");


        Assertions.assertEquals(testSnackOne, shouldBeOne, "this should return this snack based on slot T1");
        Assertions.assertEquals(testSnackTwo, shouldBeTwo, "this should return this snack based on slot T2");
        Assertions.assertNull(shouldBeNull, "this should be null as slot is unused T3"); //are these two verbose

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
