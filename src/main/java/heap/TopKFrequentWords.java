package heap;

import java.util.*;

/**
 * Created by gouthamvidyapradhan on 07/04/2018. Given a non-empty list of words, return the k most
 * frequent elements.
 *
 * <p>Your answer should be sorted by frequency from highest to lowest. If two words have the same
 * frequency, then the word with the lower alphabetical order comes first.
 *
 * <p>Example 1: Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2 Output: ["i",
 * "love"] Explanation: "i" and "love" are the two most frequent words. Note that "i" comes before
 * "love" due to a lower alphabetical order. Example 2: Input: ["the", "day", "is", "sunny", "the",
 * "the", "the", "sunny", "is", "is"], k = 4 Output: ["the", "is", "sunny", "day"] Explanation:
 * "the", "is", "sunny" and "day" are the four most frequent words, with the number of occurrence
 * being 4, 3, 2 and 1 respectively. Note: You may assume k is always valid, 1 ≤ k ≤ number of
 * unique elements. Input words contain only lowercase letters. Follow up: Try to solve it in O(n
 * log k) time and O(n) extra space.
 *
 * <p>Solution: O(n log k). Calculate frequency and maintain a inverse priority queue of size k and
 * add elements. Return result by reversing the priority queue elements.
 */
public class TopKFrequentWords {

  static class Pair {
    String word;
    int freq;

    Pair(String word, int freq) {
      this.word = word;
      this.freq = freq;
    }
  }

  /**
   * Main method
   */
  public static void main(String[] args) {
    String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
    List<String> sorted = new TopKFrequentWords().topKFrequentApproach1(words, 2);
    sorted.forEach(System.out::println);
  }

  public List<String> topKFrequentApproach1(String[] words, int k) {
    if (words == null || words.length == 0) {
      return new ArrayList<>();
    }

    Map<String, Integer> map = new HashMap<>();
    for (String s: words) {
      map.put(s, map.getOrDefault(s, 0) + 1);
    }

    List<String> candidates = new ArrayList<>(map.keySet());
    candidates.sort((w1, w2) -> !map.get(w1).equals(map.get(w2)) ?
        map.get(w2) - map.get(w1) : w1.compareTo(w2));

    return candidates.subList(0, k);
  }

  public List<String> topKFrequentApproach2(String[] words, int k) {
    Map<String, Integer> map = new HashMap<>();
    for (String w : words) {
      map.putIfAbsent(w, 0);
      int freq = map.get(w);
      map.put(w, freq + 1);
    }
    Queue<Pair> pq =
        new PriorityQueue<>(
            (o1, o2) ->
                (o1.freq == o2.freq)
                    ? o2.word.compareTo(o1.word)
                    : Integer.compare(o1.freq, o2.freq));
    for (String w : map.keySet()) {
      int f = map.get(w);
      pq.offer(new Pair(w, f));
      if (pq.size() > k) {
        pq.poll();
      }
    }
    List<String> result = new ArrayList<>();
    while (!pq.isEmpty()) {
      result.add(pq.poll().word);
    }
    Collections.reverse(result);
    return result;
  }
}
