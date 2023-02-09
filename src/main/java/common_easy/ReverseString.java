package common_easy;

// Java program to ReverseString using ByteArray.
import java.lang.*;

// Class of ReverseString
class ReverseString {
  public static void main(String[] args)
  {
    String input = "GeeksforGeeks";

    // getBytes() method to convert string
    // into bytes[].
    byte[] strAsByteArray = input.getBytes();

    byte[] result = new byte[strAsByteArray.length];

    // Store result in reverse order into the
    // result byte[]
    for (int i = 0; i < strAsByteArray.length; i++)
      result[i] = strAsByteArray[strAsByteArray.length - i - 1];

    //Create a new String object using byte[] to
    //   store result.
    System.out.println(new String(result));
  }
}