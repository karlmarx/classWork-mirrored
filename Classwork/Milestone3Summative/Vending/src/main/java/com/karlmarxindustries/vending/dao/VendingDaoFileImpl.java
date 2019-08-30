/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karlmarxindustries.vending.dao;

import com.karlmarxindustries.vending.dto.Snack;
import com.karlmarxindustries.vending.exception.FilePersistenceException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author karlmarx
 */
public class VendingDaoFileImpl implements VendingDao {
    
    public static final String LIBRARY_FILE = "inventory.txt";
    public static final String DELIMITER = "::";
    private Map<String, Snack> snacks = new HashMap<>();

    
    private Snack unmarshallSnack(String inventoryAsText){
        String[] vendTokens = inventoryAsText.split(DELIMITER);
        String slot = vendTokens[0];
        Snack snackFromFile = new Snack();
        snackFromFile.setSlot(slot);
        snackFromFile.setName(vendTokens[1]);
        snackFromFile.setPrice(new BigDecimal(vendTokens[2]));
        snackFromFile.setQuantity(Integer.valueOf(vendTokens[3]));
        return snackFromFile;
    }
    
    private String marshallDvd(Snack aSnack){
        String dvdAsText = aSnack.getSlot() + DELIMITER;
        dvdAsText += aSnack.getName() + DELIMITER; 
        dvdAsText += String.valueOf(aSnack.getPrice()) + DELIMITER;
        dvdAsText += aSnack.getQuantity();
        return dvdAsText;
    } ///STOPPED HERE
    
    public void loadInventory() throws FilePersistenceException {
        Scanner scanner;
        try{
            scanner = new Scanner(new BufferedReader(new FileReader(LIBRARY_FILE)));
        } catch (FileNotFoundException e) {
            throw new FilePersistenceException("Uh-oh! Could not load inventory data into memory", e);
        }
        String currentLine;
        Snack currentSnack;
        while (scanner.hasNextLine()){
            currentLine = scanner.nextLine();
            currentSnack = unmarshallSnack(currentLine);
            snacks.put(currentSnack.getSlot(), currentSnack);
        }
        scanner.close();
    }
    
    public void writeLibrary() throws FilePersistenceException {
        PrintWriter out;
        try{
            out = new PrintWriter(new FileWriter(LIBRARY_FILE));
        } catch (IOException e){
            throw new FilePersistenceException("Could not save Inventory data", e);
        }
        String dvdAsText;
        List<DVD> dvdList = this.getAllDvds();
        for (DVD currentDvd : dvdList){
            dvdAsText = marshallDvd(currentDvd);
            out.println(dvdAsText);
            out.flush();
        }
               out.close();
    }
    
    @Override
    public void print(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double readDouble(String prompt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float readFloat(String prompt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int readInt(String prompt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long readLong(String prompt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String readString(String prompt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Snack> getAllSnacks() throws FilePersistenceException {
        return  new ArrayList<Snack>(snacks.values());
    }

    @Override
    public Snack getSnack(Slot slot) throws FilePersistenceException {
        return snacks.get(slot);
    }

//    @Override
//    public DVD removeDvd(String title) throws FilePersistenceException {
//        DVD removedDvd = snacks.remove(title);
//        return removedDvd;
//    }   
    

    @Override
    public DVD updateQuantity (String slot, Snack dvd) throws FilePersistenceException {
        DVD newDvd =dvds.put(title, dvd);
        return newDvd;   //put the rest of update in service LAyer
    }
    
    
    @Override
    public DVD editDVD (String title, DVD dvd) throws DvdLibraryDaoException{
        //this needs to be different - find and update in map
        DVD editedDvd = dvds.put(title, dvd); //make sure first part is good with updates using an if 
        return editedDvd;
    }
    

    private Writer newFileWriter(String LIBRARY_FILE) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
