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
public class NoSuchCarException extends Exception {

    public NoSuchCarException() {
    }

    public NoSuchCarException(String message) {
        super(message);
    }

    public NoSuchCarException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchCarException(Throwable cause) {
        super(cause);
    }

    public NoSuchCarException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
