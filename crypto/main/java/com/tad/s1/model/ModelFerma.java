/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tad.s1.model;

import com.tad.s1.input.InputOnePair;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;

/**
 *
 * @author Never
 */
public class ModelFerma implements Model{
    private InputOnePair input;
    private BigInteger t;
    private BigInteger w;
    private BigInteger d;
    
    private String result;
    
    public ModelFerma(InputOnePair inp) {
        this.input = inp;
        this.result = "";
    }
    
    private void findTandW() {
        BigInteger N = input.getN();
        BigInteger n = N.sqrt().add(BigInteger.ONE);
        int counter = 1;
        while(true) {
            this.t = n.add(BigInteger.valueOf(counter++));
            this.w = this.t.multiply(this.t).subtract(N);
            if(this.w.sqrt().multiply(this.w.sqrt()).compareTo(this.w) == 0) break;
        }
    }
    
    private void findD() {
        BigInteger p = this.t.add(this.w.sqrt());
        BigInteger q = this.t.subtract(this.w.sqrt());
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        
        BigInteger x = new BigInteger(String.valueOf(input.getE()));
        BigInteger y = new BigInteger("-1"); // Exponent
        BigInteger z = new BigInteger(String.valueOf(phi));
        
        this.d = x.modPow(y, z);
    }
    
    private void decode() throws UnsupportedEncodingException {
        ArrayList<String> encoded = this.input.getC();
        for(int i = 0; i < encoded.size(); ++i) {
            BigInteger temp = BigInteger
                    .valueOf(
                            Long.parseLong( 
                                    encoded.get(i)
                            )
                    ).modPow(
                            this.d, 
                            input.getN()
                    );
            String t = new String(temp.toByteArray(), "windows-1251");
            if(t.charAt(0) == 0) t = t.substring(1);
            this.result += t;
        }
    }
    
    @Override
    public void solve() {
        try{
            findTandW();
            findD();
            decode();
        } catch (UnsupportedEncodingException e) {
        }

    }
    
    public String getResult() {
        return this.result;
    }
}
