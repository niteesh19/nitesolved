package stack;

// Java Program to find the solution of the arithmetic using the stack

import java.io.*;
import java.util.*;

// Evaluate Reverse Polish Notation expression
class ReversePolishNotation {
  public static void main(String[] args) {
    // String Input
    String[] s = {"2", "1", "+", "3", "*"};
    System.out.println(evalRPN(s));   // 9

    s = new String[]{"4", "13", "5", "/", "+"};
    System.out.println(evalRPN(s));    // 6
  }

  public static int evalRPN(String[] tokens) {
    int returnValue = 0;
    String operators = "+-*/";
    Stack<String> stack = new Stack<>();
    for (String t : tokens) {
      if (!operators.contains(t)) { //push to stack if it is a number
        stack.push(t);
      } else {
        //pop numbers from stack if it is an operator
        int a = Integer.parseInt(stack.pop());
        int b = Integer.parseInt(stack.pop());
        switch (t) {
          case "+":
            stack.push(String.valueOf(a + b));
            break;
          case "-":
            stack.push(String.valueOf(b - a));
            break;
          case "*":
            stack.push(String.valueOf(a * b));
            break;
          case "/":
            stack.push(String.valueOf(b / a));
            break;
        }
      }
    }
    returnValue = Integer.parseInt(stack.pop());
    return returnValue;
  }

}

