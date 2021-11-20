package midlab2;

public class LeafNode extends Node {

    private int freq;
    private char element;

    public LeafNode(){
    }

    public LeafNode(int f, char e){
        freq = f;
        element = e;
    }


    public int getFreq(){
        return freq;
    }

    public char getElement(){
        return element;
    }
    
    public boolean isLeaf(){
        return true;
    }

}
