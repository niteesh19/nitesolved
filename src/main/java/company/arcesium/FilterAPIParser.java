package company.arcesium;

import com.google.gson.Gson;

import java.io.*;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.net.HttpURLConnection.HTTP_CREATED;
import static java.net.HttpURLConnection.HTTP_OK;

/* build a search service that help identify relevant contacts based on certain search criteria
    https://raw.githubusercontent.com/arcjsonapi/ApiSampleData/master/api/users

  2. The search criteria based on which the contacts need to be searched are provided in the form of an input
  obj as explained below:
  Use gson lib for parsing json

  input will be a str list containing filter criteria in the format:
  [<Obj field name>, <operation type>, <value>]

  address.street EQUALS/IN someval

  output:
  List of integer to return matching ids
  if not found, then -1

  Constraints:
   - For EQUALS & IN operations, perform case-sensitive match
   - Max nested obj level is 2
   - Criteria has 3 args passed as array of str

  Input: ["username", "EQUALS", "vinayk"]
  Output: [1]

  Input: ["address.city", "EQUALS", "Kolkata"]
  Output: [2]

  Input: ["address.city", "IN", "Mumbai,Kolkata"]
  Output: [1,2]

  Input: ["username", "EQUALS", "Tom"]
  Output: [-1]
 */
public class FilterAPIParser {

  public static void main(String[] args) {

    //Collections.unmodifiableList(Arrays.asList("username", "EQUALS", "vinayk"));
    System.out.println(apiResponseParser(List.of("username", "EQUALS", "Garimag"), 3));
    System.out.println(apiResponseParser(List.of("address.city", "EQUALS", "Surat"), 3));
    System.out.println(apiResponseParser(List.of("address.city", "IN", "Mumbai,Kolkata"), 3));
    System.out.println(apiResponseParser(List.of("username", "EQUALS", "Tom"), 3));
//    
  }

  @Retention(RetentionPolicy.RUNTIME)
  @interface Column {
    String value();
  }

  public static List<Integer> apiResponseParser(List<String> inputList, int size) {

    if (inputList.size() != size) {
      Logger.getLogger(FilterAPIParser.class.getName()).log(Level.SEVERE, "ERROR: INVALID INPUT");
      return Collections.emptyList();
    }
    final String jsonResp;
    final List<Integer> results = new ArrayList<>();
    try {
      jsonResp = readJSONFromGETApi(
          new URL("https://raw.githubusercontent.com/arcjsonapi/ApiSampleData/master/api/users"));
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
    Gson gson = new Gson();
    List<User> userResp = List.of(gson.fromJson(jsonResp, User[].class));
    for (User user : userResp) {
      try {
        Object val = getFieldValue(user, inputList.get(0));
        switch (inputList.get(1)) {
          case "EQUALS":
            if (inputList.get(2).equals(val)) {
              results.add(user.id);
            }
            break;
          case "IN":
            for(String inputVal: inputList.get(2).split(",")) {
              if(inputVal.equals(val))
                results.add(user.id);
            }
            break;
        }
      } catch (NoSuchFieldException | IllegalAccessException e) {
        throw new RuntimeException(e);
      }
    }

    return results.size() == 0 ? List.of(-1) : results;
  }

  public static Object getFieldValue(Object object, String fieldName) throws NoSuchFieldException, IllegalAccessException {
    String[] fields = fieldName.split("\\.");
    Field field = object.getClass().getDeclaredField(fields[0]);
//    field.setAccessible(true);
    Object val = field.get(object);
    if (val == null) {
      return null;
    } else if (fields.length > 1) {
      return getFieldValue(val, fieldName.substring(fieldName.indexOf(".") + 1));
    } else {
      return val;
    }
  }

  public static String readJSONFromGETApi(final URL url) {
    // setting a default read and connection timeout of 1 min for all GETs
    return readJSONFromGETApi(url, 60000);
  }

  // Utility method
  public static String readJSONFromGETApi(final URL url, int timeout) {
    final StringBuilder sb = new StringBuilder();
    HttpURLConnection conn = null;
    try {
      conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      conn.setRequestProperty("Content-length", "0");
      conn.setRequestProperty("accept", "application/json");
      conn.setUseCaches(false);
      conn.setAllowUserInteraction(false);
      conn.setConnectTimeout(timeout);
      conn.setReadTimeout(timeout);
      conn.connect();
      int status = conn.getResponseCode();

      switch (status) {
        case HTTP_CREATED:
        case HTTP_OK: {
          try (final BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
              sb.append(line).append("\n");
            }
          }
          return sb.toString();
        }
      }

    } catch (IOException ex) {
      Logger.getLogger(FilterAPIParser.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      if (conn != null) {
        try {
          conn.disconnect();
        } catch (Exception ex) {
          Logger.getLogger(FilterAPIParser.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
    return null;
  }

  public final class User {
    @Column("id")
    private final Integer id;
    @Column("name")
    private final String name;
    @Column("username")
    private final String username;
    @Column("email")
    private final String email;
    @Column("address")
    private final Address address;
    @Column("website")
    private final String website;
    @Column("company")
    private final Company company;

    public User(Integer id, String name, String username, String email, Address address, String website, Company company) {
      this.id = id;
      this.name = name;
      this.username = username;
      this.email = email;
      this.address = address;
      this.website = website;
      this.company = company;
    }

    public Integer getId() {
      return id;
    }

    public String getName() {
      return name;
    }

    public String getUsername() {
      return username;
    }

    public String getEmail() {
      return email;
    }

    public Address getAddress() {
      return address;
    }

    public String getWebsite() {
      return website;
    }

    public Company getCompany() {
      return company;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      User user = (User) o;
      return Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
      return Objects.hash(getId());
    }

    @Override
    public String toString() {
      return "User{" +
          "id=" + id +
          ", name='" + name + '\'' +
          ", username='" + username + '\'' +
          ", email='" + email + '\'' +
          ", address=" + address +
          ", website='" + website + '\'' +
          ", company=" + company +
          '}';
    }
  }

  public class Address {
    @Column("street")
    private final String street;
    @Column("suite")
    private final String suite;
    @Column("city")
    private final String city;
    @Column("zipcode")
    private final String zipcode;
    @Column("geo")
    private final Geo geo;

    public Address(String street, String suite, String city, String zipcode, Geo geo) {
      this.street = street;
      this.suite = suite;
      this.city = city;
      this.zipcode = zipcode;
      this.geo = geo;
    }

    public String getStreet() {
      return street;
    }

    public String getSuite() {
      return suite;
    }

    public String getCity() {
      return city;
    }

    public String getZipcode() {
      return zipcode;
    }

    public Geo getGeo() {
      return geo;
    }
  }

  public final class Geo {
    @Column("lat")
    private final String lat;
    @Column("lng")
    private final String lng;

    public Geo(String lat, String lng) {
      this.lat = lat;
      this.lng = lng;
    }

    public String getLat() {
      return lat;
    }

    public String getLng() {
      return lng;
    }
  }

  public final class Company {
    @Column("name")
    private final String name;
    @Column("basename")
    private final String basename;

    public Company(String name, String basename) {
      this.name = name;
      this.basename = basename;
    }

    public String getName() {
      return name;
    }

    public String getBasename() {
      return basename;
    }
  }

}
