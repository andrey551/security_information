
package com.tad.cardano.model;

import com.tad.cardano.model.enums.EReadStatus;
import com.tad.cardano.model.enums.ERotateAngle;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
/**
 *
 * @author Dau Cong Tuan Anh
 */
public class Grid {
    private boolean[][] grid;
    
    public Grid(){
        grid = new boolean[4][4];
    };
    
    public Grid(String filePath) {
        this();
        read(filePath);
    }
    
    public EReadStatus read(String fileName) {
        int i, j;
        String line[];
        
        try{
            
            Scanner scanner = new Scanner(new File(fileName));
            
            scanner.useDelimiter("\n");
            
            for(i = 0; i < 4; ++i) {
                line = scanner.nextLine().split(" ");
                
                for(j = 0; j < 4; ++j) {
                    if(line[j].equals("0")) {
                        grid[i][j] = false;
                    } else {
                        grid[i][j] = true;
                    }
                }
            }
            return EReadStatus.SUCCESS;
            
        } catch ( IOException e) {
            System.out.println("can't open file");
            return EReadStatus.CAN_NOT_OPEN_FILE;
            
        } finally {
            
            return EReadStatus.FAIL;
            
        }
    }
    
    public void rotate(ERotateAngle angle) {
        switch(angle) {
            case A_QUARTER -> rotate90();
            case HALF -> rotate180();
            case THREE_QUARTERS -> rotate270();
            default -> {
            }
        }
    }
    
    public int calSum() {
        int res = 0;
        
        for(int i = 0; i < 4; ++i) {
            for(int j = 0; j < 4; ++j) {
                if(grid[i][j]) ++res;
            }
        }
        
        return res;
    }
    
    public boolean checkValid() {
        boolean[][] cp = this.grid.clone();
        boolean[][] origin = this.grid.clone();
        for(int i = 0 ;i < 3; ++i) {
            rotate90();
            
            for(int j = 0 ; j < 4; ++j) {
                for(int k = 0; k < 4; ++k) {
                    if(cp[j][k] && this.grid[j][k]) {
                        this.grid = origin;
                        return false;
                    } else if(!cp[j][k] && this.grid[j][k]) {
                        cp[j][k] = true;
                    } else {}
                }
            }
        }
        
        this.grid = origin;
        return true; 
    }
    
    public int[][] orderedMatrix() {
        int[][] res = new int[4][4];
        int counter = 0;
        
//        init order of matrix
        for(int i = 0 ;i < 4; ++i) {
            for(int j = 0 ;j < 4; ++j) {
                res[i][j] = -1;
            }
        }
        
        for(int i = 0 ; i < 4; ++i) {
            
            
            for(int j = 0; j < 4; ++j) {
                for(int k = 0; k < 4; ++k) {
                    if(this.grid[j][k])
                        res[j][k] = counter++;
                }
            }
            
            rotate90();
        }
        
        return res;
    }
    
    private void rotate90() {
        // Initialize the result matrix with zeros
        boolean[][] res = new boolean[4][4];

        // Flip the matrix counterclockwise using nested loops
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                res[4 - j - 1][i] = this.grid[i][j];
            }
        }

        // Copy the result matrix back to mat
        for (int i = 0; i < 4; i++) {
            System.arraycopy(res[i], 0, this.grid[i], 0, 4);
        }
    }
    
    private void rotate180() {
        for (int i = 0; i < 4 / 2; i++) {
            for (int j = 0; j < 4; j++) {
                boolean temp = this.grid[i][j];
                this.grid[i][j] = this.grid[4 - i - 1][4 - j - 1];
                this.grid[4 - i - 1][4 - j - 1] = temp;
            }
        }

        // Handle the middle row if the matrix 
        // has odd dimensions
        if (4 % 2 != 0) {
            int mid = 4 / 2;
            for (int j = 0; j < 4 / 2; j++) {
                boolean temp = this.grid[mid][j];
                this.grid[mid][j] = this.grid[mid][4 - j - 1];
                this.grid[mid][4 - j - 1] = temp;
            }
        }
    }
    
    private void rotate270() {
        rotate180();
        rotate90();
    }
}
