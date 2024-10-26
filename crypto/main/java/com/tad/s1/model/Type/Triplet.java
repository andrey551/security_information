
package com.tad.s1.model.Type;

import java.math.BigInteger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Dau Cong Tuan Anh
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Triplet {
    private BigInteger a;
    private BigInteger b;
    private BigInteger c;
}
