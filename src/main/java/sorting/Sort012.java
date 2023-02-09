package sorting;

// Simple Java program to sort an array of 0s 1s and 2s.
import java.util.*;
import java.lang.*;

public class Sort012{

  public static void sort012(int arr[], int n)
  {
    // Variables to maintain
    // the count of 0's,
    // 1's and 2's in the array
    int count0 = 0, count1 = 0;
    int count2 = 0;
    for (int i = 0; i < n; i++) {
      if (arr[i] == 0)
        count0++;
      if (arr[i] == 1)
        count1++;
      if (arr[i] == 2)
        count2++;
    }

    // Putting the 0's in the array in starting.
    for (int i = 0; i < count0; i++)
      arr[i] = 0;

    // Putting the 1's in the array after the 0's.
    for (int i = count0; i < (count0 + count1); i++)
      arr[i] = 1;

    // Putting the 2's in the array after the 1's
    for (int i = (count0 + count1); i < n; i++)
      arr[i] = 2;

    printArray(arr, n);
  }

  // Prints the array
  public static void printArray(int arr[], int n)
  {
    for (int i = 0; i < n; i++)
      System.out.print(arr[i] + " ");
    System.out.println();
  }

  // Driver function
  public static void main(String args[])
  {

    int arr[] = { 0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1 };
    int n = 12;
    sort012(arr, n);
  }

  static void optimizedSort012(int a[], int arr_size)   // Constant Space O(1)
  {
    int lo = 0;
    int hi = arr_size - 1;
    int mid = 0, temp = 0;
    while (mid <= hi) {
      switch (a[mid]) {
        case 0: {
          temp = a[lo];
          a[lo] = a[mid];
          a[mid] = temp;
          lo++;
          mid++;
          break;
        }
        case 1:
          mid++;
          break;
        case 2: {
          temp = a[mid];
          a[mid] = a[hi];
          a[hi] = temp;
          hi--;
          break;
        }
      }
    }
  }

  // ============ Sorting Object ============
  static class ColoredObject {
    int someProperty;
    String anotherProperty;
    int color; //replace the &#039@COLOR@&#039 type with something valid
    ColoredObject(int x, String y, int z)
    {
      someProperty =x;
      anotherProperty = y;
      color = z;
    }
  }

  public void sortColors(ColoredObject[] A)
  {
    int redPosition = 0;
    int greenPosition = 0;
    int bluePosition = 0;

    for(int i=0; i < A.length;i++)
    {
      ColoredObject temp;
      if(A[i].color == 0)
      {
        temp = A[greenPosition];
        A[greenPosition] = A[redPosition];
        A[redPosition] = A[i];
        A[i] = temp;
        redPosition++;
        greenPosition++;
        bluePosition++;
      }
      else if( A[i].color == 1)
      {
        temp = A[greenPosition] ;
        A[greenPosition]= A[i];
        A[i] = temp;
        greenPosition++;
        bluePosition++;
      }
      else if(A[i].color == 2)
      {
        bluePosition++;
      }
    }
  }
}

/*
Write a function that sorts an array of objects that have a “color” property:
– “color” can only take 3 distinct values: red, green and blue
– red =0< green=1 < blue=2
– The function must sort the array in-place
*/
