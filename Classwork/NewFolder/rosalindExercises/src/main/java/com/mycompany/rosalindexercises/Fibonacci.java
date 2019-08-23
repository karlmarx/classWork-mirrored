/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rosalindexercises;

/**
 *
 * @author karlmarx
 */
public class Fibonacci {
    public static void main(String[] args) {
        int n = 5;
        int k = 3;
        int n1 = 1;
        int n2 = 1;
        int n3;
        int sum = n1+n2;
        for (int i=2; i<n; i++){
            n3=k*(n1+n2);
            n2=n3;
            sum = sum + n3;
        }   
        System.out.println(sum);
    }
}
