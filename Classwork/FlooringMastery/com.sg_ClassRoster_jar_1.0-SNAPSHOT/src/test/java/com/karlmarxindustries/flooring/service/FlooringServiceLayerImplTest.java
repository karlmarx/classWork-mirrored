/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karlmarxindustries.flooring.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author karlmarx
 */
public class FlooringServiceLayerImplTest {
    private FlooringServiceLayer service;
    private FlooringODaoStubImpl oDao;
     private FlooringPDaoStubImpl pDao;
      private FlooringTDaoStubImpl tDao;
            
    
    public FlooringServiceLayerImplTest() {
         ApplicationContext ctx = 
            new ClassPathXmlApplicationContext("flooringTestApplicationContext.xml");
        service = ctx.getBean("serviceLayer", FlooringServiceLayer.class);
    }

    @Test
    public void testSomeMethod() {
    }
    
}
