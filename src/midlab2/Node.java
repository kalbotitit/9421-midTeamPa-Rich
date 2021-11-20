package midlab2;

public class Node implements Comparable<Node> {

    private int freq;
    private Node left;
    private Node right;

    public Node(){
    }

    public Node(Node l, Node r){
        this.freq = l.getFreq() + r.getFreq();
        this.left = l;
        this.right = r;
    }

    @Override
    public int compareTo(Node n) {
        return Integer.compare(freq, n.getFreq());
    }

    public boolean isLeaf(){
        return false;
    }

    public int getFreq() {
        return freq;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }
}
