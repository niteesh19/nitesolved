package common_medium;

/*
How to add two numbers of any length in java
Sum of two large numbers
Input  : str1 = "3333311111111111",
         str2 =   "44422222221111"
Output : 3377733333332222

Input  : str1 = "7777555511111111",
         str2 =    "3332222221111"
Output : 7780887733332222

===== Solution
BigInteger a = new BigInteger("9223372036854775807");
BigInteger b = new BigInteger("9223372036854775808");
BigInteger result = a.add(b);

The idea is based on school mathematics. We traverse both strings from end, one by one add digits and keep track of carry. To simplify the process, we do following:
1) Reverse both strings.
2) Keep adding digits one by one from 0’th index (in reversed strings) to end of smaller string, append the sum % 10 to end of result and keep track of carry as sum/10.
3) Finally reverse the result.

 */
public class Add2BigNums {
  // Java program to find sum of two large numbers.
  // Function for finding sum of larger numbers
  static String findSum(String str1, String str2) {
    // Before proceeding further, make sure length
    // of str2 is larger.
    if (str1.length() > str2.length()) {
      String t = str1;
      str1 = str2;
      str2 = t;
    }

    // Take an empty String for storing result
    StringBuilder str = new StringBuilder();

    // Calculate length of both String
    int n1 = str1.length(), n2 = str2.length();

    // Reverse both of Strings
    str1 = new StringBuilder(str1).reverse().toString();
    str2 = new StringBuilder(str2).reverse().toString();

    int carry = 0;
    for (int i = 0; i < n1; i++) {
      // Do school mathematics, compute sum of
      // current digits and carry
      int sum = ((int) (str1.charAt(i) - '0') + (int) (str2.charAt(i) - '0') + carry);
      str.append((char) (sum % 10 + '0'));

      // Calculate carry for next step
      carry = sum / 10;
    }

    // Add remaining digits of larger number
    for (int i = n1; i < n2; i++) {
      int sum = ((int) (str2.charAt(i) - '0') + carry);
      str.append((char) (sum % 10 + '0'));
      carry = sum / 10;
    }

    // Add remaining carry
    if (carry > 0) str.append((char) (carry + '0'));

    // reverse resultant String
    str = new StringBuilder(new StringBuilder(str.toString()).reverse().toString());

    return str.toString();
  }

  // Driver code
  public static void main(String[] args) {
    String str1 = "12";
    String str2 = "198111";
    System.out.println(findSum(str1, str2));
  }
}
