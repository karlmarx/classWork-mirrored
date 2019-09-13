/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karlmarxindustries.flooring.dao;

import com.karlmarxindustries.flooring.dto.Tax;
import java.math.BigDecimal;
import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author karlmarx
 */
public class FlooringTaxDaoImplTest {
      FlooringTaxDaoImpl testTDao;
   
    public FlooringTaxDaoImplTest() {
         ApplicationContext ctx = 
            new ClassPathXmlApplicationContext("flooringTestApplicationContext.xml");
        testTDao = ctx.getBean("tDao", FlooringTaxDaoImpl.class);
    }
    
    
    @Test
    public void testGetAllTaxes() throws FilePersistenceException {
       testTDao.loadRateList();
       List<Tax> allTaxes =  testTDao.getAllTaxes();
        Assertions.assertEquals(4, allTaxes.size(), "there should be 4 taxs in the array list");
    }
    @Test
    public void testGetTax() throws FilePersistenceException {
        Tax shouldBeNull = testTDao.getTax("KY");
       testTDao.loadRateList();
        Tax gottenTax = testTDao.getTax("KY");
        Assertions.assertEquals("Ky", gottenTax.getStateAbbreviation(), "State abbr should be KY.");
        Assertions.assertEquals("Kentucky"  , gottenTax.getStateName(), "State should be kentucky.");
        Assertions.assertEquals(new BigDecimal("6.00")   , gottenTax.getTaxRate(), "Tax rate should be 6.00.");
        Assertions.assertNull(shouldBeNull,"empty dao should mean no tax to get.");
    }
    
}