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

    private static HuffmanCodeTree text;
    private static String str = null;

    public static void main(String[] args) {
        run();
    }

    static void run(){
        Scanner kbd = new Scanner(System.in);
        str = input("Please enter series of characters or paragraph as basis for creating Huffman Code");
        // Instantiate a HuffmanCodeTree to generate the tree
        text = new HuffmanCodeTree(str);
        //prints the huffman code table
        text.printHuffmanCodeTable();
        // print the percentage of storage savings
        text.printPercentageSave();
        do {
            int choice = getChoice();
            switch (choice) {
                case 1 -> {
                    // create another base text
                    str = input("Please enter series of characters or paragraph as basis for creating Huffman Code");
                    text = new HuffmanCodeTree(str);
                    text.printHuffmanCodeTable();
                    text.printPercentageSave();
                }
                case 2 -> {
                    // convert text to huffman code
                    do {
                        try{
                            str = input("Make sure that the text you enter is in the base text you enter.");
                            String res = text.cnvrtTxtToHuffmanCode(str);
                            // print the generated huffman code
                            System.out.printf("Huffman code equivalent of text %s: %s\n", str, res);
                            System.out.print("Press enter to proceed");
                            String x = kbd.nextLine();
                            break;
                        }catch (InputMismatchException e){
                            System.out.println("Check your input");
                        } catch (Exception e1){
                            e1.printStackTrace();
                        }

                    }while(true);

                }
                case 3 -> {
                    // convert huffman code to text
                    do {
                        try {
                            str = input("Make sure that the code you enter is in the converted code generated and do not have space");
                            String res = text.cnvrtCodeToTxt(str);
                            // print the converted text
                            System.out.printf("Text equivalent of Huffman code %s: %s\n", str, res);
                            System.out.print("Press enter to proceed");
                            String x = kbd.nextLine();
                            break;
                        }catch (HasSpaceException e){
                            System.out.println("Enter a code that has no space");
                        } catch (Exception exception){
                            exception.printStackTrace();
                        }

                    }while (true);

                }
                default -> {
                    System.out.println("Thank you!!");
                    System.exit(0);
                }
            }
        } while (true);

    }

    /**
     * Get the input of the user
     * @param msg message for direction on what to input
     * @return string input text
     */
    static String input(String msg){
        Scanner kbd = new Scanner(System.in);
        String base;
        System.out.println(msg);
        System.out.print("Enter here: ");
        base = kbd.nextLine();
        return base;
    }

    /**
     * Main menu of the program
     */
    static void mainMenu(){
        System.out.println("""
                
                ================ Main Menu ================
                |   1. Create new basis for Huffman Code   |
                |   2. Convert Text to Huffman Code        |
                |   3. Convert Huffman Code to Text        |
                |   4. Exit                                |
                ============================================""");
    }

    /**
     * Get and check the choice if it is range of the maximum and minimum choice
     * @return int for the choice
     */
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

}
