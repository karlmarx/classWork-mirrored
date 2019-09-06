/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karlmarxindustries.vending.app;

import com.karlmarxindustries.vending.dao.VendingDaoFileImpl;
import com.karlmarxindustries.ui.UserIO;
import com.karlmarxindustries.ui.UserIOConsoleImpl;
import com.karlmarxindustries.ui.VendingView;
import com.karlmarxindustries.vending.controller.VendingController;
import com.karlmarxindustries.vending.dao.AuditDao;
import com.karlmarxindustries.vending.dao.AuditDaoImpl;
import com.karlmarxindustries.vending.dao.VendingDao;
import com.karlmarxindustries.vending.exception.FilePersistenceException;
import com.karlmarxindustries.vending.exception.InsufficientFundsException;
import com.karlmarxindustries.vending.exception.ItemSoldOutException;
import com.karlmarxindustries.vending.service.ServiceLayer;
import com.karlmarxindustries.vending.service.ServiceLayerImpl;

/**
 *
 * @author karlmarx
 */
public class App {
    
        public static void main(String[] args) throws FilePersistenceException, InsufficientFundsException, ItemSoldOutException  {
            UserIO myIO = new UserIOConsoleImpl();
            VendingView myView = new VendingView(myIO);
            VendingDao myDao = new VendingDaoFileImpl();
            AuditDao myAuditDao = new AuditDaoImpl();
            ServiceLayer myServiceLayer = new ServiceLayerImpl(myDao, myAuditDao);
            VendingController controller = new VendingController(myView, myServiceLayer);
            controller.run();
    }   
}
