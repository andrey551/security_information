
package Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Dau Cong Tuan Anh
 */
public class Container {
    private String data;
    private Key key;
    private SBox box;
    
    public Container(Key key, SBox sBox) {
        this.data = "";
        this.key = key;
        this.box = sBox;
    }
    
    public String encodeBlock(String text) {
        short[][] data = new short[4][4];
        String res = "";
        
        for(int i = 0; i < 4; ++i) {
            for(int j = 0; j < 4; ++j) {
                if(i * 4 + j >= text.length()) {
                    data[i][j] = 0;
                } else {
                    data[i][j] = (short) text.charAt(i * 4 + j);
                }
                
            }
        }
        
        Block block = new Block(data, this.box);
        block.cypher(key);
        System.out.println("decode block: " + block.toString());
        
        return block.toString();  
    }
    private int min(int a, int b) {
        return a < b ? a : b;
    }
    public String encode() {
        String encodeText = "";
        int l = this.data.length();
        
        for(int i = 0; i < l; i += 16) {
            System.out.println("block " + i/16 + " :");
            String s =  this.data.substring(i, min(i+ 16, l));
            System.out.println("s:" + s);
            encodeText += encodeBlock(s);
        }
        
        return encodeText;
    }
    
    public void read(String path) {
        String res = "";
        String line ;
        try{
            
            Scanner scanner = new Scanner(new File(path));
            scanner.useDelimiter("\n");
            while(scanner.hasNextLine()) {
                
                line = scanner.nextLine();
                res = res.concat(line);
            }
            
            this.data = res; 
            
            System.out.println("Input: " + res);
        } catch(IOException e) {

            System.err.println("Cannot open file!");

        } 
    }
    
    public void write(String out, String path) {
        try {
            try (BufferedWriter writer = 
                    new BufferedWriter(new FileWriter(path))) {
                writer.write(out);
            }

        } catch( IOException e) {
            System.err.println("Cannot write to file");
        }
    }
}
