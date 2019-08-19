/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.milestone1summative;

import java.util.Scanner;

/**
 *
 * @author karlmarx
 */
public class HealthyHearts {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("What is your age in years? (enter whole numbers only)");
            int userAge = scanner.nextInt();
            int maxHeartRate = 220 - userAge;
            System.out.println("Your maximum heart rate should be " + maxHeartRate + " beats per minute.");
            System.out.println("Your target HR zone is " + (.5*maxHeartRate) + " - " + (.85*maxHeartRate) + " beats per minute.");
    }
}
