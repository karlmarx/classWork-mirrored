/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karlmarxindustries.vending.app;

import com.karlmarxindustries.vending.controller.VendingController;
import com.karlmarxindustries.vending.exception.FilePersistenceException;
import com.karlmarxindustries.vending.exception.InsufficientFundsException;
import com.karlmarxindustries.vending.exception.ItemSoldOutException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author karlmarx
 */
public class App {
    
    public static void main(String[] args) throws FilePersistenceException, InsufficientFundsException, ItemSoldOutException  {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        VendingController controller = 
        ctx.getBean("controller", VendingController.class);
        controller.run();
    }   
}
