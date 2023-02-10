package two_pointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by gouthamvidyapradhan on 29/03/2017. Given an array S of n integers, are there elements
 * a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum
 * to any target value.
 *
 * <p>Given an array of integers, find all triplets in the array that sum up to a given target
 * value.
 * <p>In other words, given an array arr and a target value target, return all triplets a, b, c such
 * that a + b + c = target.
 *
 * <p>Note: The solution set must not contain duplicate triplets.
 *
 * <p>For example, given array S = [-1, 0, 1, 2, -1, -4],
 *
 * <p>A solution set is: [ [-1, 0, 1], [-1, -1, 2] ]
 */
public class ThreeSum {
  /* Solution:
   Naive approach: Use three for loops
  The naive approach is to just use three nested for loops and check if the sum of any three elements in the array is equal to the given target.
  Time complexity: O(n^3)
   */
  public static void main(String[] args) throws Exception {
    int[] nums = {
      -1, 0, 1, 2, -1, -4, -1, 0, 1, 2, -1, -4, -1, 0, 1, 2, -1, -4, -1, 0, 1, 2, -1, -4, -1, 0, 1,
      2, -1, -4, -1, 0, 1, 2, -1, -4, -1, 0, 1, 2, -1, -4, -1, 0, 1, 2, -1, -4
    };
    System.out.println(findThreeSum_Sorting(nums, 0));
  }

  /* Optimzed: Sorting and two pointer
  Iterate through each element of the array and for every iteration,
  Fix the first element (nums[i])
  Try to find the other two elements whose sum along with nums[i] gives target. This boils down to the two sum problem.
   */
  // Time complexity: O(n^2)
  private static List<Integer[]> findThreeSum_Sorting(int[] nums, int target) {
    List<Integer[]> result = new ArrayList<>();
    Arrays.sort(nums);
    for (int i = 0; i < nums.length; i++) {
      int left = i + 1;
      int right = nums.length - 1;
      while (left < right) {
        if (nums[i] + nums[left] + nums[right] == target) {
          result.add(new Integer[] {nums[i], nums[left], nums[right]});
          left++;
          right--;
        } else if (nums[i] + nums[left] + nums[right] < target) {
          left++;
        } else {
          right--;
        }
      }
    }
    return result;
  }
}
