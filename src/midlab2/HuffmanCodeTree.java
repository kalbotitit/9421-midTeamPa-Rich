package midlab2;

import java.util.*;

/**
 * Class for creating Huffman Code using tree data structure
 * Hash map was used for storing and retrieving character frequency and huffman codes
 */
public class HuffmanCodeTree {

    private HuffmanNode root;
    private String text;
    private Map<Character, Integer> charFreqs;
    private Map<Character, String> huffmanCodes = new HashMap<>();
    private PriorityQueue<HuffmanNode> nodeQueue;

    /**
     * Constructor
     * @param text base text for creating huffman code
     */
    HuffmanCodeTree(String text){
        this.text = text;
        // Generate the Huffman code Tree
        genHuffmanTree();
    }

    /**
     * Create the Huffman Coding tree
     */
    private void genHuffmanTree(){
        countCharFreq();

        // Instantiate priority queue to store the leaf nodes of Huffman tree
        // The leaf node with lowest frequency is the priority
        nodeQueue = new PriorityQueue<>(
                (l, r) -> l.getFreq() - r.getFreq());
        // Add the leaf node to the priority queue
        for (Map.Entry<Character, Integer> entry : charFreqs.entrySet()) {
            nodeQueue.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }
        // Create the internal nodes of the huffman tree
        while (nodeQueue.size() > 1){
            // Removes two nodes with lowest frequency in priority queue
            HuffmanNode left = nodeQueue.poll();
            HuffmanNode right = nodeQueue.poll();

            // Internal nodes
            int sum = left.getFreq() + right.getFreq();
            nodeQueue.add(new HuffmanNode('\0', sum, left, right));
        }

        // Points to the root of the Huffman Code tree
        root = nodeQueue.peek();
        // Generate the huffman code for every character
        genHuffmanCode(root, "");

    }

    /**
     * Counts the frequency of every character in the base text
     */
    private void countCharFreq(){
        charFreqs = new HashMap<>();
        for (char c : text.toCharArray()) {
            // check if the character is new
            if (!charFreqs.containsKey(c))
                charFreqs.put(c, 0);
            // When the character already added
            // the frequency of the character increases by 1
            charFreqs.put(c, charFreqs.get(c) + 1);
        }
    }

    /**
     * Convert the Huffman code to corresponding character
     * @param code huffman code
     * @return string equivalent of code
     * @throws HasSpaceException when the input code has space included
     */
    public String cnvrtCodeToTxt(String code) throws HasSpaceException {
        StringBuilder codeToTxt = new StringBuilder();
        hasSpace(code);
        HuffmanNode curr = root;
        // traverse sequentially through the input code
        // then generate the corresponding text of code input
        for (char chr : code.toCharArray()){
            curr = chr == '0' ? curr.getLeft() : curr.getRight();
            if (curr.getLeft() == null && curr.getRight() == null) {
                codeToTxt.append(curr.getCh());
                curr = root;
            }
        }
        return codeToTxt.toString();
    }

    /**
     * Check if the input code has space included
     * @param code huffman code
     * @throws HasSpaceException when input has space
     */
    private void hasSpace(String code) throws HasSpaceException {
        // Sequentially check the string for space
        for (char chr : code.toCharArray())
            if (chr == ' ')
                throw new HasSpaceException("Input has space");
    }

    /**
     * Convert string text to correspoding huffman code
     * @param text string input
     * @return huffman code of text
     */
    public String cnvrtTxtToHuffmanCode(String text){
        StringBuilder codeToTxt = new StringBuilder();
        // Traverse sequentially the input text
        // then generate the corresponding huffman code for text
        for (char chr : text.toCharArray()) {
            if (huffmanCodes.get(chr) == null)
                throw new InputMismatchException();
            codeToTxt.append(huffmanCodes.get(chr));
        }

        return codeToTxt.toString();
    }

    /**
     * Generate the huffman code for every character in base text
     * Hash Map holds the codes for faster retrieval of codes
     * @param curr current root
     * @param str '0' for left node and '1' for right node
     */
    private void genHuffmanCode(HuffmanNode curr , String str){
        if (curr == null) return;

        // leaf node is encountered
        if (curr.getLeft() == null && curr.getRight() == null) huffmanCodes.put(curr.getCh(), str);

        // to generate the huffman code
        // assign 0 to left node and 1 to right node
        genHuffmanCode(curr.getLeft(), str.concat("0"));
        genHuffmanCode(curr.getRight(), str.concat("1"));
    }

    /**
     * Print the Huffman Code Table
     * It includes the characters, huffman code, number of bits and frequency
     */
    public void printHuffmanCodeTable(){
        System.out.println("Text: ");
        System.out.println(text);
        // create a table that print the characters, huffman code
        // number of bits and frequency
        System.out.printf("%-15s%-15s%-20s%-15s\n", "Character", "Huffman Code", "Number of Bits", "Frequency");
        for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
            System.out.printf("%-15s%-15s%-20s%-15s\n", entry.getKey(), entry.getValue(), entry.getValue().length(), charFreqs.get(entry.getKey()));
        }
        String gen = cnvrtTxtToHuffmanCode(text);
        System.out.println("Huffman Code equivalent of base text: " + gen );
    }

    /**
     * Print the percentage of storage savings
     */
    public void printPercentageSave(){
        // calculate the number of bits on the original text
        int numOrigBits = text.length() * 7;
        // calculate the number of bits on the compressed text
        int totalCompressedBits = 0;
        for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
            totalCompressedBits += (charFreqs.get(entry.getKey()) * entry.getValue().length());
        }
        // calculate for the percentage of storage savings
        double percentage = (double) (numOrigBits - totalCompressedBits) / numOrigBits * 100;
        //print the result
        System.out.println("Uncompressed size of text: " + numOrigBits);
        System.out.println("Compressed size of text: " + totalCompressedBits);
        System.out.printf("Percentage of storage saving: %.2f%c\n", percentage, '%');
    }

}
