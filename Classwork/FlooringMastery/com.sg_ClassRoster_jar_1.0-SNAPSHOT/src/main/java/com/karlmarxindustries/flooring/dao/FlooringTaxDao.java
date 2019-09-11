/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karlmarxindustries.flooring.dao;

import com.karlmarxindustries.flooring.dto.Order;
import com.karlmarxindustries.flooring.dto.Tax;
import java.util.List;
import java.util.Set;

/**
 *
 * @author karlmarx
 */
public interface FlooringTaxDao {
  
    public List<Tax> getAllTaxes() throws FilePersistenceException;
    public Tax getTax(String stateAbbr) throws FilePersistenceException;
    public List<String> getAllStates() throws FilePersistenceException;
    
    public void loadRateList() throws FilePersistenceException;

}
