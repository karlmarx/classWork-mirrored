/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdklueber.gumball.controller;

import com.karlmarxindustries.gumballmachine.servicelayer.DvdLibraryDaoException;
import com.karlmarxindustries.gumballmachine.servicelayer.ServiceLayer;
import com.karlmarxindustries.dvdlibrary.dto.DVD;
import com.karlmarxindustries.ui.GumballMachineView;
import java.util.List;

/**
 *
 * @author karlmarx
 */
public class GumballMachineController {
    GumballMachineView view;
    ServiceLayer serviceLayer;
    public GumballMachineController(GumballMachineView view, ServiceLayer serviceLayer) {
        this.view = view;
        this.serviceLayer = this.serviceLayer; //STOP POINT
    }

    public void run()  {
        boolean keepGoing = true;
        int menuSelection = 0;
        welcomeMessage();
        
        while (keepGoing) {
            
            menuSelection = getMenuSelection();

            switch (menuSelection) {
                case 1:
                    addCoin();
                    break;
                case 2:
                    turnHandle();
                    break;
                case 3:
                    checkStatus();
                    break;
                case 4:
                    keepGoing = false;
                    break;
                default:
                    invalidInput();
            }
        }
        exitMessage();
    }
    private int getMenuSelection(){
        return view.printMenuAndGetSelection();
    }
    private void addCoin() {
        view.displayAddCoinBanner();
        int outcome = serviceLayer.addCoin();
        view.displayAddCoinOutcome(outcome);
    }
    
    private void listDvds() throws DvdLibraryDaoException {
        view.displayDisplayAllBanner();
        List<DVD> dvdList = dao.getAllDvds();
        view.displayDvdList(dvdList);
    }
    private void searchDvd() throws DvdLibraryDaoException {
        view.displayDisplayDvdBanner();
        List<DVD> dvdList = dao.getAllDvds();
         view.getTitleChoiceAndSearch(dvdList);
    }
    
    private void viewDvdTitle() throws DvdLibraryDaoException {
        view.displayViewDvdBanner();
        String exactTitle = view.getTitleChoiceExact();
        DVD dvdMatch = dao.getDvd(exactTitle.toUpperCase());
        view.viewDvd(dvdMatch);
    }
    private void removeDvd() throws DvdLibraryDaoException {
        view.displayRemoveDvdBanner();
        boolean keepRemoving = true;
        while (keepRemoving) {
            String title = view.getTitleChoice(); //made case-insensitive
            dao.removeDvd(title.toUpperCase());
            view.displayRemoveSuccessBanner();
            keepRemoving = view.confirmContinueRemoving();
        }
    }
       private void editDvd() throws DvdLibraryDaoException {
            view.displayEditDvdBanner();
            DVD dvd = null;
            boolean correctSelection = false;
            boolean keepEditing = true;
            while(keepEditing){
                while(!correctSelection) {
                    String title = view.getTitleChoice().toUpperCase();
                    dvd = dao.getDvd(title); 
                    view.viewDvd(dvd); 
                    correctSelection = view.confirmCorrectSelection();
                }
                dvd = view.updateDvdInfo(dvd);
                dao.editDVD(dvd.getTitle().toUpperCase(), dvd);
                keepEditing = view.confirmContinueEditing();
            }
            view.displayEditSuccessBanner();
    } 
    private void invalidInput() {
        view.displayInvalidInput();
    }
    private void exitMessage() throws DvdLibraryDaoException {
        dao.writeLibrary();
        view.displayExitBanner();
    }
    private void welcomeMessage(){
        view.displayWelcomeBanner();
    }
    
    
}
