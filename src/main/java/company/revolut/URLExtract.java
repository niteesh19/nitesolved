package company.revolut;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLExtract {
  public static void main(String[] args) {
    String urlTest = "http://www.example.com/abc?page=6&other=yes&filter=none";
    String queryParam = urlTest.substring(urlTest.lastIndexOf("?") + 1);
    System.out.println(queryParam);
    System.out.println(URLExtract.extractStringFromURL(urlTest,"other"));
  }

  public static String extractStringFromURL(String url, String par) {
    String extractedVal = "";
    Pattern p = Pattern.compile("[?&]"+par+"=([0-9A-Za-z]+)");
    Matcher m = p.matcher(url);
    m.find();
    try {
      extractedVal = m.group(1);
    } catch (java.lang.IllegalStateException e){
      e.printStackTrace();
    }
    return extractedVal;
  }
}


