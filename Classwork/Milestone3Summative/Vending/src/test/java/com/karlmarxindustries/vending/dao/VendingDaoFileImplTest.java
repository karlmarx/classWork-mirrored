/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karlmarxindustries.vending.dao;

import com.karlmarxindustries.vending.dto.Snack;
import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author karlmarx
 */
public class VendingDaoFileImplTest {
    
    VendingDaoFileImpl testDao;
    public VendingDaoFileImplTest() {
        testDao = new VendingDaoFileImpl();
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
        Snack toTextify = new Snack("T2", "Name 2", new BigDecimal(2.00), 2);
        String snackAsText = testDao.marshallSnack(toTextify);
        Assertions.assertEquals ("T2::Name 2::2.00::2", snackAsText, "Lines should match.");
    }
    
}
