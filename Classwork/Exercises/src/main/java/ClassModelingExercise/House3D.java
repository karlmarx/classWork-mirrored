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
public class House3D {
       private double totalFloorArea;
       private int numberOfRooms;
       
       public void createNewRoom(){
           
       }
       public void render3D() {
           
       }

    public double getTotalFloorArea() {
        return totalFloorArea;
    }

    public void setTotalFloorArea(double totalFloorArea) {
        this.totalFloorArea = totalFloorArea;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public House3D(double totalFloorArea, int numberOfRooms) {
        this.totalFloorArea = totalFloorArea;
        this.numberOfRooms = numberOfRooms;
    }
       
       
}
