package company.mmt;

import java.util.Arrays;

/*
number of people, n = 6, standing in queue in front of them is given by array ‘Infront’: [0, 1, 2, 0, 3, 2]
Find the height of all person considering all heights are unique.
 */
public class PersonHeights {

  public static void getHeights(int[] a, int n) {
    int[] adup = new int[n];
    int[] hdup = new int[n+1];
    for(int i=n-1; i>=0; i--) {
      int heightTmp = n - a[i];
      if(hdup[heightTmp] == 1){
        for (int j=heightTmp; j > 0; j--) {
          if (hdup[j] != 1) {
            adup[i] = j;
            hdup[j] = 1;
            break;
          }
        }
      } else {
        adup[i] = heightTmp;
        hdup[heightTmp] = 1;
      }

    }
    System.out.println(Arrays.toString(adup));
  }

  public static void main(String[] args) {
//    int[] a = {0,0,0,2,2,4};
//    int n = 6;
    int[] a = {0,1,1};
    int n = 3;
    getHeights(a, n);
  }
}