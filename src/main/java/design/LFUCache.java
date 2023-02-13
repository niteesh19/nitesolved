package design;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * Design and implement a data structure for Least
 * Frequently Used (LFU) cache. It should support the following operations: get and put.
 *
 * <p>get(key) - Get the value (will always be positive) of the key if the key exists in the cache,
 * otherwise return -1. put(key, value) - Set or insert the value if the key is not already present.
 * When the cache reaches its capacity, it should invalidate the least frequently used item before
 * inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more
 * keys that have the same frequency), the least recently used key would be evicted.
 *
 * <p>Follow up: Could you do both operations in O(1) time complexity?
 *
 * <p>Example:
 *
 * <p>LFUCache cache = new LFUCache( 2 /* capacity
 */
/*)

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

  // IMPORTANT: This solution doesn't comply with LRU eviction which is required in the problem statement.
  // In this eviction is FIFO based.
public class LFUCache {
  private static int initialCapacity = 10;
  private static LinkedHashMap<Integer, CacheEntry> cacheMap =
      new LinkedHashMap<Integer, CacheEntry>();

  public LFUCache(int initialCapacity) {
    this.initialCapacity = initialCapacity;
  }
  /* LinkedHashMap is used because it has features of both HashMap and LinkedList.
   * Thus, we can get an entry in O(1) and also, we can iterate over it easily.
   * */

  public static boolean isFull() {
    if (cacheMap.size() == initialCapacity) return true;

    return false;
  }

  public void addCacheEntry(int key, String data) {
    if (!isFull()) {
      CacheEntry temp = new CacheEntry();
      temp.setData(data);
      temp.setFrequency(0);

      cacheMap.put(key, temp);
    } else {
      int entryKeyToBeRemoved = getLFUKey();
      cacheMap.remove(entryKeyToBeRemoved);

      CacheEntry temp = new CacheEntry();
      temp.setData(data);
      temp.setFrequency(0);

      cacheMap.put(key, temp);
    }
  }

  //This will run in O(n) and is less optimised. We can use min Heap for O(logn) optimisation
  public int getLFUKey() {
    int key = 0;
    int minFreq = Integer.MAX_VALUE;

    for (Map.Entry<Integer, CacheEntry> entry : cacheMap.entrySet()) {
      if (minFreq > entry.getValue().frequency) {
        key = entry.getKey();
        minFreq = entry.getValue().frequency;
      }
    }

    return key;
  }

  public String getCacheEntry(int key) {
    if (cacheMap.containsKey(key)) // cache hit
    {
      CacheEntry temp = cacheMap.get(key);
      temp.frequency++;
      cacheMap.put(key, temp);
      return temp.data;
    }
    return null; // cache miss
  }

  class CacheEntry {
    private String data;
    private int frequency;

    // default constructor
    private CacheEntry() {}

    public String getData() {
      return data;
    }

    public void setData(String data) {
      this.data = data;
    }

    public int getFrequency() {
      return frequency;
    }

    public void setFrequency(int frequency) {
      this.frequency = frequency;
    }
  }
}
