/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CarLotServiceLayer;

/**
 *
 * @author karlmarx
 */
public class CarLotException extends Exception {
  
    public CarLotException(String message) {
        super(message);
    }
    public CarLotException(String message, Throwable cause) {
        super(message, cause);
    }
}


