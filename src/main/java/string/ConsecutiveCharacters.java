package string;

/**
 * The power of the string is the maximum length of a non-empty substring that contains only one unique character.
 * Given a string s, return the power of s.
 * Example 1:
 *
 * Input: s = "leetcode"
 * Output: 2
 * Explanation: The substring "ee" is of length 2 with the character 'e' only.
 *
 * Input: s = "abbcccddddeeeeedcba"
 * Output: 5
 * Explanation: The substring "eeeee" is of length 5 with the character 'e' only.
 */
public class ConsecutiveCharacters {

  public static int consCharsPower(String s) {
    char storedChar = 0;
    int power = 0;
    int count = 0;
    for (int i = 0; i < s.length(); i++) {
      if (storedChar == s.charAt(i))
        count++;
      else {
        storedChar = s.charAt(i);
        count = 1;
      }
      if (count > power)
        power = count;
    }
    return power;
  }

  public static void main(String[] args) {
    System.out.println(consCharsPower("leetcode"));
    System.out.println(consCharsPower("abbcccddddeeeeedcba"));
    System.out.println(consCharsPower("1"));
    System.out.println(consCharsPower(""));
  }
}
