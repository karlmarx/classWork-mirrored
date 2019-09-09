/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karlmarxindustries.vending.dao;

import com.karlmarxindustries.vending.dto.Snack;
import com.karlmarxindustries.vending.exception.FilePersistenceException;
import com.karlmarxindustries.vending.service.VendingServiceLayer;
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
public class VendingDaoFileImplTest {
    
    VendingDaoFileImpl testDao;
    
    public VendingDaoFileImplTest() {
        ApplicationContext ctx = 
        new ClassPathXmlApplicationContext("testApplicationContext.xml");
        testDao = ctx.getBean("vendingDao", VendingDaoFileImpl.class);
    
    }

    @Test
    public void testUnmarshallSnack() {
        String snackLine =  "T1::Name1::1.00::1";
        Snack fromLine = testDao.unmarshallSnack(snackLine);
        Assertions.assertEquals("T1", fromLine.getSlot(), "Slot should be T1");
        Assertions.assertEquals("Name1", fromLine.getName(), "Name should be Name1");
        Assertions.assertEquals(new BigDecimal("1.00"), fromLine.getPrice(), "Price should be 1.00");
        Assertions.assertEquals(1, fromLine.getQuantity(), "Quantity should be 1");
    }
    @Test
    public void testMarshallSnack() {
        Snack toTextify = new Snack("T2", "Name 2", new BigDecimal("1.02"), 2);
        String snackAsText = testDao.marshallSnack(toTextify);
        Assertions.assertEquals ("T2::Name 2::1.02::2", snackAsText, "Lines should match.");
    }
    @Test 
    public void testMarshallAndUnmarshall() {
        Snack toTextify = new Snack("T2", "Name 2", new BigDecimal("1.02"), 2);
        String snackAsText = testDao.marshallSnack(toTextify);
        Snack snackFromText = testDao.unmarshallSnack(snackAsText);
        Assertions.assertEquals(toTextify, snackFromText, "Input and output of marshalling/unmarshalling should be the same.");
    }
   
   
     @Test
    public void testGetAllSnacksWithInventory() throws FilePersistenceException {
        testDao.loadInventory(); //the inventory from file has exactly six items at all times.
        List<Snack> allSnacks = testDao.getAllSnacks();
        Assertions.assertEquals(5, allSnacks.size(), "There should be 5 snacks in array list as there are 5 snacks in test inventory file.");
        
    }
    @Test
     public void testGetAllSnacksWithoutInventory() throws FilePersistenceException {
        List<Snack> allSnacks = testDao.getAllSnacks();
        Assertions.assertEquals(0, allSnacks.size(), "There should be 0 snacks in array list as the inventory file was not loaded.");
    }
     
    @Test
    public void testGetSnack() throws FilePersistenceException { //updated to Tests 
        testDao.loadInventory(); //the Snack located at Slots A1 has name Veuve Clicqout & price 59.99.   quantity changes overtime.
        Snack gottenSnack = testDao.getSnack("T2");  
        Snack secondGottenSnack = testDao.getSnack("T4");
        Assertions.assertEquals("T2", gottenSnack.getSlot(), "Slot should be T2");
        Assertions.assertEquals(new BigDecimal("2.00"), gottenSnack.getPrice(), "Price should be 2.00");
        Assertions.assertEquals("Name2", gottenSnack.getName(), "Name should be Name2.");
        Assertions.assertEquals("T4", secondGottenSnack.getSlot(), "Slot should be T4");
        Assertions.assertEquals(new BigDecimal("4.00"), secondGottenSnack.getPrice(), "Price should be 4.00");
        Assertions.assertEquals("Name4", secondGottenSnack.getName(), "Name should be Name4.");
        Assertions.assertNotSame(gottenSnack, secondGottenSnack, "Getting at 2 slots should yield two different objects");
    }
//    @Test
//    public void testGetAllSnacks() throws FilePersistenceException {
//        testDao.loadInventory();
//        List<Snack> initialSnacks = testDao.getAllSnacks();
//        Snack snackToRemove = testDao.getSnack("A1");  
//        List<Snack> snacksToEdit = initialSnacks;
//        testDao.getSnacks().remove("A1"); //made public to work. is that ok??
//        List<Snack> snacksAfterRemoval = testDao.getAllSnacks();
//        Snack ungettableSnack = testDao.getSnack("A1");
//        Snack shouldStillBeAbleToGet = testDao.getSnack("A2");
//                
//        Assertions.assertNotEquals(snacksToEdit.size(), snacksAfterRemoval.size(), "Removing a snack should be reflected in size of list");
//        Assertions.assertEquals(snacksToEdit.size(), initialSnacks.size(), "Getting a snack should not add or remove any snacks");
//        Assertions.assertNull(ungettableSnack, "this snack has been removed, so getting should return null");
//        Assertions.assertNotNull(testDao.getSnack("A2"), "other snacks should not have been affected by gettting or getting all");
//    }
    
