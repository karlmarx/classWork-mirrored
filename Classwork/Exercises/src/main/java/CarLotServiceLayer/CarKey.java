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
public class CarKey {
    private String VIN;
    private boolean allowsRemote;

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public boolean isAllowsRemote() {
        return allowsRemote;
    }

    public void setAllowsRemote(boolean allowsRemote) {
        this.allowsRemote = allowsRemote;
    }

    public CarKey() {
    }

    // plus getters, setters, noArg & all Arg constructors

    public CarKey(String VIN, boolean allowsRemote) {
        this.VIN = VIN;
        this.allowsRemote = allowsRemote;
    }
}