package common_medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SubdomainVisit {
  public List<String> subdomainVisits(String[] cpdomains) {
    Map<String, Integer> map = new HashMap<>();
    for (String domain : cpdomains) {   //  "900 google.mail.com", "50 yahoo.com"
      String[] strs = domain.split("\\s+");   //  900, google.mail.com
      int count = Integer.parseInt(strs[0]);
      String[] lowerDomains = strs[1].split("\\.");   //  google    mail    com
      StringBuilder sb = new StringBuilder();
      for (int i = lowerDomains.length - 1; i >= 0; i--) {
        sb.insert(0, lowerDomains[i]);  //  com --- mail.com
        map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) + count); //  com, 900 --- mail.com, 900
        sb.insert(0, '.');  //  .com --- //.mail.com
      }
    }
    List<String> list = new ArrayList<>();
    for (String key : map.keySet()) {
      list.add(map.get(key) + " " + key);
    }
    return list;
  }

  public static void main(String[] args) {
    System.out.println(new SubdomainVisit().subdomainVisits(new String[]{"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"}));
  }

}