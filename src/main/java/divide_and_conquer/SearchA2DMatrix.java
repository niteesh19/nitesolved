package divide_and_conquer;

/**
 * Created by gouthamvidyapradhan on 09/03/2017. Write an efficient algorithm that searches for a
 * value in an m x n matrix. This matrix has the following properties:
 *
 * <p>Integers in each row are sorted in ascending from left to right. Integers in each column are
 * sorted in ascending from top to bottom. For example,
 *
 * <p>Consider the following matrix:
 *
 * <p>[ [1, 4, 7, 11, 15], [2, 5, 8, 12, 19], [3, 6, 9, 16, 22], [10, 13, 14, 17, 24], [18, 21, 23,
 * 26, 30] ] Given target = 5, return true.
 *
 * <p>Given target = 20, return false.
 */
public class SearchA2DMatrix {
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[][] matrix = {
      {1, 3, 5, 7, 9}, // 1, 3, 5, 7, 9
      {2, 4, 6, 8, 10}, // 2, 4, 6, 8, 10
      {11, 13, 15, 17, 19}, // 11, 15, 17, 18, 19
      {12, 14, 16, 18, 20}, // 13, 20, 21, 22, 23
      {21, 22, 23, 24, 25} // 14, 25, 26, 27, 28
    };
    new SearchA2DMatrix().naiveSearchMatrix(matrix, 13);
    new SearchA2DMatrix().optimizedSearchMatrix(matrix, 11);
  }

  private int naiveSearchMatrix(int[][] mat, int x)
  {
    int n = mat.length;
    if (n == 0)
      return -1;

    // traverse through the matrix
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++)
        // if the element is found
        if (mat[i][j] == x) {
          System.out.print("Element found at ("
              + i + ", " + j
              + ")\n");
          return 1;
        }
    }

    System.out.print(" Element not found");
    return 0;
  }


  /*
  Approach: The simple idea is to remove a row or column in each comparison until an element is found.
  Start searching from the top-right corner of the matrix. There are three possible cases.

The given number is greater than the current number: This will ensure that all the elements in the current row
are smaller than the given number as the pointer is already at the right-most elements and the row is sorted.
Thus, the entire row gets eliminated and continues the search for the next row.
Here, elimination means that a row needs not be searched.

The given number is smaller than the current number: This will ensure that all the elements in the current column
are greater than the given number. Thus, the entire column gets eliminated and continues the search
for the previous column, i.e. the column on the immediate left.

The given number is equal to the current number: This will end the search.
   */
  private boolean optimizedSearchMatrix(int[][] matrix, int target) {
    if (matrix.length == 0) return false;
    int M = matrix.length;
    int N = matrix[0].length;
    int r = 0, c = N - 1;
    while (r < M && c >= 0) {
      if (matrix[r][c] == target) {
        System.out.print("Element found at ("
            + r + ", " + c
            + ")\n");
        return true;
      }
      else if (target < matrix[r][c]) --c;
      else if (target > matrix[r][c]) r++;
    }
    return false;
  }
}
