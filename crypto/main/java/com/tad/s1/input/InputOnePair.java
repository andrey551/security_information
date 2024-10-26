/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tad.s1.input;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.math.BigInteger;
import java.util.ArrayList;
import lombok.Getter;

/**
 *
 * @author Never
 */

@Getter
public class InputOnePair implements Input{
    private BigInteger N;
    private BigInteger e;
    private ArrayList<String> C;
    
    public InputOnePair(){
        this.C = new ArrayList<>();
    }
    
    public InputOnePair(BigInteger N, 
                 BigInteger e, 
                 String C) {
        this.N  = N;
        this.e = e;
        this.C = new ArrayList<>();
    }
    
    @Override
    public void read(String fileName){
        try{
            try (Scanner scanner = new Scanner(new File(fileName))) {
                scanner.useDelimiter("\n");
                this.N = BigInteger.valueOf(scanner.nextLong());
                this.e = BigInteger.valueOf(scanner.nextLong());
                while(scanner.hasNextLine()) {
                    C.add(scanner.next());
                }
            }
        }
        catch(FileNotFoundException e) {
            System.err.print("File does not exist");
        }
        
    }
    
    @Override
    public String toString() {
        return N + " " + e + " " + C; 
    }
}
