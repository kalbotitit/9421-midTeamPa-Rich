/*
        Algorithm:
        1. Scan the infix notation
        2. If there is space in infix notation return error
        3. If current infix character is operand
            Append it to postfix string
        4. Else the current infix character is operator
            If the operator has greater precedence than at the top of stack
                Push it to stack
            Else
                Pop the stack until the top of stack is less precedence than the operator
        5. If the current infix character is equal to opening parenthesis
            Push it to stack
        6. If the current infix character is equal to closing parenthesis
            Pop the stack until the opening parenthesis
        7. Repeat step 3 - 7 until the last infix character
        8. Pop the remaining operator in the stack, append it to postfix string
        9. Print the postfix string
 */
package midlab1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        MyStack<Integer> resultStack = new MyStack<>();
        boolean invalid = false;
        Notation n = new Notation();
        int min = 0;
        int max = 4;
        int choice = 0;
        String nt = "";
        String output = "";
        while(choice != 3) {
        showMenu();
        choice = isValidChoice(min, max);
            switch (choice) {
                case 1:
                    do {
                        try {
                            System.out.print("Enter infix expression (Make sure to enter with spaces\ne.g. 2+3*4): ");
                            nt = scan.nextLine();
                            invalid = false;
                            output = n.infixToPostfix(nt);
                        } catch (InputMismatchException x) {
                            invalid = true;
                            System.out.println("Input has space");
                        }
                    } while (invalid);
                    System.out.println("Before: " + nt);
                    System.out.print("After: " + output);
                    break;
                case 2:
                    do {
                        try {
                            System.out.print("Enter postfix expression (Make sure to enter with spaces\ne.g. 2 3 4 * +): ");
                            nt = scan.nextLine();
                            invalid = false;
                            n = new Notation(nt);
                            output = n.evaluate(resultStack);
                        } catch (StackException x) {
                            invalid = true;
                            System.out.println("Invalid Expression!");
                        } catch (InputMismatchException i) {
                            invalid = true;
                            System.out.println("Input has no space");
                        }
                    } while (invalid);
                    System.out.print(output + " ");
                    resultStack.toString();
                    break;
                case 3:
                    System.exit(0);
                    break;
            }
        }
    }
    public static void showMenu(){
        System.out.println("\n +----------------- Menu -----------------------+");
        System.out.println(" |                                              |");
        System.out.println(" |   1. Infix                                   |");
        System.out.println(" |   2. Postfix                                 |");
        System.out.println(" |   3. Quit the program                        |");
        System.out.println(" +----------------------------------------------+");
    }

    public static int isValidChoice(int min, int max){
        Scanner keyboard = new Scanner(System.in);
        int choice = 0;
        boolean valid;
        do{
            try{
                System.out.print("Enter choice: ");
                choice = Integer.parseInt(keyboard.nextLine());
                valid = false;
                if (choice <= min && choice >= max) {
                    valid = true;
                }

            }catch(NumberFormatException exception){
                System.out.print("Please enter an integer.");
                valid = true;
            }
            }while (valid);
        return choice;
    }
}
