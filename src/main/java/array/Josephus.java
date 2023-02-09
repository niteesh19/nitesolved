package array;

// Java code to Implement Josephus Problem

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * There are N people standing in a circle waiting to be executed.
 * The counting out begins at some point in the circle and proceeds around the circle in a fixed direction.
 * In each step, a certain number of people are skipped and the next person is executed.
 * The elimination proceeds around the circle
 * (which is becoming smaller and smaller as the executed people are removed),
 * until only the last person remains, who is given freedom.
 * Given the total number of persons N and a number k which indicates that k-1 persons are skipped
 * and the kth person is killed in a circle. The task is to choose the person in the initial circle that survives.
 */
class Josephus {

  //Brute force O(N^2)
  //  k--; // (k-1)th person will be killed
  //  int index = 0;
  //  List<Integer> person = new ArrayList<>();
  //    // fill the person vector
  //    for (int i = 1; i <= n; i++) {
  //      person.add(i);
  //    }
  static void JoshBrute(List<Integer> person, int k, int index) {
    // Base case , when only one person is left
    if (person.size() == 1) {
      System.out.println(person.get(0));
      return;
    }

    // find the index of first person which will die
    index = ((index + k) % person.size());

    // remove the first person which is going to be killed
    person.remove(index);

    // recursive call for n-1 persons
    JoshBrute(person, k, index);
  }

  // Optimised Iterative method. Takes O(N) time and space
  public static int josephIter(int noOfPeople, int kPos) {
    int tempPos = kPos - 1;
    int[] people = new int[noOfPeople];

    for (int i = 0; i < noOfPeople; i++) {
      people[i] = i + 1;
    }

    int iteration = noOfPeople - 1;

    List<Integer> list = IntStream.of(people).boxed().collect(Collectors.toList());

    while (iteration > 0) {
      list.remove(tempPos);   //Remove at index
      // calc next position to remove i.e. adding current tempPos with kPos
      // and then sub 1, as the person who was killed and removed from the list
      tempPos += kPos - 1;
      if (tempPos > list.size() - 1) {    //since standing in a circle
        tempPos = tempPos % list.size();  //we have to modulo with curr list size
      }
      iteration--;
    }

    return list.get(0);
  }

  static int josephusRecur(int n, int k) {
    if (n == 1)
      return 1;
    else
			/* The position returned by josephus(n - 1, k)
			is adjusted because the recursive call
			josephus(n - 1, k) considers the original
			position k%n + 1 as position 1 */
      return (josephusRecur(n - 1, k) + k - 1) % n + 1;
  }

  // main function
  public static void main(String[] args) {

    int N = 12, k = 3;
//    int N = 6, k = 2;
//    int ans = josephusRecur(N, k);
    int ans = josephIter(N, k);
    System.out.println(ans);
  }
}

// This code is presented by Akash Mangal
