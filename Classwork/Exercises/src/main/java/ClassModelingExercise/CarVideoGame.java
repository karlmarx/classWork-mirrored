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
public class CarVideoGame {
    private int maxSpeed;
    private String color;
    private int maxDamagePoints;
    private int maxOccupents;
    
    public void attackCar(){
        
    }
    public void switchCar(){
        
    }
    public void upgradeCar(){
        
    }

    public CarVideoGame(int maxSpeed, String color, int maxDamagePoints, int maxOccupents) {
        this.maxSpeed = maxSpeed;
        this.color = color;
        this.maxDamagePoints = maxDamagePoints;
        this.maxOccupents = maxOccupents;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getMaxDamagePoints() {
        return maxDamagePoints;
    }

    public void setMaxDamagePoints(int maxDamagePoints) {
        this.maxDamagePoints = maxDamagePoints;
    }

    public int getMaxOccupents() {
        return maxOccupents;
    }

    public void setMaxOccupents(int maxOccupents) {
        this.maxOccupents = maxOccupents;
    }
  
}
