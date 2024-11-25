
import Model.Container;
import Model.Key;
import Model.Output;
import Model.SBox;


/**
 *
 * @author Never
 */
public class Rijndael {

    public static void main(String[] args) {
        SBox box = new SBox();
        box.read("sbox.txt");
        Key key = new Key();
        key.read("key.txt");
        Container container = new Container(key, box);
        container.read("input.txt");
        String out = container.encode();
        System.out.println("output: " + out);
        Output.write(out, "output.txt");
    }
}
