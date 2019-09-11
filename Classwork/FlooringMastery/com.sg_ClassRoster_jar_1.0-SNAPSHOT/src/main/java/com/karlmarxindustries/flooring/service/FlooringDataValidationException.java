/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karlmarxindustries.flooring.service;

/**
 *
 * @author karlmarx
 */
public class FlooringDataValidationException extends Exception {

    public FlooringDataValidationException(String message) {
        super(message);
    }

    public FlooringDataValidationException(String message,
            Throwable cause) {
        super(message, cause);
    }

}
