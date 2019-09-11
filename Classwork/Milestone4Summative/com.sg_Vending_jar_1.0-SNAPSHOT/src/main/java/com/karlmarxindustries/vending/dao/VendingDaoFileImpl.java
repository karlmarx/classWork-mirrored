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
import com.karlmarxindustries.vending.service.VendingServiceLayer;

/**
 *
 * @author karlmarx
 */
public class VendingDaoFileImpl implements VendingDao {
  // public static String libraryFile (liveInventory);
    
    //private final  String productionFile = "inventory.txt";
    //private final String testFile = "inventorytest.txt";
    private static final String DELIMITER = "::";
    private Map<String, Snack> snacks = new HashMap<>();
    private String filename;

    public VendingDaoFileImpl(String filename) {
        this.filename = filename;
    }
    
   
   
    //@Override
    public Snack unmarshallSnack(String inventoryAsText){
        String[] vendTokens = inventoryAsText.split(DELIMITER);
        String slot = vendTokens[0];
        Snack snackFromFile = new Snack();
        snackFromFile.setSlot(slot);
        snackFromFile.setName(vendTokens[1]);
        snackFromFile.setPrice(new BigDecimal(vendTokens[2]));
        snackFromFile.setQuantity(Integer.valueOf(vendTokens[3]));
        return snackFromFile;
    }
    public String marshallSnack(Snack aSnack){
        String snacksAsText = aSnack.getSlot() + DELIMITER;
        snacksAsText += aSnack.getName() + DELIMITER; 
        snacksAsText += String.valueOf(aSnack.getPrice()) + DELIMITER;
        snacksAsText += String.valueOf(aSnack.getQuantity());
        return snacksAsText;
    } 
    @Override
    public void loadInventory() throws FilePersistenceException {
        Scanner scanner;
        try{
            scanner = new Scanner(new BufferedReader(new FileReader(filename)));
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
    
    @Override
    public void writeInventory(List<Snack> snackList) throws FilePersistenceException {
        PrintWriter out;
        try{
            out = new PrintWriter(new FileWriter(filename));
        } catch (IOException e){
            throw new FilePersistenceException("Could not save Inventory data", e);
        }
        String snackAsText;
       
        for (Snack currentSnack : snackList){
            snackAsText = marshallSnack(currentSnack);
            out.println(snackAsText);
            out.flush();
        }
               out.close();
    }

    @Override
    public List<Snack> getAllSnacks() throws FilePersistenceException {
        return  new ArrayList<Snack>(snacks.values());
    }
    @Override
    public Snack getSnack(String slot) throws FilePersistenceException {
        return snacks.get(slot);
    }
}
