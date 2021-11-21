package midlab2;

/**
 * Class for both internal and leaf node of Huffman Code tree
 */
public class HuffmanNode {
    private char ch;
    private int freq;
    private HuffmanNode left = null, right = null;

    /**
     * Constructor for leaf node
     * @param ch character from input text
     * @param freq frequency of character in text
     */
    HuffmanNode(char ch, int freq)
    {
        this.ch = ch;
        this.freq = freq;
    }

    /**
     * Constructor for Internal Node
     * @param ch blank character
     * @param freq sum of two lowest frequency character
     * @param left left node
     * @param right right node
     */
    public HuffmanNode(char ch, int freq, HuffmanNode left, HuffmanNode right) {
        this.ch = ch;
        this.freq = freq;
        this.left = left;
        this.right = right;
    }

    /**
     * Get the character
     * @return character
     */
    public char getCh() {
        return ch;
    }

    /**
     * Get the frequency of a character
     * @return int frequency
     */
    public int getFreq() {
        return freq;
    }

    /**
     * Get the left node
     * @return left node
     */
    public HuffmanNode getLeft() {
        return left;
    }

    /**
     * Get the right node
     * @return right node
     */
    public HuffmanNode getRight() {
        return right;
    }
}
