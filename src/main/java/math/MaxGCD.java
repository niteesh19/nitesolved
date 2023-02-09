package math;

public class MaxGCD
{
  // Recursive function to return gcd of a and b
  static int gcd(int a, int b)
  {
    if (b == 0)
      return a;
    return gcd(b, a % b);
  }

  // Function to return the maximized gcd
  // after removing a single element
  // from the given array
  static int findMaxGCD(int a[], int n)
  {

    // prefix and suffix arrays
    int[] prefix = new int[n + 2];
    int[] suffix = new int[n + 2] ;

    // Single state dynamic programming relation
    // for storing gcd of first i elements
    // from the left in prefix[i]
    prefix[1] = a[0];
    for (int i = 2; i <= n; i += 1)
    {
      prefix[i] = gcd(prefix[i - 1], a[i - 1]);
    }

    // Initializing suffix array
    suffix[n] = a[n - 1];

    // Single state dynamic programming relation
    // for storing gcd of all the elements having
    // greater than or equal to i in suffix[i]
    for (int i = n - 1; i >= 1; i -= 1)
    {
      suffix[i] = gcd(suffix[i + 1], a[i - 1]);
    }

    // If first or last element of
    // the array has to be removed
    int ans = Math.max(suffix[2], prefix[n - 1]);

    // If any other element is replaced
    for (int i = 2; i < n; i += 1)
    {
      ans = Math.max(ans, gcd(prefix[i - 1], suffix[i + 1]));
    }

    // Return the maximized gcd
    return ans;
  }

  // Driver code
  public static void main(String[] args)
  {

    int[] a = { 14, 17, 28, 70 };
    int n = a.length;

    System.out.println(findMaxGCD(a, n));
  }
}
