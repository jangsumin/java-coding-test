// 문서 검색
package baekjoon;

import java.io.IOException;
import java.util.Scanner;

public class Main_1543 {
  public static void main(String[] args) throws IOException {
    Scanner sc = new Scanner(System.in);
    String[] input_str = sc.nextLine().split("");
    String[] to_find = sc.nextLine().split("");

    int cnt = 0;
    int skip = 0;
    for (int i = 0; i < input_str.length - to_find.length + 1; i += skip) {
      Boolean isSame = true;
      for (int j = 0; j < to_find.length; j++) {
        if (!(input_str[i + j].equals(to_find[j]))) {
          isSame = false;
          break;
        }
      }
      if (isSame) {
        cnt++;
        skip = to_find.length;
      } else {
        skip = 1;
      }
    }
    
    System.out.println(cnt);
    sc.close();
  }
}