package string;

// Java program for Naive Pattern Searching
//Matches exact pattern in the string
//O(m(n-m))
public class PatternSearch1 {

  public static void search(String txt, String pat) {
    int patLen = pat.length();
    int txtLen = txt.length();

    /* A loop to slide txt one by one */
    //Last index to search will be txtLen - patLen
    for (int i = 0; i <= txtLen - patLen; i++) {

      int j;
      /* For current index i, check for pattern
        match */
      for (j = 0; j < patLen; j++)
        if (txt.charAt(i + j) != pat.charAt(j))
          break;

      if (j == patLen) // if pat[0...M-1] = txt[i, i+1, ...i+M-1]
        System.out.println("Pattern found at index " + i);
    }
  }

  public static void main(String[] args) {
    String txt = "AABAACAADAABAAABAA";
    String pat = "AABA";
    search(txt, pat);
  }
}