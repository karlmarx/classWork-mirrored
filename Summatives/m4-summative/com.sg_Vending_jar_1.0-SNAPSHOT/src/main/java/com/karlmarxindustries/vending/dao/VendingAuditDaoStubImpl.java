/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karlmarxindustries.vending.dao;

import com.karlmarxindustries.vending.exception.FilePersistenceException;

/**
 *
 * @author karlmarx
 */
public class VendingAuditDaoStubImpl implements VendingAuditDao {

    @Override
    public void writeAuditEntry(String entry) throws FilePersistenceException {
        //do nothing
    }
    
}
