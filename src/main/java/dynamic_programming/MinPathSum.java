/**
 * [NITE SOLVED]
 */
package dynamic_programming;

import java.util.Arrays;
/*
  Minimum Path Sum
  Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right,
  which minimizes the sum of all numbers along its path.
  Note: You can only move either down or right at any point in time.
 */

public class MinPathSum {

  // Brute Force method,
  // Time complexity -> O(2^(m+n))
  // Space complexity -> O(m+n)
  public int usingRecursion(int[][] grid, int i, int j) {
    //Case when 2d array is exceeding boundary
    if (i == grid.length || j == grid[0].length) {
      System.out.print(" X");
      return Integer.MAX_VALUE;
    }
    //Ending last elem in the 2d array where we have to reach
    if (i == grid.length - 1 && j == grid[0].length - 1) {
      System.out.print(" " + grid[i][j]);
      return grid[i][j];
    }
    int rowDown = usingRecursion(grid, i + 1, j);
    int colRight = usingRecursion(grid, i, j + 1);
    System.out.println();
    System.out.print("i=" + i + " j=" + j);
    System.out.print(" rowDown:" + rowDown);
    System.out.print(" colRight:" + colRight);
    int computed = grid[i][j] + Math.min(rowDown, colRight);
    System.out.print(" computed:" + computed);
    // Returns either the rowDown or colRight values, bottom-up fashion, for further computation
    return computed;
  }

  public int usingDP(int[][] grid) {
    int n = grid.length;  //row
    int m = grid[0].length;   //column

    //initialize 1st row since only right movement happens
    for (int j = 1; j < m; j++) {
      grid[0][j] = grid[0][j - 1] + grid[0][j];
    }
    //Now grid[0] first row: [1, 4, 5]
    //initialize 1st column since only down movement happens
    for (int i = 1; i < n; i++) {
      grid[i][0] = grid[i - 1][0] + grid[i][0];
    }
    //Now grid: [1, 3, 7] first column
    for (int i = 1; i < n; i++) {
      for (int j = 1; j < m; j++) {
        //find minimum of top and left block cost and add it to the node itself and replace
        grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
      }
    }
    //return the last elem in the grid as it will have the shortest path value stored finally <-- DP
    return grid[n - 1][m - 1];
  }

  public int minPathSum(int[][] grid) {
//    return usingRecursion(grid, 0, 0);
    return usingDP(grid);
  }

  public static void main(String[] args) {
//    int[][] grid = {
//        {1, 3, 1, 2},
//        {2, 3, 2, 3},
//        {4, 3, 1, 2}
//    };
    int[][] grid = {
        {1, 3, 1},
        {2, 3, 2},
        {4, 3, 1}
    };
    System.out.println(Arrays.deepToString(grid));
    int ans = new MinPathSum().minPathSum(grid);
    System.out.println();
    System.out.println(ans);
  }
}
