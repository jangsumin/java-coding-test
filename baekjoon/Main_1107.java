package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1107 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int M = Integer.parseInt(br.readLine());
    if (M == 0) {
      System.out.println(Math.min(String.valueOf(N).length(), Math.abs(N - 100)));
      System.exit(0);
    }

    boolean[] malfunc = new boolean[10];
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    for (int i = 0; i < M; i++) malfunc[Integer.parseInt(st.nextToken())] = true;

    int min_result = Math.abs(N - 100);
    for (int i = 0; i <= 999999; i++) {
      String num_str = String.valueOf(i);
      int len = num_str.length();

      boolean isPossible = true;
      for (int j = 0; j < len; j++) {
        if (malfunc[num_str.charAt(j) - '0']) {
          isPossible = false;
          break;
        }
      }

      if (isPossible) {
        min_result = Math.min(min_result, len + Math.abs(i - N));
      }
    }

    System.out.println(min_result);
  }
}
