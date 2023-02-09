package tree;

// Java program to print all nodes at a distance k from leaf
// A binary tree node

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class NodeAtKDistanceLeaf {
  static class Node {
    int data;
    Node left, right;

    Node(int item)
    {
      data = item;
      left = right = null;
    }
    @Override
    public String toString() {
      return "TreeNode{" +
          "data=" + data +
          ", left=" + left +
          ", right=" + right +
          '}';
    }
  }
  Node root;

  /* Given a binary tree and a nuber k, print all nodes that are k
  distant from a leaf*/
  int printKDistantFromLeaf(Node node, int k)
  {
    if (node == null)
      return -1;
    int lk = printKDistantFromLeaf(node.left, k);
    int rk = printKDistantFromLeaf(node.right, k);
    boolean isLeaf = lk == -1 && lk == rk;
    if (lk == 0 || rk == 0 || (isLeaf && k == 0))
      System.out.print(" " + node.data);
    if (isLeaf && k > 0)
      return k - 1; // leaf node
    if (lk > 0 && lk < k)
      return lk - 1; // parent of left leaf
    if (rk > 0 && rk < k)
      return rk - 1; // parent of right leaf

    return -2;
  }

  // Function to check if a given node is a leaf node or not
  public static boolean isLeaf(Node node) {
    return (node.left == null && node.right == null);
  }

  // Recursive function to find all nodes at a given distance from leaf nodes
  public static void leafNodeDistance(Node node, List<Node> path,
                                      Set<Node> set, int dist)
  {
    // base case: empty tree
    if (node == null) {
      return;
    }

    // if a leaf node is found, insert the node at a distance `dist` from the leaf node into the set
    if (isLeaf(node) && path.size() >= dist)
    {
      set.add(path.get(path.size() - dist));
      return;
    }

    // include the current node in the current path
    path.add(node);

    // recur for the left and right subtree
    leafNodeDistance(node.left, path, set, dist);
    leafNodeDistance(node.right, path, set, dist);

    // remove the current node from the current path
    path.remove(node);
  }

  // Find all distinct nodes at a given distance from leaf nodes
  public static void leafNodeDistanceProcess(Node node, int dist)
  {
    // list to store root-to-leaf path
    List<Node> path = new ArrayList<>();

    // create an empty set to store distinct nodes at a given
    // distance from leaf nodes
    Set<Node> set = new HashSet<>();

    // find all nodes
    leafNodeDistance(node, path, set, dist);

    // print output
    for (Node e: set) {
      System.out.print(e.data + " ");
    }
  }

  // Driver program to test the above functions
  public static void main(String args[])
  {
    NodeAtKDistanceLeaf tree = new NodeAtKDistanceLeaf();

    /*      1
         2       3
        4  5   6   7
                 8
     */
    /* Let us construct the tree shown in above diagram */
    tree.root = new Node(1);
    tree.root.left = new Node(2);
    tree.root.right = new Node(3);
    tree.root.left.left = new Node(4);
    tree.root.left.right = new Node(5);
    tree.root.right.left = new Node(6);
    tree.root.right.right = new Node(7);
    tree.root.right.left.right = new Node(8);

    System.out.println(" Nodes at distance 2 are :");
//    leafNodeDistanceProcess(tree.root, 2);
    tree.printKDistantFromLeaf(tree.root, 2);
  }
}

