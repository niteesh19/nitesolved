package array;

/**
 * Created by gouthamvidyapradhan on 12/12/2017. Given an array of citations (each citation is a
 * non-negative integer) of a researcher, write a function to compute the researcher's h-index.
 *
 * <p>According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her
 * N papers have at least h citations each, and the other N − h papers have no more than h citations
 * each."
 *
 * <p>For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in
 * total and each of them had received 3, 0, 6, 1, 5 citations respectively. Since the researcher
 * has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations
 * each, his h-index is 3.
 *
 * <p>Note: If there are several possible values for h, the maximum one is taken as the h-index.
 *
 * One solution is first sorting the array and then doing H-index-2 solution -> O(nlogn) + O(n)
 * <p>Solution O(n) Replace all the citations which are greater than n with n, the result will not
 * change with this operation. Maintain a count array with count of each citations. Sum up all the
 * counts from n -> 0 and store this in a array S. Value in array index Si is number of papers
 * having citations at least i.
 *
 * <p>The first value at index i, from right to left in array S which has i <= Si is the answer.
 */
public class HIndex {

  public static void main(String[] args) throws Exception {
    int[] A = {3, 0, 6, 1, 5};
    System.out.println(new HIndex().hIndexNew(A));
  }

  public int hIndexNew(int[] citations) {
    int[] bucket =new int[citations.length+1];  // 1 Extra space for storing numbers greater than the bucket length

    for (int citation : citations) {
      if (citation > citations.length)
        bucket[citations.length]++; //numbers greater than length will keep filling the last index
      else
        bucket[citation]++; //else will fill the corresponding index counts
    }
    // bucket: [1, 1, 0, 1, 0, 2]
    // and now we cumulate sum from back of array and if that matches i, we have the ans
    int count=0;
    for(int i=bucket.length-1; i>=0; i--){
      count+=bucket[i];
      if(count>=i)
        return i;
    }

    return 0;
  }
}
