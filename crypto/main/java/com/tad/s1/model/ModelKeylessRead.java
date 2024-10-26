
package com.tad.s1.model;

import com.tad.s1.model.Type.Triplet;
import com.tad.s1.input.InputTwoPair;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dau Cong Tuan Anh
 */
public class ModelKeylessRead implements Model{
    private InputTwoPair inp;
    private String result;
    
    public ModelKeylessRead(InputTwoPair inp) {
        this.inp = inp;
        this.result = "";
    }
    
    private Triplet extendedEuclid(BigInteger a, BigInteger b) {
        if(b.equals(BigInteger.ZERO))
             return new Triplet(a, BigInteger.ONE, BigInteger.ZERO);
        Triplet res = extendedEuclid(b, a.mod(b));
        return new Triplet(res.getA(), res.getC(),res.getB().subtract( a.divide(b).multiply(res.getC())));
        
    }
    
    private void decode(Triplet key) throws UnsupportedEncodingException {
        ArrayList<String> encoded1 = this.inp.getC1();
        ArrayList<String> encoded2 = this.inp.getC2();
        for(int i = 0; i < encoded1.size(); ++i) {
            BigInteger temp1 = BigInteger
                    .valueOf(
                            Long.parseLong( 
                                    encoded1.get(i)
                            )
                    ).modPow(
                            key.getB(), 
                            this.inp.getN()
                    );
            
            BigInteger temp2 = BigInteger
                    .valueOf(
                            Long.parseLong( 
                                    encoded2.get(i)
                            )
                    ).modPow(
                            key.getC(),
                            inp.getN()
                    );
            
            BigInteger temp = temp1.multiply(temp2).mod(inp.getN());
            String t = new String(temp.toByteArray(), "windows-1251");
            if(t.charAt(0) == 0) t = t.substring(1);
            System.out.println("Decode C[" + i + "]: " + t);
            this.result += t;
        }
    }
    
    @Override
    public void solve() {
        BigInteger a = inp.getE1();
        BigInteger b = inp.getE2();
        Triplet key = extendedEuclid(a, b);
        System.out.println("r: " + key.getB() + " s: " + key.getC());
        
        try {
            decode(key);
        } catch (UnsupportedEncodingException ex) {
        }
        
    }
    
    public String getResult() {
        return this.result;
    }

}
