package dynamic_programming;

import java.util.Arrays;

/*
On a positive integer, you can perform any one of the following 3 steps.

Subtract 1 from it. ( n = n - 1 )
If its divisible by 2, divide by 2. ( if n % 2 == 0 , then n = n / 2 )
If its divisible by 3, divide by 3. ( if n % 3 == 0 , then n = n / 3 ).
Now the question is, given a positive integer n, find the minimum number of steps that takes n to 1

eg:

For n = 1 , output: 0
For n = 4 , output: 2 ( 4 /2 = 2 /2 = 1 )
For n = 7 , output: 3 ( 7 -1 = 6 /3 = 2 /2 = 1 )
 */
public class MinStepsToOne {

  // recursive tree with 3 choices: -1, /2, /3
  // then find the min path of these 3
  // time complexity: O(2^n)
  public static int countMinStepsTo1(int n) {

    if (n == 1) {
      return 0;
    }
    int count1, count2 = Integer.MAX_VALUE, count3 = Integer.MAX_VALUE;

    count1 = countMinStepsTo1(n - 1);

    if ((n % 2) == 0) {
      count2 = countMinStepsTo1(n / 2);
    }
    if ((n % 3) == 0) {
      count3 = countMinStepsTo1(n / 3);
    }

    return 1 + Math.min(count1, Math.min(count2, count3));
  }

  // same recursive tree but with memoization
  // optimal solution, trade off with space. O(n)
  public static int countMinStepsTo1UsingDP(int n, int[] dp) {

    if (n == 1) {
      return 0;
    }
    int count1, count2 = Integer.MAX_VALUE, count3 = Integer.MAX_VALUE;

    if (dp[n] != -1)
      return dp[n];
    count1 = countMinStepsTo1UsingDP(n - 1, dp);

    if ((n % 2) == 0) {
      count2 = countMinStepsTo1UsingDP(n / 2, dp);
    }
    if ((n % 3) == 0) {
      count3 = countMinStepsTo1UsingDP(n / 3, dp);
    }

    return dp[n] = 1 + Math.min(count1, Math.min(count2, count3));
  }

  // iterative optimal solution O(n)
  public static int countMinStepsTo1UsingDPIterative(int n)
  {
    int storage[] = new int[n+1];
    storage[1]=0;

    for(int i=2; i<=n;i++)
    {
      int min=storage[i-1];
      if(i%3==0)
      {
        if(min>storage[i/3])
        {
          min=storage[i/3];
        }
      }
      if(i%2==0)
      {
        if(min>storage[i/2])
        {
          min=storage[i/2];
        }
      }
      storage[i]=1+min;
    }

    return storage[n];

  }

  public static void main(String[] args) {
    System.out.println(countMinStepsTo1(7));
    System.out.println(countMinStepsTo1(4));

    int n = 7;
    int[] dp = new int[n+1];
    Arrays.fill(dp, -1);
    System.out.println(countMinStepsTo1UsingDP(n, dp));
  }

}
