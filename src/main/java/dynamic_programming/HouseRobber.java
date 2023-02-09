package dynamic_programming;

/**
 * Created by pradhang on 4/3/2017. You are a professional robber planning to rob houses along a
 * street. Each house has a certain amount of money stashed, the only constraint stopping you from
 * robbing each of them is that adjacent houses have security system connected and it will
 * automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * <p>Given a list of non-negative integers representing the amount of money of each house,
 * determine the maximum amount of money you can rob tonight without alerting the police.
 * <p>
 * Solution: At n, max(n + max(0 .. n-2)), max(0 .. n-1)
 */
public class HouseRobber {
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    System.out.println(robSol(new int[]{5, 2, 3, 4, 5, 6, 1, 1}));
    System.out.println(robSol(new int[]{1, 1, 6, 7, 6, 1}));

  }

  static int robSol(int[] nums) {
    int rob1 = 0, rob2 = 0;
    for (int n : nums) {
      int temp = Math.max(n + rob1, rob2);
      rob1 = rob2;
      rob2 = temp;
    }
    /*  rob1, rob2
          0,5
          5,5
          5,8
          8,9
          9,13
          13,15
          15,15
          15,16
    */
    return rob2;
  }
}

