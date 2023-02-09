package graph;

// Java program to print DFS
// traversal from a given
// graph
import java.util.*;

// This class represents a
// directed graph using adjacency
// list representation
class DFSGraph {
  private final int V; // No. of vertices

  // Array of lists for
  // Adjacency List Representation
  private final ArrayList<LinkedList<Integer>> adj;

  // Constructor
  DFSGraph(int v)
  {
    V = v;
    adj = new ArrayList<LinkedList<Integer>>();
    for (int i = 0; i < v; ++i)
      adj.add(i, new LinkedList<Integer>());
  }

  // Function to add an edge into the graph
  void addEdge(int v, int w)
  {
    adj.get(v).add(w); // Add w to v's list.
  }

  // A function used by DFS
  void DFSRecur(int v, boolean[] visited)
  {
    // Mark the current node as visited and print it
    visited[v] = true;
    System.out.print(v + " ");

    // Recur for all the vertices adjacent to this
    // vertex
    for (int n : adj.get(v)) {
      if (!visited[n])
        DFSRecur(n, visited);
    }
  }

  // The function to do DFS traversal. It uses recursive
  // DFSUtil()
  void DFS()
  {
    // Mark all the vertices as not visited(set as
    // false by default in java)
    boolean[] visited = new boolean[V];

    // Call the recursive helper function to print DFS
    // traversal starting from all vertices one by one
    for (int i = 0; i < V; ++i)
      if (!visited[i])
        DFSRecur(i, visited);
  }

  public static void main(String[] args)
  {
    DFSGraph g = new DFSGraph(4);

    g.addEdge(0, 1);
    g.addEdge(0, 2);
    g.addEdge(1, 2);
    g.addEdge(2, 0);
    g.addEdge(2, 3);
    g.addEdge(3, 3);

    System.out.println("Following is Depth First Traversal");

    g.DFS();
  }
}
