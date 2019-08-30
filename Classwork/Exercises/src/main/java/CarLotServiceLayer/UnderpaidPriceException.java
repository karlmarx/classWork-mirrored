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
class UnderpaidPriceException extends Exception {

    public UnderpaidPriceException() {
    }

    public UnderpaidPriceException(String message) {
        super(message);
    }

    public UnderpaidPriceException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnderpaidPriceException(Throwable cause) {
        super(cause);
    }

    public UnderpaidPriceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
