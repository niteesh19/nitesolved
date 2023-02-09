package common_medium;

import java.util.HashMap;
import java.util.Map;

public class LongestAbsolutePath {

  public static void main(String[] args) {
//    System.out.println("ANS>>"+new LongestAbsolutePath()
//        .lengthOfLargestPath("dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"));
//    System.out.println("ANS>>"+new LongestAbsolutePath()
//        .lengthOfLargestPath("dir\n\tsubdir1\n\t\tfile1fgdfgdfgdfgdfgdfasdasdgdfgdfgdd.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"));

    System.out.println("ANS>>"+new LongestAbsolutePath()
        .lengthLongestPath("dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"));
    System.out.println("ANS>>"+new LongestAbsolutePath()
        .lengthLongestPath("dir\n\tsubdir1\n\t\tfile1fgdfgdfgdfgdfgdfasdasdgdfgdfgdd.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"));

  }

  public int lengthLongestPath(String input) {
    if (input.length() == 0)
      return 0;
    Map<Integer, Integer> map = new HashMap<>();
    int maxLength = 0;
    String[] paths = input.split("\n");
    for (String path : paths) {
      String dirOrFile = path.replaceFirst("\\t*", "");
      int level = path.lastIndexOf("\t") + 1;

      int prefixLength = 0;
      if (level > 0) {
        prefixLength = map.get(level - 1);
      }

      int pathLength = prefixLength + dirOrFile.length();
      if (dirOrFile.contains(".")) {
        maxLength = Math.max(pathLength + level, maxLength);
      }
      map.put(level, pathLength);
    }
    return maxLength;
  }


  public int lengthOfLargestPath(String input) {

    String[] fileRows = input.split("\n");
    int[] dirs = new int[fileRows.length];
    int max = 0;
    StringBuilder dirPath = new StringBuilder();
    for (String fileRow : fileRows) {
      String s = fileRow;
      int count = s.lastIndexOf("\t") + 1;
      s = s.substring(count);
      System.out.println(s);
      if (s.contains(".")) {
        int fileCount = s.length();
        if (count != 0) {
          fileCount = dirs[count - 1] + s.length();
        }
        System.out.println(fileCount);
        max = Math.max(fileCount, max);
      } else {
        dirs[count] = s.length();
        if (count != 0) {
          dirs[count] = dirs[count] + dirs[count - 1];
        }
        System.out.println(dirs[count]);
      }
    }

    return max;

  }

}
