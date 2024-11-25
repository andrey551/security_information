
package Model;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Dau Cong Tuan Anh
 */
public class Key {
    private short[][] cypherKey;
    private int rcon;
    
    public Key() {
        cypherKey = new short[4][4];
        rcon = 0x1;
    }
    
    public void read(String path) {
        int row_iter = 0, column_iter = 0;
        String line ;
        try{
            
            Scanner scanner = new Scanner(new File(path));
            scanner.useDelimiter("\n");
            while(scanner.hasNextLine()) {
                
                line = scanner.nextLine();
                
                String[] s = line.split(" ");
                
                for(column_iter = 0; column_iter < 4; ++column_iter) {
                    this.cypherKey[row_iter][column_iter] = 
                            Short.parseShort(s[column_iter]);
                }
                
                ++row_iter;
            }
            
            System.out.println("Key:");
            for(int i = 0 ;i < 4; ++i) {
                for(int j = 0 ;j < 4; ++j) {
                    System.out.print(this.cypherKey[i][j] + " ");
                }
                
                System.out.println();
            }
        } catch(IOException e) {
            
            System.err.println("Cannot open file!");

        } 
    }
    
    public Key(short rcon) {
        this.rcon = rcon << 1;
    }
    
    public Key(short[][] cypherKey, int rcon) {
        this.cypherKey = cypherKey;
        this.rcon = rcon;
    }
    
    public Key generateNext(SBox sBox) {
        short[][] res = new short[4][4];
        short[] tmp = new short[4];
        short[] sCol;
        short[] RCon = new short[4];
               
        tmp[0] = this.cypherKey[1][3];
        tmp[1] = this.cypherKey[2][3];
        tmp[2] = this.cypherKey[3][3];
        tmp[3] = this.cypherKey[0][3];
        
        sCol = sBox.encode(tmp);
        
        RCon[0] = (short) ((short)this.rcon * 2);
        RCon[1] = RCon[2] = RCon[3] = 0x0;
        
        for(int i = 0; i < 4; ++i) {
            res[i][0] = (short) (this.cypherKey[i][0] ^ sCol[i] ^ RCon[i] % 256);
        } 
        
        for(int i = 1; i < 4; ++i) {
            for(int j = 0 ;j <4; ++j) {
                res[j][i] = (short) (this.cypherKey[j][i] ^ res[j][i - 1]% 256);
            }
        }
        
        return new Key(res, this.rcon << 1);
    }
    
    public short at(int i, int j) {
        return this.cypherKey[i][j];
    }
    
}
