// 단어 공부
package baekjoon;

import java.io.*;
import java.util.HashMap;

public class Main_1157 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] input_str = br.readLine().split("");

    HashMap<String, Integer> map = new HashMap<>(){{
      put("A", 0);
      put("B", 0);
      put("C", 0);
      put("D", 0);
      put("E", 0);
      put("F", 0);
      put("G", 0);
      put("H", 0);
      put("I", 0);
      put("J", 0);
      put("K", 0);
      put("L", 0);
      put("M", 0);
      put("N", 0);
      put("O", 0);
      put("P", 0);
      put("Q", 0);
      put("R", 0);
      put("S", 0);
      put("T", 0);
      put("U", 0);
      put("V", 0);
      put("W", 0);
      put("X", 0);
      put("Y", 0);
      put("Z", 0);
    }};

    for (int i = 0; i < input_str.length; i++) {
      map.put(input_str[i].toUpperCase(), map.get(input_str[i].toUpperCase()) + 1);
    }

    int cnt = 0;
    String result = "";
    for (String i : map.keySet()) {
      if (map.get(i) > cnt) {
        cnt = map.get(i);
        result = i;
      }
    }
    int checkDuplicatedCnt = 0;
    boolean isDuplicated = true;
    for (String i : map.keySet()) {
      if (map.get(i) == cnt) {
        checkDuplicatedCnt += 1;
      }
    }
    if (checkDuplicatedCnt > 1)
      isDuplicated = false;
    if (!isDuplicated) {
      System.out.println('?');
    } else {
      System.out.println(result);
    }
  }
}