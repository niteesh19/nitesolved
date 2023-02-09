/**
 * [NITE SOLVED]
 */
package tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Created by gouthamvidyapradhan on 28/07/2018. Given a binary tree, return the preorder traversal
 * of its nodes' values.
 *
 * <p>Example:
 *
 * <p>Input: [1,null,2,3] 1 \ 2 / 3
 *  Pre-order (root, left, right)
 * <p>Output: [1,3,2]
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 */
public class BinaryTreePreorderTraversal {
  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public static void main(String[] args) throws Exception {
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);
    root.left.left = new TreeNode(4);
    root.left.right = new TreeNode(5);
    preorderIterative(root);
    System.out.println();
    new BinaryTreePreorderTraversal().printPreorder(root);
  }

  /* Given a binary tree, print its nodes in preorder*/
  void printPreorder(TreeNode node)
  {
    if (node == null) {
      System.out.print("X ");
      return;
    }
    /* first print data of node */
    System.out.print(node.val + " ");
    /* then recur on left sutree */
    printPreorder(node.left);
    /* now recur on right subtree */
    printPreorder(node.right);
  }

  // Iterative function to perform preorder traversal on the tree
  public static void preorderIterative(TreeNode root)
  {
    // return if the tree is empty
    if (root == null) {
      return;
    }

    // create an empty stack and push the root node
    Stack<TreeNode> stack = new Stack();
    stack.push(root);

    // loop till stack is empty
    while (!stack.empty())
    {
      // pop a node from the stack and print it
      TreeNode curr = stack.pop();

      System.out.print(curr.val + " ");

      // push the right child of the popped node into the stack
      if (curr.right != null) {
        stack.push(curr.right);
      }

      // push the left child of the popped node into the stack
      if (curr.left != null) {
        stack.push(curr.left);
      }

      // the right child must be pushed first so that the left child
      // is processed first (LIFO order)
    }
  }
}
