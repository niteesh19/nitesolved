package graph;

import java.util.ArrayList;
import java.util.LinkedList;

public class WeightedGraphPrint {
  static class Edge {
    int source;
    int destination;
    int weight;

    public Edge(int source, int destination, int weight) {
      this.source = source;
      this.destination = destination;
      this.weight = weight;
    }
  }

  static class Graph {
    int vertices;
    ArrayList<LinkedList<Edge>> adjacencyList;

    Graph(int vertices) {
      this.vertices = vertices;
      adjacencyList = new ArrayList<LinkedList<Edge>>();
      //initialize adjacency lists for all the vertices
      for (int i = 0; i <vertices ; i++) {
        adjacencyList.add(i,new LinkedList<>());
      }
    }

    public void addEgde(int source, int destination, int weight) {
      Edge edge = new Edge(source, destination, weight);
      adjacencyList.get(source).addFirst(edge); //for directed graph
    }

    public void printGraph(){
      for (int i = 0; i <vertices ; i++) {
        LinkedList<Edge> list = adjacencyList.get(i);
        for (Edge edge : list) {
          System.out.println("vertex-" + i + " is connected to " +
              edge.destination + " with weight " + edge.weight);
        }
      }
    }
  }
  public static void main(String[] args) {
    int vertices = 6;
    Graph graph = new Graph(vertices);
    graph.addEgde(0, 1, 4);
    graph.addEgde(0, 2, 3);
    graph.addEgde(1, 3, 2);
    graph.addEgde(1, 2, 5);
    graph.addEgde(2, 3, 7);
    graph.addEgde(3, 4, 2);
    graph.addEgde(4, 0, 4);
    graph.addEgde(4, 1, 4);
    graph.addEgde(4, 5, 6);
    graph.printGraph();
  }
}