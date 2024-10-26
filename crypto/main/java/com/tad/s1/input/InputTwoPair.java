
package com.tad.s1.input;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;
import lombok.Getter;

/**
 *
 * @author Dau Cong Tuan Anh
 */

@Getter
public class InputTwoPair implements Input{
    private BigInteger N;
    private BigInteger e1;
    private BigInteger e2;
    private ArrayList<String> C1;
    private ArrayList<String> C2;
    
    public InputTwoPair() {
        C1 = new ArrayList<>();
        C2 = new ArrayList<>();
    }
    
    @Override
    public void read(String fileName) {
        try{
            try (Scanner scanner = new Scanner(new File(fileName))) {
                
                ArrayList<String> temp = new ArrayList<>();
                
                int i = 0, size;
                
                scanner.useDelimiter("\n");
                
                this.N = scanner.nextBigInteger();
                this.e1 = scanner.nextBigInteger();
                this.e2 = scanner.nextBigInteger();
                
                while(scanner.hasNextLine()) {
                    temp.add(scanner.next());
                }
                
                while(!temp.get(i).equals("***")) {
                    this.C1.add(temp.get(i++));
                }
                
                size = temp.size();
                
                ++i;
                
                while(i < size) {
                    this.C2.add(temp.get(i++));
                }
            }
        }
        catch(FileNotFoundException e) {
            System.err.print("File does not exist");
        }
    }
    @Override
    public String toString() {
        return N 
                + "\n" + e1 
                + "\n" + e2 
                + "\n" + C1.toString() 
                + "\n" + C2.toString()
                + "\n"; 
    }
}
