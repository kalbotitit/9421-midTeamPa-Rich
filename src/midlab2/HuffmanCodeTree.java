package midlab2;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class HuffmanCodeTree {

    private Node root;
    private String text;
    private Map<Character, Integer> charFreq;
    private Map<Character, String> huffmanCodes;

    public HuffmanCodeTree(){}

    public HuffmanCodeTree(String t){
        text = t;
        fillCharFreqMap();
        huffmanCodes = new HashMap<>();
    }

    public void getBaseText(String t){
        text = t;
        fillCharFreqMap();
        huffmanCodes = new HashMap<>();
    }

    public void fillCharFreqMap(){
        charFreq = new HashMap<>();
        for (char chr : text.toCharArray()){
            Integer freq = charFreq.get(chr);
            charFreq.put(chr, freq != null ? freq + 1 : 1);
        }
    }

    public String encode(){
        Queue<Node> queue = new PriorityQueue<>();
        charFreq.forEach((chr, freq) ->
                queue.add(new LeafNode(freq, chr)));

        while (queue.size() > 1)
            queue.add( new Node(queue.poll(), queue.poll()));

        genHuffmanCode(root = queue.poll(), "");
        return getEncodedText();
    }

    private void genHuffmanCode(Node node, String code){
        if (node instanceof LeafNode){
            huffmanCodes.put(((LeafNode) node).getElement(), code);
            return;
        }
        genHuffmanCode(node.getLeft(), "0");
        genHuffmanCode(node.getRight(), "1");
    }

    private String getEncodedText(){
        String str = null;
        for (char chr : text.toCharArray())
            str += huffmanCodes.get(chr);

        return str;
    }

    public String decode(String text){
        String str = null;
        Node curr = root;
        for(char chr : text.toCharArray()){
            curr =  chr == '0' ? curr.getLeft() : curr.getRight();
            if (curr instanceof LeafNode){
                str += (((LeafNode) curr).getElement());
                curr = root;
            }
        }
        return str;
    }

    public Map<Character, String> getHuffmanCodes(){
        return huffmanCodes;
    }

    private void printCode(){
        huffmanCodes.forEach((chr, code) ->
                System.out.println(chr + " " + code));
    }

    public static void main(String[] args) {
        HuffmanCodeTree obj = new HuffmanCodeTree();
        obj.getBaseText("aaabbbbcccddd");

        System.out.println(obj.encode());

        obj.printCode();

        System.out.println(obj.decode(obj.encode()));

    }

}
