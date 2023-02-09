package common_medium;

/*
Find maximum occurring character in a string
Input: geeksforgeeks
Output: e
Explanation: ‘e’ occurs 4 times in the string

Input: test
Output: t
Explanation: ‘t’ occurs 2 times in the string
 */
public class MaxOccurringCharInStr {

  static char getMaxOccurringChar(String str)
  {
    // Create array to keep the count of individual
    // characters and initialize the array as 0
    int[] charCount = new int[256];

    // Construct character count array from the input
    // string.
    int len = str.length();
    for (int i = 0; i < len; i++)
      charCount[str.charAt(i)]++;

    int max = -1; // Initialize max count
    char result = ' '; // Initialize result

    // Traversing through the string and maintaining
    // the count of each character
    for (int i = 0; i < len; i++) {
      if (max < charCount[str.charAt(i)]) {
        max = charCount[str.charAt(i)];
        result = str.charAt(i);
      }
    }

    return result;
  }

  // Driver Method
  public static void main(String[] args)
  {
    String str = "sample string";
    System.out.println("Max occurring character is "
        + getMaxOccurringChar(str));
  }

}
