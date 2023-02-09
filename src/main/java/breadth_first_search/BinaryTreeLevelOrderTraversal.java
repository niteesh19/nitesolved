/**
 * [NITE SOLVED]
 */
package breadth_first_search;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 13/03/2017. Given a binary tree, return the level order
 * traversal of its nodes' values. (ie, from left to right, level by level).
 *
 * <p>For example: Given binary tree [3,9,20,null,null,15,7], 3 / \ 9 20 / \ 15 7 return its level
 * order traversal as: [ [3], [9,20], [15,7] ]
 */
public class BinaryTreeLevelOrderTraversal {
  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  // Iterative function to perform level order traversal on the tree - O(N)
  public static void levelOrder(TreeNode root)
  {
    // return if the tree is empty
    if (root == null) {
      return;
    }

    // create an empty queue and push the root node
    Queue<TreeNode> que = new LinkedList<>();
    que.add(root);
    que.add(null);  //Optional: for printing new line after every levels
    // loop till stack is empty
    while (!que.isEmpty())
    {
      // pop a node from the stack and print it
      TreeNode curr = que.poll();

      //Optional if: for printing new line after every levels
      if(curr == null) {
        if (que.isEmpty()) return;
        que.add(null);
        System.out.println();
        continue;
      }

      System.out.print(curr.val + " ");
      // the left child must be pushed first so that the left child is processed first (FIFO order)
      // push the left child of the popped node into the queue
      if (curr.left != null) {
        que.add(curr.left);
      }

      // push the right child of the popped node into the queue
      if (curr.right != null) {
        que.add(curr.right);
      }
    }
  }

  public static void main(String[] args) throws Exception {
    TreeNode root = new TreeNode(2);
    root.left = new TreeNode(3);
    root.right = new TreeNode(4);
    root.right.right = new TreeNode(5);
    root.right.left = new TreeNode(4);
    root.right.left.right = new TreeNode(8);
    root.right.left.left = new TreeNode(7);
    root.right.left.left.right = new TreeNode(10);
    root.right.left.left.left = new TreeNode(9);

    levelOrder(root);
  }

}
