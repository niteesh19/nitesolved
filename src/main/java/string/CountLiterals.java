package string;

import java.util.HashMap;
import java.util.Map;

public class CountLiterals {

  //count of N = 1
  //count of i = 1
  //count of t = 2
  //count of e = 2
  //count of a = 2 ..
  public static void main(String[] args) {
    String inputStr = "Niteesh Kumar Gupta";
    countLiteralsUsingMap(inputStr);
  }

  private static void countLiteralsUsingMap(String inputStr) {
    try {
      Map<Character, Integer> countMap = new HashMap<>();
      for (int i = 0; i < inputStr.length(); i++) {
        char c = inputStr.charAt(i);
        if (c != ' ')
          countMap.put(c, !countMap.containsKey(c) ? 1 : countMap.get(c) + 1);
      }
      System.out.println(countMap);
      System.out.println(countMap.get('e'));

    } catch (Exception e) {
      e.printStackTrace();
    }
  }


}
