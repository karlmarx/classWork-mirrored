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
public class TheSecondDataset {
    public static void main(String[] args) {
         String sequenceInput = "TTGACGGCTACTGCCATTTATGTGATCGAAAGCACAGGTGAGATTGTAAGCTGGCCAACGGCCTTTGGTTTGGTCATATAGGGTAATATGAGCGAAAGGTCTATACTGAAGGGTTTGCCAAGGGGGCCACAGAATCTCACGTAGTAAATTGCGTTGCAGCGTAACTCCAGTAAGTGCGTGCCGCACTTCCAGCAATGTTACGATGTGGACGGTGCAGACTACATCATCGATGGTCCGGTACGCCTGGATTCGACCATCTAAGTGGTATAGCGTAGTCGAACTTATCGTTCACTGAGTTAAGCTGGGCTTGAGCAAGTTTGATGTAGGACTGGACTTTGGACCACGAGGTTGTAAACGAGCATAGTGATCTGATTATAAAGGGACGACCCTCCCGTTCATAGGCGCCAATCGAGCTGCCACGTCAAGGAGGGGCACACTTATGCGACACGATTTCATTAAACCCTGCCGTGTAGCTAGGCCGTGCCATTACGTTACGGGAGTAGGAGCCAAATGATGCGCCGCAAGTACATTGCCGACCCTTGGTGGAGACGGAATTAGTCTACTCCCGTATACTGCTATCTGGGTACTGCATGGAGGTCTACGAAAAAAAACTTAACCGTTACCCCAGAGTGGGCCCTATATAGCTGCTTTGATACCGCCTATGGCGGCCGTTGTTGATAAACATGTACCCATTGGATTAGAATTCTTCGATCTCTGAGGGACTGGATGTTCTCTAATTATAGAATGTATAACTGTCTCCATCAATCATACTGAGCACTTGAGCTGGGCTTCACCTCCTGGTACTTGTTCTATGTCCAAGGGAAATTCGGTAAATTGCGCAGTAATTTATGCTATACTTTAATCACTTGCGAGTACCCGGTAGATCCGTATTCTCGACATCTGGAGATAAGTAACGGAGTGCACAGTGGC";
         char newArray[] = sequenceInput.toCharArray();

 for (int i =0; i < newArray.length; i++){
         if (newArray[i] =='T'){
            newArray[i] = 'U';
            }
}
        System.out.println(newArray);
    }
    
}
