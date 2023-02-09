package array2d;

import java.io.*;

class MaxXYPoint {

  // Function to find the point having max X and Y coordinates
  static void findMaxPoint(int arr[][]) {
    // Initialize maxX and maxY
    int maxX = Integer.MIN_VALUE;
    int maxY = Integer.MIN_VALUE;

    // Length of the given array
    int n = arr.length;

    // Get maximum X & Y coordinates
    for (int i = 0; i < n; i++) {
      maxX = Math.max(maxX, arr[i][0]);
      maxY = Math.max(maxY, arr[i][1]);
    }

    // Check if the required point
    // i.e., (maxX, maxY) is present
    for (int i = 0; i < n; i++) {

      // If point with maximum X and
      // Y coordinates is present
      if (maxX == arr[i][0] && maxY == arr[i][1]) {

        System.out.println("(" + maxX + ", " + maxY + ")");
        return;
      }
    }

    // If no such point exists
    System.out.println(-1);
  }

  // Driver Code
  public static void main(String[] args) {
    // Given array of points
    int arr[][] = new int[][]{
        {1, 2}, {2, 1},
        {3, 4}, {4, 3},
        {5, 5}};

    // Print answer
    findMaxPoint(arr);
  }
}
