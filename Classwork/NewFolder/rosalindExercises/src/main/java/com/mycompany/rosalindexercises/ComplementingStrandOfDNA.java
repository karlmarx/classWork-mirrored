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
public class ComplementingStrandOfDNA {
    public static void main(String[] args) {
        String inputSeq = "ATTTATAGCGCGCGGCACCGGTGAAGGAGGTGGCCGATTAATACTTGTCATCCACGAGAGAATTGCGAAATTCGGACATAAGGGCCGCCGCTAACCTAAACTGGATCACATGCTCTTCATGATACCAGACGTTGGGTTCACAAGAAGGTGCCGCCCAATATCGTATTGACCCGGCTACCCACTGAATGGCACATTTACAGATCGTCTTCCCGGAGCTCGACTCGGACGATAAGAGAATATGCTCCAAGTGTGGGCCTACCGCAAAGGTGCGCATAGTGACCTATCCGGGTGACAGGCTAATGCCGACTATGAAGTACCCTTTGCTTCACAAACCGACCGCTTACCCCAACTATTACTGCGAATCCATTACTGATCTTCCTCCCGACGCGGCTCAGATCCCAACCTAATCTAAACGATTGATCAACGTCATTCGGATCGTCAGTCGGCGTACCGAAATAGTTCCTGAACAATCATAGCTCGTAGGAGGAGATGTATCGAGCCTCTGTGACAAAGCGGGTTAGAGTTTCTGTGGCTCATAAATCGTCAGTAGGGTGGTACGGATTTAAGTTTAGTATTTAGTATTGTTATAACGGTGTTAGTTAGCAACTAATGAATCTTGAGTCCCGGTAGAAAAAGGCCTCGCGGATGCGTAAGGACGTCGTCTAGAAGTGCACCTCCGCTTTCAGATCAAATAAACTCTACCAGTTCTCTGACTTTGGCGTGACTGAGAGACCCTTCCGCCCAAACTTTTAGCGCCACACATAAGTGTAAACTACCCAACCGTACGCAAACATGAAATCAATCCCACCTTAGCCCTATTACTAATACCTGGCCGCACCTAATATGCACTTAAAAAGTAA";
        char charArray[] = inputSeq.toCharArray();
        char newArray[] = reverseArray(charArray);
        for (int i=0; i<newArray.length; i++) {
            switch (newArray[i]) {
                case 'A':
                    newArray[i]= 'T';
                    break;
                case 'T':
                    newArray[i] = 'A';
                    break;
                case 'C':
                    newArray[i] = 'G';
                    break;
                case 'G':
                    newArray[i] = 'C';
                    break;
                default:
                    break;
            }
        }
        System.out.println(newArray);
            }
    
    public static char[] reverseArray (char[] array){
                char[] newArray = new char[array.length];
                int j = array.length;
                for (int i =0; i<array.length; i++) {
                    newArray[j-1] = array[i];
                    j -= 1;
                }
        
                return newArray;
    }
}
