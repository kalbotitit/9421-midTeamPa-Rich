/*
    Algorithm:
    infixToPostfix():
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

public class Notation {

    private String notation;

    /** Default constructor */
    Notation(){
        notation = "A+B(C*D)";
    }

    Notation(String n){
        notation = n;
    }

    /**
     * Return an integer according to the precedence of operators
     * @param o operator
     * @return an int
     */
    private int precedence(char o){
        if (o == '+' || o == '-') return 1;
        else if (o == '*' || o == '/') return 2;
        else if (o == '^') return 3;
        else return -1;
    }

    /**
     * Check if the input has no space
     */
    private void noSpace(){
        for (int c = 0; c < notation.length(); c++){
            if (notation.charAt(c) == ' ') throw new InputMismatchException();
        }
    }

    /**
     * Convert the infix notation to postfix notation
     * @return a string of postfix notation
     */
    public String infixToPostfix(String n){
        notation = n;
        String postfix = "";
        MyStack<Character> stack = new MyStack<>();
        noSpace();

        for (int c = 0; c < n.length(); c++){
            char currChar = n.charAt(c);

            if (Character.isLetterOrDigit(currChar))
                postfix += currChar; // the current character is operand; append it to the postfix string

            // current character is equal to opening parenthesis
            else if (currChar == '(') {
                stack.push(currChar);
                postfix += " "; // for readability purpose
            }

            // current character is equal to closing parenthesis
            else if (currChar == ')'){
                while (!stack.isEmpty() && stack.top() != '(') {
                    postfix += stack.pop();
                    postfix += " "; // for readability purpose
                }

                stack.pop(); // pop the opening parenthesis in stack
            }

            else{// operator is the current character
                // operator has less precedence than the operator in top of the stack
                while (!stack.isEmpty() && precedence(currChar) <= precedence(stack.top()))
                    postfix += stack.pop();
                // the precedence of current character is greater than the top operator in the stack
                // push it to the stack
                stack.push(currChar);
            }

        } // end of for loop

        // append and pop the remaining operator in the stack
        while (!stack.isEmpty())
            postfix += stack.pop();

        return postfix;
    }// end of infixToPostfix method

}
