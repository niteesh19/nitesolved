/**
 * [NITE SOLVED]
 */
package tree;

import java.util.Stack;

// Ternary Expression to Binary Tree
public class TernaryExprToBT {

  // Class to represent Tree node
  static class Node
  {
    char data;
    Node left, right;

    public Node(char item)
    {
      data = item;
      left = null;
      right = null;
    }
  }

  // Function to convert Ternary Expression to a Binary
  // Tree. It return the root of tree
  Node convertExpression_NW(char[] expression, int i)
  {
    // Base case
    if (i >= expression.length)
      return null;

    // store current character of expression_string
    // [ 'a' to 'z']
    Node root = new Node(expression[i]);

    // Move ahead in str
    ++i;

    // if current character of ternary expression is '?'
    // then we add next character as a left child of
    // current node
    if (i < expression.length && expression[i]=='?')
      root.left = convertExpression_NW(expression, i+1);

      // else we have to add it as a right child of
      // current node expression.at(0) == ':'
    else if (i < expression.length)
      root.right = convertExpression_NW(expression, i+1);

    return root;
  }

  public Node ternaryToTree(String s) {
    if (s.length() == 0) {
      return null;
    }

    Node root = new Node(s.charAt(0));
    Stack<Node> stack = new Stack<>();
    stack.push(root);

    for (int i = 1; i < s.length(); i++) {
      if (s.charAt(i) == '?') {
        Node node = stack.peek(); //Get the top immediate elem whose left needs to be set
        node.left = new Node(s.charAt(i + 1));
        stack.push(node.left);  //Push in the stack so that we can pop twice when ":" comes or peek when "?"
      } else if (s.charAt(i) == ':') {
        stack.pop();
        Node node = stack.pop();  //Pop twice to reach the root whose right needs to be set
        node.right = new Node(s.charAt(i + 1));
        stack.push(node.right); //Push in the stack so that we can pop twice when ":" comes or peek when "?"
      }
    }

    return root;
  }

  // function print tree
  public void printTree( Node root)
  {
    if (root == null)
      return;

    System.out.print(root.data +" ");
    printTree(root.left);
    printTree(root.right);
  }

  // Driver program to test above function
  public static void main(String args[])
  {
    String exp = "a?b?c:d:e";
    TernaryExprToBT tree = new TernaryExprToBT();
    char[] expression=exp.toCharArray();
//    Node root = tree.convertExpression_NW(expression, 0);
    Node root = tree.ternaryToTree(exp);
    tree.printTree(root) ;
  }
}


