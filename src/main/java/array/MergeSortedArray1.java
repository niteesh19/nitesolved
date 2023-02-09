package array;

import java.util.Arrays;

public class MergeSortedArray1 {

  public static int[] merge(int[] foo, int[] bar) {

    int fooLength = foo.length;
    int barLength = bar.length;

    int[] merged = new int[fooLength + barLength];

    int fooPosition, barPosition, mergedPosition;
    fooPosition = barPosition = mergedPosition = 0;

    while(fooPosition < fooLength && barPosition < barLength) {
      if (foo[fooPosition] < bar[barPosition]) {
        merged[mergedPosition++] = foo[fooPosition++];
      } else {
        merged[mergedPosition++] = bar[barPosition++];
      }
    }

    while (fooPosition < fooLength) {
      merged[mergedPosition++] = foo[fooPosition++];
    }

    while (barPosition < barLength) {
      merged[mergedPosition++] = bar[barPosition++];
    }

    return merged;
  }

  public static void main(String[] args) {
    int[] A = {0, 2, 4, 7};
    int[] B = {1, 2, 3 , 5, 6, 9};
    System.out.println(Arrays.toString(MergeSortedArray1.merge(A, B)));
  }

}
