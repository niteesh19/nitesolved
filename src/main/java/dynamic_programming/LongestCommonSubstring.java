package dynamic_programming;

import base.DoubleLinkedList;

/**
 * Longest Common Substring Given two strings ‘X’ and ‘Y’, find the length of the longest common
 * substring.
 *
 * <p>Examples :
 *
 * <p>Input : X = “GeeksforGeeks”, y = “GeeksQuiz” Output : 5 Explanation: The longest common
 * substring is “Geeks” and is of length 5.
 *
 * <p>Input : X = “abcdxyz”, y = “xyzabcd” Output : 4 Explanation: The longest common substring is
 * “abcd” and is of length 4.
 */
public class LongestCommonSubstring {
  /*
  The idea is to find the length of the longest common suffix for all substrings of both strings and store these lengths in a table.
  Time Complexity: O(m*n)
  Auxiliary Space: O(m*n)

  */
  public static int longestSubstr(String first, String second) {
    int maxLen = 0;
    int fl = first.length();
    int sl = second.length();
    int[][] table = new int[fl + 1][sl + 1];
    //table: 8*10 matrix
    /*    x, a, y, z, a, a, b, c, d
      [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
    a [0, 0, 1, 0, 0, 1, 1, 0, 0, 0]
    b [0, 0, 0, 0, 0, 0, 0, 2, 0, 0]
    c [0, 0, 0, 0, 0, 0, 0, 0, 3, 0]
    d [0, 0, 0, 0, 0, 0, 0, 0, 0, 4]
    x [0, 1, 0, 0, 0, 0, 0, 0, 0, 0]
    y [0, 0, 0, 1, 0, 0, 0, 0, 0, 0]
    z [0, 0, 0, 0, 2, 0, 0, 0, 0, 0]
     */
    for (int i = 1; i <= fl; i++) {
      for (int j = 1; j <= sl; j++) {
        if (first.charAt(i - 1) == second.charAt(j - 1)) {
          table[i][j] = table[i - 1][j - 1] + 1;
          if (table[i][j] > maxLen) maxLen = table[i][j];
        }
      }
    }

    return maxLen;
  }

  // Driver Code
  public static void main(String[] args) {

    System.out.println("Length of Longest Common Substring is " + longestSubstr("abcdxyz", "xayzaabcd"));
    String first = "OldSite:GeeksforGeeks.org";
    String second = "NewSite:GeeksQuiz.com";
    System.out.println("Length of Longest Common Substring is " + longestSubstr(first, second));
  }

  /*
    The space used by the above solution can be reduced to O(2*n).
    A variable end is used to store the ending point of the longest common substring in string X
    and variable maxlen is used to store the length of the longest common substring.
   */
  static String LCSubStr(String X, String Y) {
    // Find length of both the Strings.
    int m = X.length();
    int n = Y.length();

    // Variable to store length of longest
    // common subString.
    int result = 0;

    // Variable to store ending point of
    // longest common subString in X.
    int end = 0;

    // Matrix to store result of two
    // consecutive rows at a time.
    int[][] len = new int[2][m];

    // Variable to represent which row of
    // matrix is current row.
    int currRow = 0;

    // For a particular value of i and j,
    // len[currRow][j] stores length of longest
    // common subString in String X[0..i] and Y[0..j].
    for (int i = 0; i <= m; i++) {
      for (int j = 0; j <= n; j++) {
        if (i == 0 || j == 0) {
          len[currRow][j] = 0;
        } else if (X.charAt(i - 1) == Y.charAt(j - 1)) {
          len[currRow][j] = len[1 - currRow][j - 1] + 1;
          if (len[currRow][j] > result) {
            result = len[currRow][j];
            end = i - 1;
          }
        } else {
          len[currRow][j] = 0;
        }
      }

      // Make current row as previous row and
      // previous row as new current row.
      currRow = 1 - currRow;
    }

    // If there is no common subString, print -1.
    if (result == 0) {
      return "-1";
    }

    // Longest common subString is from index
    // end - result + 1 to index end in X.
    return X.substring(end - result + 1, result);
  }
}
