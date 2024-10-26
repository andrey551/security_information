/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tad.s1.model;

import com.tad.s1.input.InputOnePair;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

/**
 *
 * @author Never
 */
public class ModelreEnc implements Model{
    private InputOnePair input;
    private String result;
    
    public ModelreEnc(InputOnePair inp) {
        this.result = "";
        this.input = inp;
    }
    
    @Override
    public void solve() {
        try{
            for(String stri : input.getC()) {
                BigInteger y = BigInteger.valueOf(Long.parseLong(stri));
                BigInteger yi = y.modPow(input.getE(), input.getN());
                BigInteger res = BigInteger.ZERO;
                while(y.compareTo(yi) != 0) {
                    res = yi;
                    yi = yi.modPow(input.getE(), input.getN());
                }
                String temp = new String(res.toByteArray(), "windows-1251");
                if(temp.charAt(0) == 0) temp = temp.substring(1);
                this.result += temp.substring(0, temp.length());
            }
        } catch(UnsupportedEncodingException e) {}

    }
    
    public String getResult() {
        return this.result;
    }
}
