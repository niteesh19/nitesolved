package dynamic_programming;

import java.util.Arrays;

/**
 * Created by gouthamvidyapradhan on 23/06/2017. You are given coins of different denominations and
 * a total amount of money amount. Write a function to compute the fewest number of coins that you
 * need to make up that amount. If that amount of money cannot be made up by any combination of the
 * coins, return -1.
 *
 * <p>Example 1: coins = [1, 2, 5], amount = 11 return 3 (11 = 5 + 5 + 1)
 *
 * <p>Example 2: coins = [2], amount = 3 return -1.
 *
 * <p>Note: You may assume that you have an infinite number of each kind of coin.
 *
 * <p>Solution: For example if you have N coins and amount equal to Q For every coin,
 * total amount reduces by the sum equivalent to the value of this coin,
 * so you are left with N coins and Q = (Q - value of this coin)
 * Recursively go over the Q amount by checking for all coins and returning the min path (number of coins)
 * P.S.: First solve without extra "Store" using basic recursion and then introduce "Store" as DP
 * for storing the already calculated amount for whose min path was returned
 * <p>Worst-case time complexity is O(N x Q) where N is the total number of coins and Q is the total amount
 */
public class CoinChange {

  // Better and easy solution
  public int coinChangeIter(int[] coins, int amount) {
    int max = amount + 1;
    int[] dp = new int[amount + 1];
    Arrays.fill(dp, max); //Filling with amount+1 as that can be the max ways else it will be -1
    dp[0] = 0;  //Base condition given 0 coins then 0 ways
    for (int i = 1; i <= amount; i++) {
      for (int coin : coins) {
        if (coin <= i) {
          dp[i] = Math.min(dp[i], dp[i - coin] + 1);  //DP table with min of 1+F(7-1) or 1+F(7-2) or 1+F(7-5)
        }
      }
    }
    return dp[amount] > amount ? -1 : dp[amount];
  }

  // Complex to understand
  public int coinChange(int[] coins, int amount) {
    if (amount < 1) return 0;
    int[] store = new int[amount+1];
//    Arrays.fill(store, -1);   //This somehow impacts while retrieving the value from store and checking if negative
    store[0] = 0;
    return myCoinChange(coins, amount, store);
  }

  //Returns number of coins (path) for the balance considering all coins
  public int myCoinChange(int[] coins, int amount, int[] store) {
    if (amount == 0)
      return 0;
    if (store[amount] != 0)
      return store[amount];
    int ans = Integer.MAX_VALUE;
    for (int coin : coins) {
      int balance = amount - coin;
      if (balance >= 0) {
        int subAns = myCoinChange(coins, balance, store);
        //This checks at each balance level, which is the lowest num of coins
        //subAns + 1 is required as the current balance uses up one coin
        if (subAns >= 0 && subAns < ans) {
          ans = subAns + 1;
        }
      }
    }
    //Store in DP to retrieve from there to optimise the O(n^m) to O (nm)
    if(ans != Integer.MAX_VALUE)
      store[amount] = ans;
    else
      store[amount] = -1;
    return store[amount];
  }

  public static void main(String[] args) throws Exception {
    int[] coins1 = {1, 2, 5};
    System.out.println(new CoinChange().coinChange(coins1, 7));
    System.out.println(new CoinChange().coinChangeIter(coins1, 7));

    int[] coins2 = {5, 7, 1};
    System.out.println(new CoinChange().coinChange(coins2, 18));
    System.out.println(new CoinChange().coinChangeIter(coins2, 18));

    int[] coins3 = {7};
    System.out.println(new CoinChange().coinChange(coins3, 8));
    System.out.println(new CoinChange().coinChangeIter(coins3, 8));

    int[] coins4 = {186,419,83,408};
    System.out.println(new CoinChange().coinChange(coins4, 6249));
    System.out.println(new CoinChange().coinChangeIter(coins4, 6249));

  }

}
