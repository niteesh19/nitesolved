package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by gouthamvidyapradhan on 06/08/2017. Given a binary tree, return the inorder traversal
 * of its nodes' values.
 *
 * <p>For example: Given binary tree [1,null,2,3], 1 \ 2 / 3 return [3,1,2].
 *  Inorder (left, root, right)
 * <p>Note: Recursive solution is trivial, could you do it iteratively?
 */
public class BinaryTreeInorderTraversal {
  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }

    @Override
    public String toString() {
      return "TreeNode{" +
          "val=" + val +
          ", left=" + left +
          ", right=" + right +
          '}';
    }
  }

  /*   */
  public static void main(String[] args) throws Exception {
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);
    root.left.left = new TreeNode(4);
    root.left.right = new TreeNode(5);
    List<Integer> result = new BinaryTreeInorderTraversal().inorderTraversal(root);
    System.out.println(result);
    new BinaryTreeInorderTraversal().printInorder(root);
  }

  /* Given a binary tree, print its nodes in inorder*/
  void printInorder(TreeNode node)
  {
    if (node == null) {
      System.out.print("X ");
      return;
    }
    /* first recur on left child */
    printInorder(node.left);
    /* then print the data of node */
    System.out.print(node.val + " ");
    /* now recur on right child */
    printInorder(node.right);
  }

  public List<Integer> inorderTraversal(TreeNode root) {
    Stack<TreeNode> stack = new Stack<>();
    TreeNode curr = root;
    List<Integer> result = new ArrayList<>();
    while (curr != null || !stack.isEmpty()) {
      while (curr != null) {
        stack.push(curr);
        curr = curr.left;
      }
      curr = stack.pop();
      result.add(curr.val);
      curr = curr.right;
    }
    return result;
  }
}
