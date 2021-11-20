package midlab2;

public class HuffmanInternalNode implements HuffmanBaseNode {
    private int wt;        //node element
    private HuffmanBaseNode right;
    private HuffmanBaseNode left;

    public HuffmanInternalNode(HuffmanBaseNode rt, HuffmanBaseNode lt, int weight){
        right = rt;
        left = lt;
        wt = weight;
    }

    HuffmanBaseNode getRight(){
        return right;
    }

    HuffmanBaseNode getLeft(){
        return left;
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public int wt() {
        return wt;
    }
}
