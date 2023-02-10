package string;

import java.util.Arrays;

/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 *
 * <p>Solution: O(N x M) where N is the length of the given array and M is the max_length of a
 * string.
 *
 * <p>The idea is to sort the array of strings and find the common prefix of the first and last
 * string of the sorted array. because definitely either first or last will have the shortest word
 * count
 */
public class LongestCommonPrefix {

  public static void main(String[] args) throws Exception {
    String[] A = {"geeksforgeeks", "geeks", "geek", "geezer"};
    System.out.println(new LongestCommonPrefix().longestCommonPrefixOptimised(A));
  }

  public String longestCommonPrefix(String[] strs) {
    if (strs.length == 0) return "";
    String result = strs[0];
    for (int i = 1; i < strs.length; i++) {
      String s = strs[i];
      for (int j = 0; j < result.length(); j++) {
        if (j >= s.length() || result.charAt(j) != s.charAt(j)) {
          result = result.substring(0, j);
          break;
        }
      }
    }
    return result;
  }

  //time complexity: O(n*logn)
  //Please note that comparison of two strings would take at most O(MAX) time, and for sorting n strings,
  // we would need O(MAX * n * log n ) time.
  public String longestCommonPrefixOptimised(String[] a) {
    int size = a.length;

    /* if size is 0, return empty string */
    if (size == 0) return "";

    if (size == 1) return a[0];

    /* sort the array of strings */
    Arrays.sort(a);
    //a: ["geek", "geeks", "geeksforgeeks", "geezer"]

    /* find the minimum length from first and last string */
    int end = Math.min(a[0].length(), a[size - 1].length());
    //end = 4 ("geek" length)

    /* find the common prefix between the first and last string */
    //optimise it with i < end ; that is the shortest word count
    int i = 0;
    while (i < end && a[0].charAt(i) == a[size - 1].charAt(i))
      i++;
    //i=3
    String pre = a[0].substring(0, i);
    return pre;
  }
}
