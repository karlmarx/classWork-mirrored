/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.temperatureproject;

import java.util.Scanner;

/**
 *
 * @author karlmarx
 */
public class App {
    public static void main(String[] args) {
        // ask for a temperature
        double inputTemp = getTemperatureInput();
        // ask for a scale (f or c)
        String scale = getTempScale();
        // calculate conversion
        double convertedTemp = convertTemp(inputTemp, scale);
        // report the conversion 
        printConversionReport(inputTemp, scale, convertedTemp);
        // --stretch goal -- wrap in a loop
    }

    private static double getTemperatureInput() {
        double result = 0.0;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a temperature to convert: ");
        String input = scanner.nextLine();
        result = Double.valueOf(input); 
                
        return result;
    }
    
    private static String getTempScale(); {
        String result = "";
        boolean goodInput = false;
        
        Scanner scanner = new Scanner(System.in);
        
       while(!goodinput) {
           System.out.println("C or F?");
           String input = scanner.nextLine();
           if (!input.equals("C") && !input.equals("F"))
       }
}

    private static String getTempScale() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static double convertTemp(double inputTemp, String scale) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void printConversionReport(double inputTemp, String scale, double convertedTemp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
