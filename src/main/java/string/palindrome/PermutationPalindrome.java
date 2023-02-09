package string.palindrome;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
Check if a permutation of a string can become a palindrome

Input    | Output
mmo      | True
yakak    | True
travel   | False

 */

public class PermutationPalindrome {

  private static boolean canMakePalindrome(String s) {
    Map<Character, Integer> countChars = new HashMap<>();

    // Count the occurrences of each character
    for (char c : s.toCharArray()) {
      Integer count = countChars.get(c);
      if (count == null) {
        count = 1;
      } else {
        count = count + 1;
      }
      countChars.put(c, count);
    }

    boolean hasOdd = false;
    for (int count : countChars.values()) {
      if (count % 2 == 1) {
        if (hasOdd) {
          // Found two chars with odd counts - return false;
          return false;
        } else {
          // Found the first char with odd count
          hasOdd = true;
        }
      }
    }

    // Haven't found more than one char with an odd count
    return true;
  }

  /*
  The above implementation has a built in inefficiency.
  I don't think the first iteration over the string can be avoided, but there's no real reason to keep a count of all the occurrences -
  it's enough to just keep track of those with the an odd count. For this usecase,
  it's enough to keep track of each character we encounter (e.g., with a Set), and remove it when we encounter it again.
  In the worst case, where all the characters in the string are different, the performance is comparable,
  but in the common case, where there are several occurrences of each character, this implementation improves both time
  and memory complexity of the second loop (which is now reduced to a single condition)

  Set is better than List because an Arraylist will dynamically expand and grow. So for remove(), it has to shift all elements.
  Set is internally implemented as Map.
   */
  private static boolean canMakePalindromeOptimised(String s) {
    Set<Character> oddChars = new HashSet<>();

    // Go over the characters
    for (char c : s.toCharArray()) {
      // Record the encountered character:
      if (!oddChars.add(c)) {
        // If the char was already encountered, remove it -
        // this is an even time we encounter it
        oddChars.remove(c);
      }
    }

    // Check the number of characters with odd counts:
    return oddChars.size() <= 1;
  }

  public static void main(String[] args) {
    System.out.println(canMakePalindrome("yakak"));
    System.out.println(canMakePalindrome("travel"));
  }
}
