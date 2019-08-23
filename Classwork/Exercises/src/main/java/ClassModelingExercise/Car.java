/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassModelingExercise;

/**
 *
 * @author karlmarx
 */
public class Car {
    private int vehicleIdentificationNumber;
    private int inventoryCode;
    private String carMake;
    private String carModel;
    private int carYear;
    
    public void sellCar(int salePrice, int dateOfSale){
        
    }
    public void borrowCar(){
        
    }
    public void addCarPromotion(){
        
    }

    public int getVehicleIdentificationNumber() {
        return vehicleIdentificationNumber;
    }

    public void setVehicleIdentificationNumber(int vehicleIdentificationNumber) {
        this.vehicleIdentificationNumber = vehicleIdentificationNumber;
    }

    public int getInventoryCode() {
        return inventoryCode;
    }

    public void setInventoryCode(int inventoryCode) {
        this.inventoryCode = inventoryCode;
    }

    public String getCarMake() {
        return carMake;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public int getCarYear() {
        return carYear;
    }

    public void setCarYear(int carYear) {
        this.carYear = carYear;
    }

    public Car(int vehicleIdentificationNumber, int inventoryCode, String carMake, String carModel, int carYear) {
        this.vehicleIdentificationNumber = vehicleIdentificationNumber;
        this.inventoryCode = inventoryCode;
        this.carMake = carMake;
        this.carModel = carModel;
        this.carYear = carYear;
    }
    
    
}
