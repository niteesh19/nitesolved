package hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by gouthamvidyapradhan on 10/03/2017. Given an array of strings, group anagrams together.
 *
 * <p>For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], Return:
 *
 * <p>[ ["ate", "eat","tea"], ["nat","tan"], ["bat"] ] Note: All inputs will be in lower-case.
 */
public class GroupAnagrams {
  private int[] A = new int[256];
  private final HashMap<String, List<String>> hashMap = new HashMap<>();
  private final List<List<String>> result = new ArrayList<>();

  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat", "huh", "at"};

    List<List<String>> result = new GroupAnagrams().groupAnagrams(strs);
    for (List<String> l : result) {
      for (String s : l) System.out.println(s);
      System.out.println("-----");
    }
  }

  public List<List<String>> groupAnagrams(String[] strs) {
    for (String str : strs) {
      Arrays.fill(A, 0);
      for (int j = 0; j < str.length(); j++)
        A[str.charAt(j)]++;   //eg. capturing 'a' at 97th index with value as +1

      StringBuilder sb = new StringBuilder();
      for (int k = 0; k < 256; k++) {
        // forming a string with ordered int array starting with 97th idx, which is 'a' char :: 97111011161
        if (A[k] != 0) sb.append(k).append(A[k]);
      }
      List<String> value = hashMap.get(sb.toString());    //checking if hashmap has the key 97111011161
      if (value == null) value = new ArrayList<>();
      value.add(str);
      hashMap.put(sb.toString(), value);    //associating 97111011161 with value tan
    }

    for (String s : hashMap.keySet()) result.add(hashMap.get(s));

    return result;
  }
}
