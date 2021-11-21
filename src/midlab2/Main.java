/*
    Algorithm:
        1. Get series of characters as a basis of Huffman code
        2. Create the Huffman code of the input
        3. Print the table of generated Huffman code and percentage of storage savings
        4. Print menu
            4.1 Create new basis of Huffman code
                  repeat step 1 to 4
            4.2 Convert Text to Huffman code
                  if a character in input not in the series of characters
                        print an error message
                    else
                        convert text to Huffman code
                        print the code
              repeat step 4
            4.3 Convert Huffman code to text
                  if code cannot be converted to text
                        print an error message
                  else if code has space
                        print error message
                  else
                        convert code to text
                  repeat step 4
 */
package midlab2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static HuffmanCodeTree text = new HuffmanCodeTree();
    private static String str = null;

    public static void main(String[] args) {

        run();
    }

    static void run(){
        str = input("Please enter series of characters or paragraph as basis for creating Huffman Code");
        //
        text.getBaseText(str);
        do {
            int choice = getChoice();
            switch (choice) {
                case 1 -> {
                    str = input("Please enter series of characters or paragraph as basis for creating Huffman Code");
                    text.getBaseText(str);
                }
                case 2 -> {
                    str = input("Make sure that the text you enter is in the base text you enter.");
                    System.out.println(text.cnvrtTextToHuffmanCode(str));
                }
                case 3 -> {
                    str = input("Make sure that the code you enter is in the converted code generated");
//                    text.decode(str);
                }
                default -> System.exit(0);
            }
        } while (true);

    }

    static String input(String msg){
        Scanner kbd = new Scanner(System.in);
        String base;
        System.out.println(msg);
        System.out.print("Enter here: ");
        base = kbd.nextLine();
        return base;
    }

    static void mainMenu(){
        System.out.println("""
                
                ================ Main Menu ================
                |   1. Create new basis for Huffman Code   |
                |   2. Convert Text to Huffman Code        |
                |   3. Convert Huffman Code to Text        |
                |   4. Exit                                |
                ============================================""");
    }

    static int getChoice(){
        Scanner kbd = new Scanner(System.in);
        int choice = 0;
        do {
            try {
                mainMenu();
                System.out.print("Enter your choice here: ");
                choice = Integer.parseInt(kbd.nextLine());
                if (choice < 1 || choice > 4)
                    throw new InputMismatchException();
                return choice;
            }catch (NumberFormatException exception){
                System.out.println("Please enter an integer");
            } catch (InputMismatchException e){
                System.out.println("Enter integer from 1 to 4 only");
            }
        }while (true);
    }

//    static void printCode(){
//        text.getHuffmanCodes().forEach((chr, code) ->
//                System.out.println(chr + " " + code));
//    }

}
