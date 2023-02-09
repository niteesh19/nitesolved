package streams_ex;

import java.util.*;

import static com.google.common.collect.Lists.newArrayList;

/*
Sorting a Hashmap according to values
Sort a map by value
 */
public class MapSortingEx {

  public static void main(String[] args) {
    System.out.println("\nSorting using Java8 streams\n");

    List<Integer> integers = newArrayList(1, 2, 3);
    for (Integer integer : integers) {
      integers.remove(integer);     // list remove method
    }

    sortByValueJava8Stream();
    sortByValue();
  }

  private static void sortByValueJava8Stream() {
    Map<String, Integer> unSortedMap = getUnSortedMap();

    System.out.println("Unsorted Map : " + unSortedMap);

    LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
    unSortedMap.entrySet().stream()
        .sorted(Map.Entry.comparingByValue())
        .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));

    System.out.println("Sorted Map   : " + sortedMap);

    LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<>();
    unSortedMap.entrySet().stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

    System.out.println("Reverse Sorted Map   : " + reverseSortedMap);
  }

  private static Map<String, Integer> getUnSortedMap() {
    Map<String, Integer> unsortMap = new HashMap<>();
    unsortMap.put("alex", 1);
    unsortMap.put("david", 2);
    unsortMap.put("elle", 3);
    unsortMap.put("charles", 4);
    unsortMap.put("brian", 5);
    return unsortMap;
  }

  public static HashMap<String, Integer> sortByValue() {
    Map<String, Integer> hm = getUnSortedMap();
    // Create a list from elements of HashMap
    List<Map.Entry<String, Integer>> list = new LinkedList<>(hm.entrySet());

    // Sort the list
    list.sort(Map.Entry.comparingByValue());

    // put data from sorted list to hashmap
    HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
    for (Map.Entry<String, Integer> aa : list) {
      temp.put(aa.getKey(), aa.getValue());
    }
    return temp;
  }
}
