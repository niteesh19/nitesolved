package array;

public class LargestPairSum {

  public static void main(String[] args) {
    int[] arr = {1, 2, 3, 4, 9, 10, 5, 6, 7, 8};
    System.out.println(largestPairSum(arr, arr.length));
  }

  private static int largestPairSum(int[] arr, int n) {

    int max1 = Integer.MIN_VALUE;
    int max2 = Integer.MIN_VALUE;

    for (int i = 0; i < n; i++) {
      if (arr[i] > max1) {
        max2 = max1;
        max1 = arr[i];
      }
    }
    return max1 + max2;
  }

}