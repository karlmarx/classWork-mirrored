/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karlmarxindustries.vending.dao;

import com.karlmarxindustries.vending.exception.FilePersistenceException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author karlmarx
 */
public class AuditDaoImpl implements AuditDao {
    public static final String AUDIT_FILE = "audit.txt";
    public void stamp(BigDecimal balance, String after_money_was_inserted) {
        System.out.println("Time Stamped placeholder ");
    }

    @Override
    public void writeAuditEntry(String entry) throws FilePersistenceException {
            PrintWriter out;
            try {
                    out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
            } catch (IOException e ) { 
                throw new FilePersistenceException("Could not persist audit information.", e);
            }
            LocalDateTime timeStamp = LocalDateTime.now();
            out.println("Time <" + timeStamp.toString() + "> : Event = <" + entry + ">");
            out.flush();
    }
    
}
