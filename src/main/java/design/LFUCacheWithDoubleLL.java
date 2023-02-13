package design;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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

  //This solution uses TreeSet for storing sorted frequency and map and hence is O(logn)
  // for O(1), we should use LinkedHashSet
public class LFUCacheWithDoubleLL {

  private final TreeMap<Integer, DoubleLinkedList> sortedCountMap = new TreeMap<>();
  private final Map<Integer, Node> valueMap = new HashMap<>();
  private final Map<Integer, Integer> countMap = new HashMap<>();
  private final int size;
  public LFUCacheWithDoubleLL(int n) {
    size = n;
  }

  public static void main(String[] args) {
    LFUCacheWithDoubleLL cache1 = new LFUCacheWithDoubleLL(2);
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

  public int get(int key) {
    if (!valueMap.containsKey(key) || size == 0) {
      return -1;
    }
    Node nodeToRemove = valueMap.get(key);
    Node node = new Node(key, nodeToRemove.getValue());
    int count = countMap.get(key);
    sortedCountMap.get(count).remove(nodeToRemove);
    removeEntryFromSortedCountMapIfListIsEmpty(count);
    valueMap.remove(key);
    countMap.remove(key);
    valueMap.put(key, node);
    countMap.put(key, count + 1);
    sortedCountMap.computeIfAbsent(count + 1, k -> new DoubleLinkedList()).add(node);
    return valueMap.get(key).getValue();
  }

  public void put(int key, int value) {
    if (!valueMap.containsKey(key) && size > 0) {

      Node node = new Node(key, value);
      if (valueMap.size() == size) {
        int lowestFrequency = sortedCountMap.firstKey();
        Node nodeToRemove = sortedCountMap.get(lowestFrequency).getHead();
        sortedCountMap.get(lowestFrequency).remove(nodeToRemove);
        removeEntryFromSortedCountMapIfListIsEmpty(lowestFrequency);

        int keyToRemove = nodeToRemove.getKey();
        valueMap.remove(keyToRemove);
        countMap.remove(keyToRemove);
      }
      sortedCountMap.computeIfAbsent(1, k -> new DoubleLinkedList()).add(node);
      valueMap.put(key, node);
      countMap.put(key, 1);

    } else if (valueMap.containsKey(key) && size > 0) {
      Node node = new Node(key, value);
      Node nodeToRemove = valueMap.get(key);
      int frequency = countMap.get(key);
      sortedCountMap.get(frequency).remove(nodeToRemove);
      removeEntryFromSortedCountMapIfListIsEmpty(frequency);
      valueMap.remove(key);
      countMap.remove(key);
      valueMap.put(key, node);
      countMap.put(key, frequency + 1);
      sortedCountMap.computeIfAbsent(frequency + 1, k -> new DoubleLinkedList()).add(node);
    }
  }

  private void removeEntryFromSortedCountMapIfListIsEmpty(int frequency) {
    if (sortedCountMap.get(frequency).size() == 0) {
      sortedCountMap.remove(frequency);
    }
  }

  public static class Node {

    private final int key;
    private final int value;
    Node next;
    Node previous;

    public Node(int key, int value) {
      super();
      this.key = key;
      this.value = value;
    }

    public Node getNext() {
      return next;
    }

    public Node getPrevious() {
      return previous;
    }

    public int getKey() {
      return key;
    }

    public int getValue() {
      return value;
    }
  }

  public static class DoubleLinkedList {

    Node head;
    Node tail;
    private int n;

    public void add(Node node) {

      if (head == null) {

        head = node;
      } else {

        tail.next = node;
        node.previous = tail;
      }
      tail = node;
      n++;
    }

    public void remove(Node node) {

      if (node.next == null) {
        tail = node.previous;

      } else {

        node.next.previous = node.previous;
      }

      if (node.previous == null) {

        head = node.next;
      } else {

        node.previous.next = node.next;
      }

      n--;
    }

    public int size() {

      return n;
    }

    public Node getHead() {
      return head;
    }
  }
}
