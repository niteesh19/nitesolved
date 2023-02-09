package dynamic_programming;

/**
 * Created by gouthamvidyapradhan on 17/03/2017. Say you have an array for which the ith element is
 * the price of a given stock on day i.
 *
 * <p>If you were only permitted to complete at most one transaction (ie, buy one and sell one share
 * of the stock), design an algorithm to find the maximum profit.
 *
 * <p>Example 1: Input: [7, 1, 5, 3, 6, 4] Output: 5
 *
 * <p>max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)
 * Example 2: Input: [7, 6, 4, 3, 1] Output: 0
 *
 * <p>In this case, no transaction is done, i.e. max profit = 0.
 */
public class BestTimeToBuyAndSellStocks {
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
//    int[] prices = {1, 1, 1, 1, 1};
    int[] prices = {1,5,6,7};
//    int[] prices = {7, 6, 8, 4, 2, 3, 9, 5, 1, 7, 4};
    System.out.println(new BestTimeToBuyAndSellStocks().maxProfitSimple(prices));
  }

  //Optimised O(N)
  public int maxProfitSimple(int[] prices) {
    if(prices==null||prices.length<=1)
      return 0;

    int min=prices[0]; // min so far
    int result=0;

    for(int i=1; i<prices.length; i++){
      result = Math.max(result, prices[i]-min);
      min = Math.min(min, prices[i]);
    }

    return result;
  }

}
