/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.tad.s1;

import com.tad.s1.model.ModelreEnc;
import com.tad.s1.input.InputOnePair;
import com.tad.s1.input.InputTwoPair;
import com.tad.s1.model.ModelKeylessRead;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author Never
 */
public class S1 {

    public static void main(String[] args) throws UnsupportedEncodingException {
        InputTwoPair inp = new InputTwoPair();
        
        inp.read("in3.txt");
        System.out.println(inp.toString());
//        ModelFerma solver = new ModelFerma(inp);
//        ModelreEnc solver = new ModelreEnc(inp);
          ModelKeylessRead solver = new ModelKeylessRead(inp);
        solver.solve();
        System.out.println(solver.getResult());
        Output.write("out.txt", solver.getResult());
    }
}
