package design;

import java.util.Iterator;
import java.util.LinkedHashMap;

public class LRUCacheSimple {

  private int capacity;
  private LinkedHashMap<Integer,Integer> map;

  public LRUCacheSimple(int capacity) {
    this.capacity = capacity;

    /*
    Default initial capacity of the HashMap takes is 16 and load factor is 0.75f (i.e 75% of current map size).
    The load factor represents at what level the HashMap capacity should be doubled.

    For example product of capacity and load factor as 16 * 0.75 = 12.
    This represents that after storing the 12th key – value pair into the HashMap ,
    its capacity becomes 32.
     */
    //accessOrder – the ordering mode - true for access-order, false for insertion-order
    this.map = new LinkedHashMap<>(16, 0.75f, true);
  }

  public int get(int key) {
    Integer value = this.map.get(key);
    if (value == null) {
      value = -1;
    }
    return value;
  }

  public void put(int key, int value) {
    if (
        !this.map.containsKey(key) &&
            this.map.size() == this.capacity
    ) {
      Iterator<Integer> it = this.map.keySet().iterator();
      it.next();
      it.remove();
    }
    this.map.put(key, value);
  }

  public static void main(String[] args) {
    LRUCacheSimple c;

    // Starts empty.
    c = new LRUCacheSimple(2);
    assert c.get(1) == -1;

    // Below capcity.
    c = new LRUCacheSimple(2);
    c.put(1, 1);
    assert c.get(1) == 1;
    assert c.get(2) == -1;
    c.put(2, 4);
    assert c.get(1) == 1;
    assert c.get(2) == 4;

    // Above capacity, oldest is removed.
    c = new LRUCacheSimple(2);
    c.put(1, 1);
    c.put(2, 4);
    c.put(3, 9);
    assert c.get(1) == -1;
    assert c.get(2) == 4;
    assert c.get(3) == 9;

    // get renews entry
    c = new LRUCacheSimple(2);
    c.put(1, 1);
    c.put(2, 4);
    assert c.get(1) == 1;
    c.put(3, 9);
    assert c.get(1) == 1;
    assert c.get(2) == -1;
    assert c.get(3) == 9;

    // Double put does not remove due to capacity.
    c = new LRUCacheSimple(2);
    assert c.get(2) == -1;
    c.put(2, 6);
    assert c.get(1) == -1;
    c.put(1, 5);
    c.put(1, 2);
    assert c.get(1) == 2;
    assert c.get(2) == 6;
  }
}