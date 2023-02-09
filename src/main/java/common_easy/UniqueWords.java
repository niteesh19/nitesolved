package common_easy;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;

/**
 * Unique Words, Distinct Words
 */

public class UniqueWords {
  public static void main(String[] args) {
    String str = "apple banana mango grape lichi mango apple grape";
    printDistinctWords(str);
    System.out.println("===============");
    printOnlyUniqueWords(str);
  }

  static void printDistinctWords(String str) {
    String[] words = str.split(" ");
    HashSet<String> uniqueWords = new HashSet<>(Arrays.asList(words));
    for (String s : uniqueWords)
      System.out.println(s);
  }

  static void printOnlyUniqueWords(String str) {
    HashMap<String, Integer> map = new HashMap<>();
    String[] words = str.split("\\W");
    for (String word : words) {
      if (map.containsKey(word)) {
        //  Increment its value by one
        // using map.get() method
        map.put(word, map.get(word) + 1);
      }
      else map.put(word, 1);   // Else store the word inside map with value one
    }
    System.out.println(map);
    for (Map.Entry<String, Integer> entry : map.entrySet()) {
      if (entry.getValue() == 1)
        System.out.println(entry.getKey());
    }
  }

  /**
   * Most performant
   * The "mixed" approach which does not use streams at all, but uses the "merge" method with callback
   * introduced in Java 8 does improve the performance.
   * This is something I expected because the classic get/put appraoch requires the key to be looked up twice in the HashMap
   * and this is not required anymore with the "merge"-approach.
   */
  private void countWordsMixed(final Path path) throws IOException {
    final Map<String, Integer> wordCounts = new HashMap<>();
    for (String word : Files.readString(path).split("\\W+")) {
      wordCounts.merge(word, 1, (key, oldCount) -> oldCount + 1);
    }
  }

  private void countWordsStream(final Path tmpFile) throws IOException {
    Arrays.stream(Files.readString(tmpFile).split("\\W+"))
        .collect(Collectors.groupingBy(Function.<String>identity(), HashMap::new, counting()));
  }

  private void countWords(final Path file) throws IOException {
    Arrays.stream(new String(Files.readAllBytes(file), StandardCharsets.UTF_8).split("\\W+"))
        .collect(Collectors.groupingBy(Function.<String>identity(), TreeMap::new, counting())).entrySet()
        .forEach(System.out::println);
  }
}
