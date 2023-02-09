package hashing;

// Java program to print the maximum number
// from the set of digits of a given number
/*
Simple Approach: The simple method to solve this problem is to extract and store the digits of the given number in an integer array and sort this array in descending order. After sorting the array, print the elements of the array.
Time Complexity: O( N log N ), where N is the number of digits in the given number.
Efficient approach : We know that the digits in a number will range from 0-9, so the idea is to create a hashed array of size 10 and store the count of every digit in the hashed array that occurs in the number. Then traverse the hashed array from index 9 to 0 and calculate the number accordingly.
Below is the implementation of above efficient approach:
 */
public class MaxNum
{
  // Function to print the maximum number
  static int printMaxNum(int num)
  {
    // hashed array to store count of digits
    int count[] = new int[10];

    // Converting given number to string
    String str = Integer.toString(num);

    // Updating the count array
    for(int i=0; i < str.length(); i++)
      count[str.charAt(i)-'0']++;

    // result is to store the final number
    int result = 0, multiplier = 1;

    // Traversing the count array
    // to calculate the maximum number
    for (int i = 0; i <= 9; i++)
    {
      while (count[i] > 0)
      {
        result = result + (i * multiplier);
        count[i]--;
        multiplier = multiplier * 10;
      }
    }

    // return the result
    return result;
  }

  // Driver program to test above function
  public static void main(String[] args)
  {
    int num = 38293367;
    System.out.println(printMaxNum(num));
  }
}
