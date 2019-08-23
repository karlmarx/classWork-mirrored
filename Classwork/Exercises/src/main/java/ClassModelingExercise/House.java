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
public class House {
    private double longitude;
    private double lattitute; 
    private String address;
    
    public String recordSatelliteImage(){
        return "link to image";
    }
    public boolean markLocationClassified(){
        return true;
    }
            

    public House(double longitude, double lattitute, String address) {
        this.longitude = longitude;
        this.lattitute = lattitute;
        this.address = address;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLattitute() {
        return lattitute;
    }

    public void setLattitute(double lattitute) {
        this.lattitute = lattitute;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    
}
