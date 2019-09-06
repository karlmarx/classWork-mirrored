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
    void loadInventory() throws FilePersistenceException;
    void writeInventory(List<Snack> snackList) throws FilePersistenceException;
}
