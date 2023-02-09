package string;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 09/03/2017. Given a string, find the first non-repeating
 * character in it and return it's index. If it doesn't exist, return -1.
 *
 * <p>Examples:
 *
 * <p>s = "leetcode" return 0.
 *
 * <p>s = "loveleetcode", return 2. Note: You may assume the string contain only lowercase letters.
 */
public class FirstUniqueCharacterInAString {

  public int firstUniqChar(String s) {
    if (s == null || s.isEmpty()) return -1;
    try {
      int strLen = s.length();
      final int[] myCharArr = new int[256];

      for (int i = 0; i < strLen; i++) myCharArr[s.charAt(i)]++;

      for (int i = 0; i < strLen; i++) {
        if (myCharArr[s.charAt(i)] == 1) {
          System.out.println("value:"+s.charAt(i));
          return i;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return -1;
  }

  /*
   * Using LinkedHashMap to find first non repeated character of String
   * Algorithm :
   *            Step 1: get character array and loop through it to build a
   *                    hash table with char and their count.
   *            Step 2: loop through LinkedHashMap to find an entry with
   *                    value 1, that's your first non-repeated character,
   *                    as LinkedHashMap maintains insertion order.
   */
  public static char getFirstNonRepeatedChar(String str) {
    Map<Character,Integer> counts = new LinkedHashMap<>(str.length());

    for (char c : str.toCharArray()) {
      counts.put(c, counts.containsKey(c) ? counts.get(c) + 1 : 1);
    }

    // if required to find the index
    for (int i = 0; i < str.length(); i++) {
      if (counts.get(str.charAt(i)) == 1) {
        System.out.println("index:"+i);
        break;
      }
    }

    for (Map.Entry<Character,Integer> entry : counts.entrySet()) {
      if (entry.getValue() == 1) {
        return entry.getKey();
      }
    }

    throw new RuntimeException("didn't find any non repeated Character");
  }

  /*
  * Finds first non repeated character in a String in just one pass.
  * It uses two storage to cut down one iteration, standard space vs time trade-off.

   */
  public static char firstNonRepeatingChar(String word) {
    Set<Character> nonRepeating = new LinkedHashSet<>();
    for (int i = 0; i < word.length(); i++) {
      char letter = word.charAt(i);
      if (nonRepeating.contains(letter)) {
        nonRepeating.remove((Character) letter);
      } else {
        nonRepeating.add(letter);
      }
    }
    return nonRepeating.iterator().hasNext() ? nonRepeating.iterator().next() : 0;
  }

  /*
  * Finds first non repeated character in a String in just one pass.
  * It uses two storage to cut down one iteration, standard space vs time trade-off.
  * Since we store repeated and non-repeated character separately,
  * at the end of iteration, first element from List is our first non-repeated character from String.
  * This same function can be used for finding repeating char in a string
  * */
  public static char firstNonRepeatingOrRepeatingChar(String word) {
    Set<Character> repeating = new HashSet<>();
    List<Character> nonRepeating = new ArrayList<>();
    for (int i = 0; i < word.length(); i++) {
      char letter = word.charAt(i);
      if (repeating.contains(letter)) {
        continue;
      }
      if (nonRepeating.contains(letter)) {
        nonRepeating.remove((Character) letter);
        repeating.add(letter);
      } else {
        nonRepeating.add(letter);
      }
    }
    return nonRepeating.get(0);
  }

  public static void main(String[] args) throws Exception {

    System.out.println(new FirstUniqueCharacterInAString().firstUniqChar("loveleetcode"));
    System.out.println(getFirstNonRepeatedChar("loveleetcode"));
    System.out.println(firstNonRepeatingOrRepeatingChar("loveleetcode"));
    System.out.println(firstNonRepeatingChar("loveleetcode"));

    System.out.println(getFirstNonRepeatedChar("949674"));
    System.out.println(firstNonRepeatingOrRepeatingChar("949674"));
    System.out.println(firstNonRepeatingChar("949674"));
  }
}
