package midlab2;

public class HuffmanLeafNode implements HuffmanBaseNode {
    private char element; //node element
    private int wt;

    public HuffmanLeafNode (char el, int weight ){
        element = el;
        wt = weight;
    }

    char val() {
        return element;
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

    @Override
    public int wt() {
        return wt;
    }
}
