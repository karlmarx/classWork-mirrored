/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karlmarxindustries.vending.dao;
import com.karlmarxindustries.vending.dto.Snack;
import com.karlmarxindustries.vending.exception.FilePersistenceException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author karlmarx
 */
public interface VendingDao { //should dao interface be independent of the load and write functions?
    List<Snack> getAllSnacks() throws FilePersistenceException;
    
    Snack getSnack (String slot) throws FilePersistenceException;
    
    void updateMoneyInside(BigDecimal moneyIn);
    void updateQuantity(String slot, Snack snack) throws FilePersistenceException; 
    
    void loadInventory(String libraryFile) throws FilePersistenceException;
    public String getProductionFile();
    public String getTestFile() ;
    void writeInventory(List<Snack> snackList, String libraryFile) throws FilePersistenceException;
    public Map<String, Snack> getSnacks();
    void print(String msg);

    double readDouble(String prompt);

    double readDouble(String prompt, double min, double max);

    float readFloat(String prompt);

    float readFloat(String prompt, float min, float max);

    int readInt(String prompt);

    int readInt(String prompt, int min, int max);

    long readLong(String prompt);

    long readLong(String prompt, long min, long max);

    String readString(String prompt);

    
}
