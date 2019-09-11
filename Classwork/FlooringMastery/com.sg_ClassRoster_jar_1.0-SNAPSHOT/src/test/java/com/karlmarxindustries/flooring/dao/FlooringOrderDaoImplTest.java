/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karlmarxindustries.flooring.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author karlmarx
 */
public class FlooringOrderDaoImplTest {
    FlooringOrderDaoImpl testDao;
    public FlooringOrderDaoImplTest() {
        testDao = new FlooringOrderDaoImpl();
    }

    @Test
    public void testGetFilenames() {
        String[] filenames = testDao.getOrderFileNames("Orders/");
        
        Assertions.assertNotNull(filenames, "should not be null, there are files in dir");
        Assertions.assertEquals(2, filenames.length, "there should be two files in dir");
        
    }
    
    
    
}