    @Test
    public void testGetSnackInvalidSlot() throws FilePersistenceException {
        testDao.loadInventory(); //Snacks are located at slots A1. A2, A3, A4, A5, A6
        Snack shouldBeNull = testDao.getSnack("A7");
        Assertions.assertNull(shouldBeNull, "Should be null");
    }
    @Test 
    public void testFilePersistenceGetAll() throws FilePersistenceException {
        testDao.loadInventory();
        List<Snack> snacksEdit1 = testDao.getAllSnacks();
        for (Snack eachSnack: snacksEdit1) {
            eachSnack.setQuantity(20);
        }
        List<Snack> snacksEdit2 = testDao.getAllSnacks();
         for (Snack eachSnack: snacksEdit2) {
            eachSnack.setQuantity(10);
        }
        testDao.writeInventory(snacksEdit2);
        testDao.loadInventory();
        List<Snack> snacksAfterReloadingFile = testDao.getAllSnacks();
        Assertions.assertEquals(snacksEdit2, snacksAfterReloadingFile, "Snacks should be the same after writing and reading file.");
        Assertions.assertEquals(snacksAfterReloadingFile.get(0).getName(), snacksEdit2.get(0).getName(), "Name at index 0 should be the same after writing and reading file.");
        Assertions.assertEquals(snacksAfterReloadingFile.get(2).getName(), snacksEdit2.get(2).getName(), "Name at index 2  should be the same after writing and reading file.");
        Assertions.assertEquals(snacksAfterReloadingFile.get(3).getName(), snacksEdit2.get(3).getName(), "Name at index 3 should be the same after writing and reading file.");
        Assertions.assertEquals(snacksAfterReloadingFile.get(0).getPrice(), snacksEdit2.get(0).getPrice(), "Price at index 0 should be the same after writing and reading file.");
        Assertions.assertEquals(snacksAfterReloadingFile.get(2).getPrice(), snacksEdit2.get(2).getPrice(), "Price at index 2  should be the same after writing and reading file.");
        Assertions.assertEquals(snacksAfterReloadingFile.get(3).getPrice(), snacksEdit2.get(3).getPrice(), "Price at index 3 should be the same after writing and reading file.");
        Assertions.assertEquals(snacksAfterReloadingFile.get(0).getQuantity(), snacksEdit2.get(0).getQuantity(), "Quantity at index 0 should be the same after writing and reading file.");
        Assertions.assertEquals(snacksAfterReloadingFile.get(1).getQuantity(), snacksEdit2.get(1).getQuantity(), "Quantity at index 1 should be the same after writing and reading file.");
        Assertions.assertEquals(snacksAfterReloadingFile.get(4).getQuantity(), snacksEdit2.get(4).getQuantity(), "Quantity at index 4 should be the same after writing and reading file.");
        Assertions.assertEquals(snacksAfterReloadingFile.get(0).getSlot(), snacksEdit2.get(0).getSlot(), "Slot at index 0 should be the same after writing and reading file.");
        Assertions.assertEquals(snacksAfterReloadingFile.get(1).getSlot(), snacksEdit2.get(1).getSlot(), "Slot at index 1 should be the same after writing and reading file.");
        Assertions.assertEquals(snacksAfterReloadingFile.get(4).getSlot(), snacksEdit2.get(4).getSlot(), "Slot at index 4 should be the same after writing and reading file.");
        Assertions.assertNotSame(snacksEdit1, snacksEdit2, "Should return different Array Lists of snacks after editing quantities to known difference amounts.");

    }
}
