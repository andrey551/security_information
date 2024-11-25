
package Model;

/**
 *
 * @author Dau Cong Tuan Anh
 */
public class Block {
    private short[][] plainText = new short[4][4];
    private SBox box;
    
    public Block() {
        
    }
    
    public Block(short[][] plainText, SBox sBox) {
        this.plainText = plainText;
        this.box = sBox;
    }
    
    public void SubBytes() {
        this.plainText = this.box.encode(this.plainText);
    }
    
    public void ShiftRows() {
        for(int i = 0; i < 4; ++i) {
                shiftRow(i, i);
        }
    }
    
    public void MixColumns() {
        byte[][] RijndaelMatrix = {
                                    {2,3,1,1},
                                    {1,2,3,1},
                                    {1,1,2,3},
                                    {3,1,1,2}
                                  };
        
        for(int i = 0; i < 4; ++i) {
            short[] tmp = {
                this.plainText[0][i], 
                this.plainText[1][i], 
                this.plainText[2][i], 
                this.plainText[3][i]};
            
            for(int j = 0 ; j <4; ++j) {
                this.plainText[j][i] = 0;
                for(int k = 0; k < 4; ++k) {
                    this.plainText[j][i] += RijndaelMatrix[j][k] * tmp[k];
                }
                this.plainText[j][i] = (short)(this.plainText[j][i] % 256);
            }
        }
    }
    
    public void addRoundKey(Key key) {
        for(int i = 0 ;i < 4; ++i) {
            for(int j = 0; j < 4; ++j) {
                this.plainText[i][j] = (short) (this.plainText[i][j] ^ key.at(i, j)% 256) ;
            }
        }
    }
    
    public void doRound(Key key) {
        this.SubBytes();
        this.ShiftRows();
        this.MixColumns();
        this.addRoundKey(key);
    } 
    
    public void cypher(Key key) {
        for(int i = 0; i < 10; ++i) {
            doRound(key);
            key = key.generateNext(this.box);
        }
    }
    
    
    public void shiftRow(int row, int time) {
        short[] tmp = this.plainText[row];
        for(int i = 0; i < 4 - time; ++i) {
            this.plainText[row][i] = tmp[i + time];
        }
        
        for(int i = 4 - time; i < 4; ++i) {
            this.plainText[row][i] = tmp[i + time - 4];
        }
    } 
    
    @Override
    public String toString() {
        String s = "";
        for(int i = 0; i < 4; ++i) {
            for(int j = 0; j < 4; ++j) {
                s += (char)this.plainText[i][j];
            }
        }
        
        return s;
    }

}
