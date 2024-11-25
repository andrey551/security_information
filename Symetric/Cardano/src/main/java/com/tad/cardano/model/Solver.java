
package com.tad.cardano.model;

/**
 *
 * @author Dau Cong Tuan Anh
 */
public class Solver {
    private Grid gridMatr;
    private Input input;
    private Output output;
    
    public Solver(){
        input = new Input();
        output = new Output();
    };
    
    public Solver(String gridPath) {
        this();
        gridMatr = new Grid(gridPath);
    }
    
    public void encode(String inPath, String outPath) {
        String encoded = "";
        char[][] tempMatrix = new char[4][4];
        int step = this.gridMatr.calSum() * 4;
        
        
        input.read(inPath);
        
        int inpLength = this.input.getInput().length();
        System.out.println("length: " + step);
        int[][] orderMatrix= this.gridMatr.orderedMatrix();
        
        for(int i = 0 ; i < 4; ++i) {
            for(int j = 0 ; j < 4; ++j) {
                System.out.print(orderMatrix[i][j] + " ");
            }
            
            System.out.println();
        }
        
        for(int i = 0; i < inpLength; i += step) {
            String subStr = this.input.getInput().substring(i, min(i + step, inpLength));
            
            for(int j = 0; j < 4; ++j) {
                for(int k = 0 ;k < 4; ++k) {
                    if(orderMatrix[j][k] != -1 && orderMatrix[j][k] < subStr.length()) {
                        tempMatrix[j][k] = subStr.charAt(orderMatrix[j][k]);
                    }
                }
            }
            
            System.out.println(subStr);
            for(int j = 0; j < 4; ++j) {
                for(int k = 0 ;k < 4; ++k) {
                    System.out.print(tempMatrix[j][k] + " ");
                }
                System.out.println();
            }
            
            for(int j = 0 ; j < 4; ++j) {
                for(int k = 0 ; k < 4; ++k) {
                    if(tempMatrix[j][k] != 0) 
                        encoded += tempMatrix[j][k];
                }
            }
            
            tempMatrix = new char[4][4];
        }
        
        System.out.println("output:" + encoded);
        Output.write(encoded, outPath);
    }
    
    public void decode(String inPath, String outPath) {
        String decoded = "";
        char[] temp = new char[16];
        int step = this.gridMatr.calSum() * 4;
        
        
        input.read(inPath);
        
        int inpLength = this.input.getInput().length();
        System.out.println("length: " + inpLength);
        int[][] orderMatrix= this.gridMatr.orderedMatrix();
        
        for(int i = 0 ; i < 4; ++i) {
            for(int j = 0 ; j < 4; ++j) {
                System.out.print(orderMatrix[i][j] + " ");
            }
            
            System.out.println();
        }
        
        for(int i = 0; i < inpLength; i += step) {
            String subStr = this.input.getInput().substring(i, min(i + step, inpLength));
            
            for(int j = 0; j < 4; ++j) {
                for(int k = 0 ;k < 4; ++k) {
                    if(orderMatrix[j][k] != -1 && orderMatrix[j][k] < subStr.length()) {
                        temp[orderMatrix[j][k]] += subStr.charAt(j * 4 + k);
                    }
                }
            }
            
            System.out.println(temp);
            
            for(int j = 0; j < step; ++j) {
                decoded += temp[j];
            }
            
            temp = new char[16];
        }
        
        System.out.print("decoded: " + decoded);
    }
    
    private int min(int a, int b) {
        return a < b ? a : b; 
    }
}
