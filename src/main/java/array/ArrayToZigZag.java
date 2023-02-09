package array;

// Java program to sort an array in Zig-Zag form
import java.util.Arrays;
/*
Input array is [4, 3, 7, 8, 6, 2, 1]. Sorted array would be [1, 2, 3, 4, 6, 7, 8].
And if swap 2nd & 3rd elements, swap 4th & 5th elements, swap 6th & 7th elements,
the output zig-zag array would be [1, 3, 2, 6, 4, 8, 7]. Here we can see {1 < 3 > 2 < 6 > 4 < 8 > 7}.
time complexity of sorting and hence this approach is O(nlogn)

We can convert in O(n) time
Maintain a flag for representing which order(i.e. < or >) currently we need.
If the current two elements are not in that order then swap those elements otherwise not.

Suppose we are processing B and C currently and the current relation is ‘<‘, but we have B > C.
Since current relation is ‘<‘ previous relation must be ‘>’ i.e., A must be greater than B.
So, the relation is A > B and B > C. We can deduce A > C. So if we swap B and C then the relation is A > C and C < B.
Finally we get the desired order A C B
 */
class ArrayToZigZag
{
  static int arr[] = new int[]{4, 3, 7, 8, 6, 2, 1};

  // Method for zig-zag conversion of array
  static void zigZag()
  {
    // Flag true indicates relation "<" is expected,
    // else ">" is expected. The first expected relation
    // is "<"
    boolean flag = true;

    int temp =0;

    for (int i=0; i<=arr.length-2; i++)
    {
      if (flag) /* "<" relation expected */
      {
				/* If we have a situation like A > B > C,
				we get A > B < C by swapping B and C */
        if (arr[i] > arr[i+1])
        {
          // swap
          temp = arr[i];
          arr[i] = arr[i+1];
          arr[i+1] = temp;
        }

      }
      else /* ">" relation expected */
      {
				/* If we have a situation like A < B < C,
				we get A < C > B by swapping B and C */
        if (arr[i] < arr[i+1])
        {
          // swap
          temp = arr[i];
          arr[i] = arr[i+1];
          arr[i+1] = temp;
        }
      }
      flag = !flag; /* flip flag */
    }
  }

  // Driver method to test the above function
  public static void main(String[] args)
  {
    zigZag();
    System.out.println(Arrays.toString(arr));
  }
}
