package string;

/*
Find the largest three distinct elements in an array
Find the sum of largest three numbers in an array.
 */
public class LargestThreeSum {
  //Naive approach: we first need to sort the whole array
  // and after that when we add the last three-element of the array then we find the maximum sum of triplets.
  // O(nlogn)

  // Time Complexity: O(n)
  // Auxiliary Space: O(1)
  static void print3largest(int[] arr, int arr_size) {
    int i, first, second, third;

    /* There should be atleast three elements */
    if (arr_size < 3) {
      System.out.print(" Invalid Input ");
      return;
    }

    third = first = second = Integer.MIN_VALUE;
    for (i = 0; i < arr_size; i++) {
      /* If current element is greater than first*/
      // This order of assignment is important
      if (arr[i] > first) {
        third = second;
        second = first;
        first = arr[i];
      }
      /* If arr[i] is in between first and second then update second  */
      // For distinct: we have the check of != first
      else if (arr[i] > second && arr[i] != first) {
        third = second;
        second = arr[i];
      }
      // less than first and second but greater than third
      else if (arr[i] > third && arr[i] != first && arr[i] != second) third = arr[i];
    }

    System.out.println("Three largest elements are " + first + " " + second + " " + third);
  }

  /* Driver program to test above function*/
  public static void main(String[] args) {
    int[] arr = {12, 13, 1, 10, 34, 34, 1};
    int n = arr.length;
    print3largest(arr, n);
  }
}
