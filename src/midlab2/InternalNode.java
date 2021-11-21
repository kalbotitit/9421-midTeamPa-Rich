package midlab2;

public class InternalNode implements Comparable<InternalNode>{

    private int freq;
    private InternalNode left;
    private InternalNode right;

    public InternalNode(){}

    public InternalNode(InternalNode l, InternalNode r){
        this.left = l;
        this.right = r;
        this.freq = l.getFreq() + r.getFreq();
    }

//    public InternalNode(int freq){
//        this.freq = freq;
//    }

    public int getFreq() {
        return freq;
    }

    public InternalNode getLeft() {
        return left;
    }

    public InternalNode getRight() {
        return right;
    }

    @Override
    public int compareTo(InternalNode n) {
        return Integer.compare(freq, n.getFreq());
    }
}
