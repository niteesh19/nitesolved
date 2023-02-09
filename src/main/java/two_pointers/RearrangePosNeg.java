package two_pointers;

// Rearrange positive and negative numbers with constant extra space

class RearrangePosNeg {
  public static void RearrangePositiveNegative(int arr[])
  {
    int i = 0;
    int j = arr.length - 1;
    while (true) {
      // Loop until arr[i] < 0 and
      // still inside the array
      while (i < arr.length && arr[i] < 0)
        i++;

      // Loop until arr[j] > 0 and
      // still inside the array
      while (j >= 0 && arr[j] > 0)
        j--;

      // if i is less than j
      if (i < j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
      }
      else
        break;
    }
  }

  // Driver Code
  public static void main(String[] args)
  {
    int arr[] = { -12, 11, -13, -5, 6, -7, 5, -3, -6 };
    RearrangePositiveNegative(arr);
    for (int i = 0; i < arr.length; i++)
      System.out.print(arr[i] + " ");
  }
}
