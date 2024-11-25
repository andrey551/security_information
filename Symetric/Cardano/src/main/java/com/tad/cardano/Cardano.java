
package com.tad.cardano;

import com.tad.cardano.model.Solver;

/**
 *
 * @author Never
 * https://www.youtube.com/watch?v=jRESSvNdgmY
 */
public class Cardano {

    public static void main(String[] args) {
        Solver solver = new Solver("grid.txt");
        solver.encode("input.txt", "output.txt");
    }
}
