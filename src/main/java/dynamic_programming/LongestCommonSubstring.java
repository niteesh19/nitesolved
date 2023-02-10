package dynamic_programming;

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

  //further optimisation: In the above approach, we are only using the last row of the 2-D array only
  these algorithm can be altered to have only an O(n) storage requirement.
  By reassigning array references between two 1D arrays, this can be done without copying the state data from one array to another.
  */
  public static int longestSubstr(String first, String second) {
    int maxLen = 0;
    int fl = first.length();
    int sl = second.length();
    int[][] table = new int[fl + 1][sl + 1];

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
    String first = "OldSite:GeeksforGeeks.org";
    String second = "NewSite:GeeksQuiz.com";

    System.out.println("Length of Longest Common Substring is " + longestSubstr(first, second));
    System.out.println("Length of Longest Common Substring is " + longestSubstr("abcdxyz", "xyzabcd"));
  }
}
