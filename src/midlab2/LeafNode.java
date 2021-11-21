package midlab2;

public class LeafNode extends InternalNode{

    private int freq;
    private char character;

    public LeafNode(){}

    public LeafNode(char c, int f){
        freq = f;
        character = c;
    }

    public char getCharacter() {
        return character;
    }
}
