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

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

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
    public void evaluate() {    // evaluate method
        String pfExpression = notation;
        List<String> elements = Arrays.asList(pfExpression.split("\\s+"));
        MyStack<Integer> resultStack = new MyStack<>();

        elements.stream().forEach((string) -> { // forEach method
            Integer op1 = null, op2 = null, val = null; // variable declaration
            try {
                resultStack.push(Integer.valueOf(string)); // invokes the push method from the MyStack class
                //otherwise, if element is not numerical, switch to the corresponding operator
            } catch (NumberFormatException e) { // catches the number format exception error
                op1 = resultStack.pop(); // invokes the pop method from the MyStack class
                op2 = resultStack.pop(); // invokes the pop method from the MyStack class
                val = 0;    // set the value of val to 0
                evaluateOperator(string,val, op1, op2, resultStack);
            }
            printPostfixRow(string, op2, op1, val, printStack(resultStack));    // invokes printPostfixRow method
        }); // end of forEach
        printPostfixExpression(pfExpression, resultStack); // invoke printPostfixExpression method
    }   // end of evaluate method
    public void evaluateOperator(String string, Integer val, Integer op1, Integer op2, MyStack resultStack){    // evaluateOperator method
        switch (string) {   // switch-case statement
            //evaluate based on operator
            case "+":
                val = op2 + op1;
                resultStack.push(val);
                break;
            case "-":
                val = op2 - op1;
                resultStack.push(val);
                break;
            case "*":
                val = op2 * op1;
                resultStack.push(val);
                break;
            case "/":
                val = op2 / op1;
                resultStack.push(val);
                break;
            case "^":
                val = (int) Math.pow(op2, op1);
                resultStack.push(val);
                break;
        } // end of switch-case statement
    }   // end of evaluateOperator method
    public void printPostfixRow(String string, Integer op2, Integer op1, Integer val, String resultStack) { // printPostfixRow method
        if (op1 == null && op2 == null && val == null) {
            System.out.printf("%-10s %-10s %-10s %-10s %-10s\n", string, "", "", "", resultStack);
        } else {
            System.out.printf("%-10s %-10s %-10s %-10s %-10s\n", string, op2, op1, val, resultStack);
        } // return
    }   // end of printPostfixRow method
    public void printPostfixExpression(String pfExpression, MyStack resultStack){  // printPostfixExpression method
        System.out.print("\nThe postfixExpression " + pfExpression + " is equal to ");
        resultStack.toString();
        // return
    }   // end of printPostfixExpression method
    public String printStack(MyStack<Integer> resultStack) {    // printStack method
        MyStack<Integer> printStack = new MyStack<>();  // variable declaration
        String print = "";  // variable declaration
        if (resultStack.isEmpty()) {    // if statement
            return null;
        }   // end of if statement
        while (resultStack.size() > 0) {    // while statement
            printStack.push(resultStack.pop());
        } // end of while statement
        while (printStack.size() > 0) { // while statement
            Integer element = printStack.pop();
            if (printStack.isEmpty()) { // if statement
                print += element;
            }   // end of if statement
            if (!printStack.isEmpty()) {    // if statement
                print += element + ",";
            }   // end of if statement
            resultStack.push(element);
        }   // end of while statement
        return print;
    }   // end of printStack method
    public String getNotation(){return notation;}
    public void setNotation(String notation){this.notation = notation;}
}
