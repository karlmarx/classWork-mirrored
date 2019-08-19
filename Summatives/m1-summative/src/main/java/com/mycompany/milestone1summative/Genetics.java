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
public class Genetics {
    public static void main(String[] args) {
        
    
        String sequenceInput = "AGCTTTTCATTCTGACTGCAACGGGCAATATGTCTCTGTGTGGATTAAAAAAAGAGTGTCTGATAGCAGC";
    
    int countA = 0;
    int countC = 0;
    int countG = 0;
    int countT = 0;
    
    for (char base: sequenceInput.toCharArray()) {
        switch (base) {
            case 'A':
                countA++;
                break;
            case 'C':
                countC++;
                break;
            case 'G':
                countG++;
                break;
            case 'T':
                countT++;
                break;
                }
            }            
        int[] resultsACGT = {
            countA, countC, countG, countT
        }; 
            
          ///  int[] resultsACGT = CREATE METHOD here
                
            for (int count: resultsACGT) {
                System.out.print(count);
                System.out.print(" ");
            }
            System.out.println(countA + " " + countC + " " + countG + " " + countT);
            
                String resultStringFromCounts = createStringFromStrings(resultsACGT);
                System.out.println(resultStringFromCounts);
///COMPARE RESULT TO ORIGINAL BY PASSING RESULTS THROUGH FIRST METHOD

                ////String[] basesFromCount = new String[countA+countC+countG+countT];
            ////for (int i=0, i<countA, )
            
            
    }
    public static String createStringFromCount(int countBase, String baseLetter){
        StringBuilder sbString = new StringBuilder(countBase);
        for (int i=0; i < countBase; i++){
            sbString.append(baseLetter);
        }
        return sbString.toString();
    } 
    
    public static String createStringFromStrings (int[] arrayOfCounts){
        String newStringA = createStringFromCount(arrayOfCounts[0], "A"); 
        String newStringC = createStringFromCount(arrayOfCounts[1], "C"); 
        String newStringG = createStringFromCount(arrayOfCounts[2], "G"); 
        String newStringT = createStringFromCount(arrayOfCounts[3], "T"); 
        String fullString = newStringA + newStringC + newStringG +newStringT;
        return fullString;
    }
    
    
    
    
}
