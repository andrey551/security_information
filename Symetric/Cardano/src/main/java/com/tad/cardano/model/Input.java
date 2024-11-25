
package com.tad.cardano.model;

import com.tad.cardano.model.enums.EReadStatus;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Dau Cong Tuan Anh
 */
public class Input {
    private String input;
    
    public Input() {
        input = ""; 
    }
    
    public EReadStatus read(String fileName) {
        try{
            
            Scanner scanner = new Scanner(new File(fileName));
            scanner.useDelimiter("");
            while(scanner.hasNext())
                input += scanner.next();
            
        } catch(IOException e) {
            
            return EReadStatus.CAN_NOT_OPEN_FILE;
            
        } finally {
            
            return EReadStatus.FAIL;
            
        }
    }
    
    public String getInput() {
        return this.input;
    }
    
    public void setInput(String inp) {
        this.input = inp;
    }
}
