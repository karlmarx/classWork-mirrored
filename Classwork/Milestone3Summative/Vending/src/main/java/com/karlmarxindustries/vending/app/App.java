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
import com.karlmarxindustries.vending.dao.VendingDao;
import com.karlmarxindustries.vending.exception.FilePersistenceException;

/**
 *
 * @author karlmarx
 */
public class App {
    
        public static void main(String[] args) throws FilePersistenceException  {
            UserIO myIO = new UserIOConsoleImpl();
            VendingView myView = new VendingView(myIO);
            VendingDao myDao = new VendingDaoFileImpl();
            DvdLibraryController controller = new DvdLibraryController(myDao, myView);
            controller.run();
    }   
}
