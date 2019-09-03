/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karlmarxindustries.vending.dto;

/**
 *
 * @author karlmarx
 */
public class ChangeAndOutcome {
    public Change change;
    public boolean outcomeSuccess;

    public ChangeAndOutcome() {
    }

    public ChangeAndOutcome(Change change) {
        this.change = change;
    }
    

    public ChangeAndOutcome(Change change, boolean outcome) {
        this.change = change;
        this.outcomeSuccess = outcome;
    }

    public Change getChange() {
        return change;
    }

    public void setChange(Change change) {
        this.change = change;
    }

    public boolean getOutcomeSuccess() {
        return outcomeSuccess;
    }

    public void setOutcomeSuccess(boolean outcome) {
        this.outcomeSuccess = outcome;
    }

    
}
