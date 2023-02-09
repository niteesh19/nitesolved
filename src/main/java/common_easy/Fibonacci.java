package common_easy;

//Fibonacci Series using Recursion
class Fibonacci {
  static int fibWithRecursion(int n) {
    if (n <= 1)
      return n;
    return fibWithRecursion(n - 1) + fibWithRecursion(n - 2);
  }

  public static void main(String[] args) {
    int n = 9;
    System.out.println(fibWithRecursion(n));  // Time: O(2^n) exponential ---- Space: O(n) considering call stack
    System.out.println(fibUsingDP(n));  // Time: O(n) ---- Space: O(n)
    System.out.println(fibConstantSpace(n));  // Time: O(n) ---- Space: O(1)
  }

  static int fibUsingDP(int n) {
    /* Declare an array to store Fibonacci numbers. */
    int[] f = new int[n + 2]; // 1 extra to handle case, n = 0
    int i;
    /* 0th and 1st number of the series are 0 and 1*/
    f[0] = 0;
    f[1] = 1;

    for (i = 2; i <= n; i++) {
      // Add the previous 2 numbers in the series and store it
      f[i] = f[i - 1] + f[i - 2];
    }

    return f[n];
  }

  static int fibConstantSpace(int n) {
    int a = 0, b = 1, c;
    if (n == 0)
      return a;
    for (int i = 2; i <= n; i++) {
      c = a + b;
      a = b;
      b = c;
    }
    return b;
  }

}
