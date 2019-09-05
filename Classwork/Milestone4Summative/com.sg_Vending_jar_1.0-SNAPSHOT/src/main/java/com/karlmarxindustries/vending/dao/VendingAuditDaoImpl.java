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
import java.time.format.DateTimeFormatter;

/**
 *
 * @author karlmarx
 */
public class VendingAuditDaoImpl implements VendingAuditDao {
    public static final String AUDIT_FILE = "audit.txt";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
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
            out.println(timeStamp.format(formatter)+ " : [" + entry + "]");
            out.flush();
    }
    
}
