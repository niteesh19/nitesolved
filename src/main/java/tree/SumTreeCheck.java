/**
 * [NITE SOLVED]
 */
package tree;

// Java program to check a tree is sum tree
/*
          26
        /   \
      10     3
    /    \     \
  4      6      3

  is a sumTree
  O(N)
 */

class SumTreeCheck {

  static class Node {
    int data;
    Node left, right;

    Node(int item) {
      data = item;
      left = right = null;
    }
  }

  // Recursive function to check if a given binary tree is a sum tree or not
  public static int isSumTree(Node root)
  {
    // base case: empty tree
    if (root == null) {
      return 0;
    }

    // special case: leaf node
    if (root.left == null && root.right == null) {
      return root.data;
    }

    //Post-order traversal
    int left = isSumTree(root.left);
    int right = isSumTree(root.right);

    // if the root's value is equal to the sum of all elements present in its
    // left and right subtree
    if (left != Integer.MIN_VALUE && right != Integer.MIN_VALUE && root.data == left + right) {
      return 2 * root.data;
    }

    return Integer.MIN_VALUE;
  }

  // Driver code
  public static void main(String[] args) {
    Node root;
    root = new Node(26);
    root.left = new Node(10);
    root.right = new Node(3);
    root.left.left = new Node(4);
    root.left.right = new Node(6);
    root.right.right = new Node(3);
    if (isSumTree(root) > 0) {
      System.out.println("The given tree is a SumTree");
    } else {
      System.out.println("The given tree is not a SumTree");
    }
  }
}


