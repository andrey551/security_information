
package Model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Dau Cong Tuan Anh
 */
public class Output {
    public static boolean write(
                              String content, 
                              String fileName) {
        try {
            try (BufferedWriter writer = 
                    new BufferedWriter(new FileWriter(fileName))) {
                writer.write(content);
            }
            
            return true;
        } catch( IOException e) {
            e.printStackTrace();
        }
        
        return true;
    } 
}
