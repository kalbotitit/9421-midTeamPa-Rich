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

public class Main {

    public static void main(String[] args) {
        Notation n = new Notation();
        try {
            System.out.println("before");

            String nt = n.infixToPostfix("A+B*(C^D)");
            System.out.println(nt);
            System.out.println("after");
        }catch (InputMismatchException x){
            System.out.println("Input has space");
        }


        System.exit(0);
    }

    
}
