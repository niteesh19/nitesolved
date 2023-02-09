package common_medium;

import java.util.Arrays;

/*
Keywords: Sort By case, sort case, sortCase
given a String containing alphanumeric ASCII chars, sort accordingly:
all sorted lowercase ahead of uppercase
all sorted uppercase ahead of digits
all sorted even digits ahead of odd digits
 */
public class SortStringByCase {

  public static void main(String[] args) {
    String input = "Sorting12345";
    String[] inputStrArr = strToArray(input);
    sortDigits(input);
  }

  static String[] strToArray(String input) { return input.split(""); }

  static void sortDigits(String input) {
    input.replaceAll("[^0-9]", "");
  }

  static char[] stringToCharArr(String input) { return input.toCharArray(); }

  static boolean isAlpha(String name) { return name.matches("[a-zA-Z]+"); }

  private static boolean isStringUpperCase(String str) {
    //convert String to char array
    char[] charArray = str.toCharArray();
    for(int i=0; i < charArray.length; i++){
      //if the character is a letter
      if( Character.isLetter(charArray[i]) ){
        //if any character is not in upper case, return false
        if( !Character.isUpperCase( charArray[i] ))
          return false;
      }
    }
    return true;
  }

  static String sortString(String inputString)
  {
    char tempArray[] = inputString.toCharArray();
    Arrays.sort(tempArray);
    return new String(tempArray);
  }
}

/*
l,u,o,e=[],[],[],[] #define lower, upper, odd and even number lists.
for i in sorted(input()): #split the characters so string looks like [s,t,r,i,n,g]
    if i.isalpha(): #check if i is a letter
        x = u if i.isupper() else l #add x to upper list if it is an upper, if not, add it to l or the lower list. You can see `ternary operator` for more info.
    else: #if it is not a letter
        x = o if int(i)%2 else e # if the integer is even add it to e, if it is odd, add it to odd.
    x.append(i)
print("".join(l+u+o+e)) #join all the sorted letters in their respective order.

 */

/*;
upper = []
lower = []
even = []
odd = []

def separator(a):

    if a.isalpha():
        if a.isupper():
            upper.append(a)
        else:
            lower.append(a)
    else:
        if int(a)%2 == 0:
            even.append(a)
        else:
            odd.append(a)
    return

map(separator,raw_input())

upper.sort()
lower.sort()
even.sort()
odd.sort()

t = lower+upper+odd+even
map(lambda x: print(x,end=''),t)
 */