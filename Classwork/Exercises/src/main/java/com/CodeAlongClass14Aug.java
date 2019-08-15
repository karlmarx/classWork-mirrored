/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

/**
 *
 * @author karlmarx
 */
public class CodeAlongClass14Aug {
    public static void main(String[] args) {
        double expected = -3.2;
        double actual = getSmallest(12.3, 4.2, 3.14, -3.2, 53.3);
                
         if (actual == expected) {
             System.out.println("Huzzah");
         } else {
             System.out.println("Wait, got " + actual + " and expected" +expected);
         }
    }
    public static double getSmallest(double double1, double double2, double double3, double double4, double double5){
        double result = double1; 
       
        if (result > double2) {
            result = double2;
        }
        if (result > double3){
            result = double3;
        }
        if (result > double4){
            result = double4;
        }
        if (result > double5){
            result = double5;
        }
        return result;
    }
    
}
