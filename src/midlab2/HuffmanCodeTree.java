package midlab2;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.PriorityQueue;
import java.util.Queue;

public class HuffmanCodeTree {

    private InternalNode root;
    private String input;
    // Hash Map is used for faster retrieval of values
    // store the freq of a character in the input
    private HashMap<Character, Integer> charFreq = new HashMap<>();
    // store the generated huffman code from the huffman code tree
    private HashMap<Character, String> huffmanCodes = new HashMap<>();

    /**
     * Constructor
     */
    public HuffmanCodeTree(){}

    public void getBaseText(String input){
        this.input = input;

        // fill the hash map of the frequency of every character
        charFreqMap();
    }

    private void charFreqMap(){
        for (int c = 0; c < input.length(); c++){
            Integer frequency = charFreq.get(input.charAt(c));

            // check if the character is new it assigns 1
            // else add 1 to current frequency of the character
            frequency = frequency == null ? 1 : frequency + 1;

            charFreq.put(input.charAt(c), frequency);
        }
    }

    public String cnvrtTextToHuffmanCode(String text){
        String result = "";
        for (char c : text.toCharArray()){
            if (huffmanCodes.get(c) == null)
                throw new InputMismatchException("wala sa base text");
            result += huffmanCodes.get(c);

        }
        return result;
    }

    public String encodeHuffmanCode(){
        PriorityQueue<InternalNode> q = new PriorityQueue<>();
        charFreq.forEach((chr, freq) ->
                q.add(new LeafNode(chr, freq))
        );

        while (q.size() > 1)
            q.add(new InternalNode(q.poll(), q.poll()));

        genHuffmanCode(root = q.poll(), "");

        return printCode();
    }

    private void genHuffmanCode(InternalNode n, String code){
        if (n instanceof LeafNode){
            huffmanCodes.put(((LeafNode) n).getCharacter(), code);
            return;
        }
        genHuffmanCode(n.getLeft(), code.concat("0"));
        genHuffmanCode(n.getRight(), code.concat("1"));
    }

    private String printCode(){
        String str = "";
        for (char chr : input.toCharArray())
            str += huffmanCodes.get(chr);

        return str;
    }

}
