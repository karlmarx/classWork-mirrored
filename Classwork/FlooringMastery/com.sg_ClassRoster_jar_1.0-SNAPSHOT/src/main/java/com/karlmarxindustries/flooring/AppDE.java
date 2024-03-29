/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karlmarxindustries.flooring;

import com.karlmarxindustries.flooring.controller.FlooringControllerBilingual;
import com.karlmarxindustries.flooring.dao.FilePersistenceException;
import com.karlmarxindustries.flooring.service.FlooringDuplicateIdException;
import com.karlmarxindustries.flooring.service.NoMatchingOrdersException;
import com.karlmarxindustries.flooring.service.NoOrdersOnDateException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author karlmarx
 */
public class AppDE {

    public static void main(String[] args) throws FilePersistenceException, NoOrdersOnDateException, FlooringDuplicateIdException, NoMatchingOrdersException {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("applicationContext.xml");
        FlooringControllerBilingual controller
                = ctx.getBean("controllerBilingual", FlooringControllerBilingual.class);
        controller.run();
    }
}
