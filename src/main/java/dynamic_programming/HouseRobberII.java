package dynamic_programming;

import java.util.Arrays;

/**
 * Created by pradhang on 7/11/2017. After robbing those houses on that street, the thief has found
 * himself a new place for his thievery so that he will not get too much attention. This time, all
 * houses at this place are arranged in a circle. That means the first house is the neighbor of the
 * last one. Meanwhile, the security system for these houses remain the same as for those in the
 * previous street.
 *
 * <p>Given a list of non-negative integers representing the amount of money of each house,
 * determine the maximum amount of money you can rob tonight without alerting the police.
 */
public class HouseRobberII {
  public static void main(String[] args) throws Exception {
    int[] nums = {6, 3, 10, 8, 2, 10, 3, 5, 10, 5, 3};
    int[] nums2 = {2, 3, 2};
    System.out.println(robSol2(nums));  //36
    System.out.println(robSol2(nums2));  //3
  }

  /*
    First, run the same logic as House Robber 1 for array excluding the first elem
    and then for array excluding the last elem. Get the max of both as ans.
    Add boundary conditions for 0th elem
   */
  static int robSol2(int[] nums) {
    if(nums.length == 0)
      return 0;
    if(nums.length == 1)
      return nums[0];
    int maxRob1 = HouseRobber.robSol(Arrays.copyOfRange(nums, 1, nums.length));
    int maxRob2 = HouseRobber.robSol(Arrays.copyOfRange(nums, 0, nums.length-1));
    return Math.max(maxRob1, maxRob2);
  }
}
