/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.milestone1summative;

/**
 *
 * @author karlmarx
 */
public class summativeSums {
    public static void main(String[] args) {
        int[] arrayOne = { 1, 90, -33, -55, 67, -16, 28, -55, 15 };
        int[] arrayTwo  = { 999, -60, -77, 14, 160, 301 };
        int[] arrayThree = { 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 
            140, 150, 160, 170, 180, 190, 200, -99 }; 
        System.out.println("#1 Array Sum: " + arraySummer(arrayOne));
        System.out.println("#2 Array Sum: " + arraySummer(arrayTwo));
        System.out.println("#3 Array Sum: " + arraySummer(arrayThree));
    }
    
    public static int arraySummer (int[] inputArray) {
        int sum = 0;
        for (int arrayItem : inputArray) {
            sum += arrayItem;
        } 
        return sum;
    }
}
