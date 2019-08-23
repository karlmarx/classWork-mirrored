/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.objects;

import java.util.Random;

/**
 *
 * @author karlmarx
 */
public class Llama {
 
    private String name;
    private String woolColor;
    private String woolType;
    private int ageYears;
    private double heightInches;
          
    //getters and setters
    public String getName(){
        return this.name;
    }
    
    public void setName(String aName){
        this.name = aName;
    }
    
    public String getWoolColor(){
        return this.woolColor;
    }

    public void setWoolColor(String woolColor) {
        this.woolColor = woolColor;
    }

    public void setWoolType(String woolType) {
        this.woolType = woolType;
    }

    public void setAgeYears(int ageYears) {
        this.ageYears = ageYears;
    }

    public void setHeightInches(double heightInches) {
        this.heightInches = heightInches;
    }

    public String getWoolType() {
        return woolType;
    }

    public int getAgeYears() {
        return ageYears;
    }

    public double getHeightInches() {
        return heightInches;
    }

    
public void spit(){
    Random randomizer = new Random();
    int times = randomizer.nextInt(ageYears)+1;
    for (int i = 0; i <times; i++){
        System.out.println("Spit!");
    }
}
    public Llama(String name, String woolColor, String woolType, int ageYears, double heightInches) {
        this.name = name;
        this.woolColor = woolColor;
        this.woolType = woolType;
        this.ageYears = ageYears;
    }
    
    
    
}
