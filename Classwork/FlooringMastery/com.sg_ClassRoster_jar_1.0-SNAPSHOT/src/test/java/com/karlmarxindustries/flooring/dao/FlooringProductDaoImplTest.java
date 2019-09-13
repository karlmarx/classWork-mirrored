/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karlmarxindustries.flooring.dao;

import com.karlmarxindustries.flooring.dto.Product;
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
public class FlooringProductDaoImplTest {
      FlooringProductDaoImpl testPDao;
   
    public FlooringProductDaoImplTest() {
         ApplicationContext ctx = 
            new ClassPathXmlApplicationContext("flooringTestApplicationContext.xml");
        testPDao = ctx.getBean("pDao", FlooringProductDaoImpl.class);
    }
    
    
    @Test
    public void testGetAllProducts() throws FilePersistenceException {
       testPDao.loadProductInfo();
       List<Product> allProducts =  testPDao.getAllProducts();
        Assertions.assertEquals(4, allProducts.size(), "there should be 4 products in the array list");
    }
    @Test
    public void testGetProduct() throws FilePersistenceException {
        Product shouldBeNull = testPDao.getProduct("Wood");
        testPDao.loadProductInfo();
        Product gottenProduct = testPDao.getProduct("Wood");
        Assertions.assertEquals("Wood", gottenProduct.getProductType(), "product type should be wood.");
        Assertions.assertEquals(new BigDecimal("5.15")  , gottenProduct.getCostPerSquareFoot(), "Cost per sq ft should be 2.25.");
        Assertions.assertEquals(new BigDecimal("4.75")   , gottenProduct.getLaborCostPerSquareFoot(), "Labor cost per sq ft should be 2.10.");
        Assertions.assertNull(shouldBeNull,"empty dao should mean no product to get.");
    }
    
}
