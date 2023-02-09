package binary_search;

/**
 * Created by gouthamvidyapradhan on 11/12/2017.
 *
 * <p>Follow up for H-Index: What if the citations array is sorted in ascending order? Could you
 * optimize your algorithm?
 * <p>
 *  Solution: O(n) -> compare the last array value with its index starting from back (index = 1 for last element )
 *
 * @see array.HIndex
 */
public class HIndexII {
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[] A = {1, 1, 1, 1, 1, 15, 20};
    int[] B = {1, 1, 2, 2, 2, 2, 20};
    int[] C = {1, 2, 3, 3, 3};
    int[] D = {1, 2, 2, 6, 7, 8};

    System.out.println(hIndexNew(A));
    System.out.println(hIndexNew(B));
    System.out.println(hIndexNew(C));
    System.out.println(hIndexNew(D));
    System.out.println(hIndexBS(A));
    System.out.println(hIndexBS(B));
    System.out.println(hIndexBS(C));
    System.out.println(hIndexBS(D));
  }

  static int hIndexNew(int[] citations) {
    int n = citations.length, i;
    for(i = 1; i <= n; i++)
      if(citations[n-i] < i) break;
    return i-1;
  }

  // H index Binary search

  static int hIndexBS(int[] citations) {
    int len = citations.length;
    int low = 0;
    int high = len - 1;
    int h_index = 0;
    if(len == 0)
      return 0;
    while(low <= high) {
      int mid = low + ((high - low) / 2);
      // Condition to find the h-index:
      // citations[index] >= length(citations) - index
      if(citations[mid] >= len - mid) {
        h_index = len - mid;
        high = mid - 1;
      }
      else {
        low = mid + 1;
      }
    }
    return h_index;
  }

}
