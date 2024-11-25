
package Model;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Dau Cong Tuan Anh
 */
public class SBox {
    private short[][] STable;
    
    public SBox() {
        STable = new short[16][16];
    }
    
    public void read(String path) {
        int row_iter = 0, column_iter = 0;
        String line ;
        try{
            
            Scanner scanner = new Scanner(new File(path));
            scanner.useDelimiter("\n");
            while(scanner.hasNextLine()) {
                
                line = scanner.nextLine();
                
                String[] s = line.split("	");
                
                for(column_iter = 0; column_iter < 16; ++column_iter) {
                    this.STable[row_iter][column_iter] = 
                            Short.parseShort(s[column_iter],16);
                }
                
                ++row_iter;
            }
            System.out.println("SBox: ");
            for(int i = 0; i < 16; ++i) {
                for(int j = 0 ;j < 16; ++j) {
                    System.out.print(this.STable[i][j] + " ");
                }
                
                System.out.println();
            }
                
            
        } catch(IOException e) {
            
            System.err.println("Cannot open file!");

        } 
    }
    
    public short[][] encode(short[][] data) {
        for(int i = 0 ;i < 4; ++i) {
            for(int j = 0; j < 4; ++j) {
                System.out.print(data[i][j] + " ");
            }
            
            System.out.println();
        }
        short[][] res = new short[4][4];
        int r, c;
        
        for(int i = 0; i < 4; ++i) {
            for(int j = 0; j < 4; ++j) {
                r = data[i][j] /16;
                c = data[i][j] %16;
                res[i][j] = this.STable[r][c];
            }
        }
        
        return res;
    }
    
    public short[] encode(short[] data) {
        short[] res = new short[4];
        int r, c;
        
        for(int i = 0 ;i < 4; ++i) {
            r = data[i] /16;
            c = data[i] %16;
            res[i] = this.STable[r][c];
        }
        
        return res;
    }
}
