package hashing;

import java.util.HashMap;

/**
 * Created by gouthamvidyapradhan on 10/03/2017. Given two strings s and t, write a function to
 * determine if t is an anagram of s.
 *
 * <p>For example, s = "anagram", t = "nagaram", return true. s = "rat", t = "car", return false.
 *
 * <p>Note: You may assume the string contains only lowercase alphabets.
 *
 * <p>Follow up: What if the inputs contain unicode characters? How would you adapt your solution to
 * such case?
 */
public class ValidAnagram {
  private int[] S = new int[256];
  private int[] T = new int[256];

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    System.out.println(new ValidAnagram().isAnagram2("anagram", "nagaram"));
    System.out.println(new ValidAnagram().isAnagram3("anagram", "nngaram"));
  }

  // This solution works only for lower and upper case letters as well as digits
  public boolean isAnagram(String s, String t) {
    if (s.length() != t.length()) return false;

    for (int i = 0; i < s.length(); i++) {
      S[s.charAt(i)]++;
    }

    for (int i = 0; i < t.length(); i++) {
      T[t.charAt(i)]++;
    }

    for (int i = 0; i < 256; i++) {
      if (S[i] != T[i]) return false;
    }

    return true;
  }

  // This solution works only for lower case letters
  public boolean isAnagram2(String s, String t) {
    if (s == null || t == null)
      return false;

    if (s.length() != t.length())
      return false;

    int[] arr = new int[26];    // arr will have index as the letter ASCII code and value will be count of the letters
    for (int i = 0; i < s.length(); i++) {
      arr[s.charAt(i) - 'a']++;   //basically increment the source letters in the arr
      arr[t.charAt(i) - 'a']--;   //and then decrement the target letters from the same arr index
    }

    for (int i : arr) {
      if (i != 0)
        return false;
    }

    return true;
  }

  //If the inputs contain unicode characters, an array with length of 26 is not enough.
  public boolean isAnagram3(String s, String t) {
    if (s.length() != t.length())
      return false;

    HashMap<Character, Integer> map = new HashMap<>();

    for (int i = 0; i < s.length(); i++) {
      char c1 = s.charAt(i);
      if (map.containsKey(c1)) {
        map.put(c1, map.get(c1) + 1);
      } else {
        map.put(c1, 1);
      }
    }

    for (int i = 0; i < s.length(); i++) {
      char c2 = t.charAt(i);
      if (map.containsKey(c2)) {
        if (map.get(c2) == 1) {
          map.remove(c2);
        } else {
          map.put(c2, map.get(c2) - 1);
        }
      } else {
        return false;
      }
    }

    if (map.size() > 0)
      return false;

    return true;
  }

}
