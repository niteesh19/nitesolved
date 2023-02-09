package array;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 11/07/2017. Given an array of integers, return indices of the
 * two numbers such that they add up to a specific target.
 *
 * <p>You may assume that each input would have exactly one solution, and you may not use the same
 * element twice.
 *
 * <p>Example: Given nums = [2, 7, 11, 15], target = 9,
 *
 * <p>Because nums[0] + nums[1] = 2 + 7 = 9, return [0, 1].
 *
 * <p>Solution: You can use a HashMap to solve the problem in O(n) time complexity. Here are the steps:
 *
 * <p>Initialize an empty HashMap. Iterate over the elements of the array. For every element in the
 * array - If the element exists in the Map, then check if it’s complement (target - element) also
 * exists in the Map or not. If the complement exists then return the indices of the current element
 * and the complement. Otherwise, put the element in the Map, and move to the next iteration.
 */
public class TwoSum {

  class NumIndex {
    int i, e;

    NumIndex(int i, int e) {
      this.i = i;
      this.e = e;
    }
  }

  public static void main(String[] args) {
    int[] nums = {3, 2, 4};
    int[] ans = new TwoSum().twoSum(nums, 6);
    for (int i : ans) System.out.println(i);
  }

  // Time complexity: O(n)
  public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> numMap = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      int complement = target - nums[i];
      if (numMap.containsKey(complement)) {
        return new int[] { numMap.get(complement), i };
      } else {
        numMap.put(nums[i], i);
      }
    }
    return new int[] {};
  }

  /*
  There is another approach which works when you need to return the numbers instead of their indexes. Here is how it works:

  Sort the array.
  Initialize two variables, one pointing to the beginning of the array (left) and another pointing to the end of the array (right).
  Loop until left < right, and for each iteration
  if arr[left] + arr[right] == target, then return the indices.
  if arr[left] + arr[right] < target, increment the left index.
  else, decrement the right index.
  This approach is called the two-pointer approach. It is a very common pattern for solving array related problems.
   */
  //O(nlogn)
  private int[] findTwoSum_Sorting(int[] nums, int target) {
    Arrays.sort(nums);
    int left = 0;
    int right = nums.length - 1;
    while(left < right) {
      if(nums[left] + nums[right] == target) {
        return new int[] {nums[left], nums[right]};
      } else if (nums[left] + nums[right] < target) {
        left++;
      } else {
        right--;
      }
    }
    return new int[] {};
  }

}
