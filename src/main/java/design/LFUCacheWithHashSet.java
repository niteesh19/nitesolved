package design;

import org.openjdk.tools.javac.util.Pair;

import java.util.*;

/**
 * Design and implement a data structure for Least Frequently Used (LFU) cache. It should support
 * the following operations: get and put.
 *
 * <p>get(key) - Get the value (will always be positive) of the key if the key exists in the cache,
 * otherwise return -1. put(key, value) - Set or insert the value if the key is not already present.
 * When the cache reaches its capacity, it should invalidate the least frequently used item before
 * inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more
 * keys that have the same frequency), the least recently used (LRU) key would be evicted.
 *
 * <p>Follow up: Could you do both operations in O(1) time complexity?
 *
 * <p>Example:
 *
 * <p>LFUCache cache = new LFUCache( 2 /* capacity
 */
/*
       cache.put(1, 1);
       cache.put(2, 2);
       cache.get(1);       // returns 1
       cache.put(3, 3);    // evicts key 2
       cache.get(2);       // returns -1 (not found)
       cache.get(3);       // returns 3.
       cache.put(4, 4);    // evicts key 1.
       cache.get(1);       // returns -1 (not found)
       cache.get(3);       // returns 3
       cache.get(4);       // returns 4
*/
public class LFUCacheWithHashSet {

  // key: original key, value: frequency and original value.
  private Map<Integer, Pair<Integer, Integer>> cache;
  // key: frequency, value: All keys that have the same frequency.
  private Map<Integer, LinkedHashSet<Integer>> frequencies;
  private int minf;
  private int capacity;
  public LFUCacheWithHashSet(int capacity) {
    cache = new HashMap<>();
    frequencies = new HashMap<>();
    minf = 0;
    this.capacity = capacity;
  }

  public static void main(String[] args) {
    LFUCacheWithHashSet cache1 = new LFUCacheWithHashSet(2);
    cache1.put(1, 1);
    cache1.put(2, 2);
    System.out.println(cache1.get(1));
    cache1.put(3, 3);
    System.out.println(cache1.get(2));
    System.out.println(cache1.get(3));
    cache1.put(4, 4);
    System.out.println(cache1.get(1));
    System.out.println(cache1.get(3));
    System.out.println(cache1.get(4));
  }

  private void insert(int key, int frequency, int value) {
    cache.put(key, new Pair<>(frequency, value));
    frequencies.putIfAbsent(frequency, new LinkedHashSet<>());
    frequencies.get(frequency).add(key);
  }

  public int get(int key) {
    Pair<Integer, Integer> frequencyAndValue = cache.get(key);
    if (frequencyAndValue == null) {
      return -1;
    }
    final int frequency = frequencyAndValue.fst;
    final Set<Integer> keys = frequencies.get(frequency);
    keys.remove(key);
    if (keys.isEmpty()) {
      frequencies.remove(frequency);

      if (minf == frequency) {
        ++minf;
      }
    }
    final int value = frequencyAndValue.snd;
    insert(key, frequency + 1, value);
    return value;
  }

  public void put(int key, int value) {
    if (capacity <= 0) {
      return;
    }
    Pair<Integer, Integer> frequencyAndValue = cache.get(key);
    if (frequencyAndValue != null) {
      cache.put(key, new Pair<>(frequencyAndValue.fst, value));
      get(key);
      return;
    }
    if (capacity == cache.size()) {
      final Set<Integer> keys = frequencies.get(minf);
      final int keyToDelete = keys.iterator().next();
      cache.remove(keyToDelete);
      keys.remove(keyToDelete);
      if (keys.isEmpty()) {
        frequencies.remove(minf);
      }
    }
    minf = 1;
    insert(key, 1, value);
  }
}
