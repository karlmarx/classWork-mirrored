/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karlmarxindustries.dvdlibrary.controller;

import com.karlmarxindustries.dvdlibrary.dao.DvdLibraryDao;
import com.karlmarxindustries.dvdlibrary.dao.DvdLibraryDaoException;
import com.karlmarxindustries.dvdlibrary.dao.DvdLibraryDaoFileImpl;
import com.karlmarxindustries.dvdlibrary.dto.DVD;
import com.karlmarxindustries.ui.DvdLibraryView;
import com.karlmarxindustries.ui.UserIO;
import com.karlmarxindustries.ui.UserIOConsoleImpl;
import java.util.List;

/**
 *
 * @author karlmarx
 */
public class DvdLibraryController {
    DvdLibraryView view;
    DvdLibraryDao dao;
    
    public DvdLibraryController(DvdLibraryDao dao, DvdLibraryView view) {
        this.dao = dao;
        this.view = view;
    }

    public void run() throws DvdLibraryDaoException {
        boolean keepGoing = true;
        int menuSelection = 0;
        welcomeMessage();
        try{
        while (keepGoing) {
            
            menuSelection = getMenuSelection();

            switch (menuSelection) {
                case 1:
                    listDvds();
                    break;
                case 2:
                    addDvd();
                    break;
                case 3:
                    searchDvd();
                    break;
                case 4:
                    removeDvd();
                    break;
                case 5: 
                    editDvd();
                    break;
                case 6:
                    viewDvdTitle();
                    break;
                case 7:
                    keepGoing = false;
                    break;
                default:
                    unknownCommand();
            }
        }
        } catch (DvdLibraryDaoException e){
            view.displayErrorMessage(e.getMessage());

        }
        exitMessage();
    }
    private int getMenuSelection(){
        return view.printMenuAndGetSelection();
    }
    private void addDvd() throws DvdLibraryDaoException {
        view.displayAddDVDBanner();
        boolean keepAdding = true;
        while (keepAdding) {
            DVD newDvd = view.getNewDvdInfo();
            dao.addDVD(newDvd.getTitle(), newDvd);
            view.displayCreateSuccessBanner();
            keepAdding = view.confirmContinueAdding();
        } //added this while loop to allow for multiple adds in one session
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
        
        //DVD dvd = dao.getDvd(title); 
        //view.viewDvd(dvd);
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
            }//turn this into EDIT 
            view.displayEditSuccessBanner();
    } //basically display and then add 
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    private void exitMessage() {
        view.displayExitBanner();
    }
    private void welcomeMessage(){
        view.displayWelcomeBanner();
    }
    
    
}
