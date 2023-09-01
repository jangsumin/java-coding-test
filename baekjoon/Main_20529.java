package baekjoon;

import java.io.*;
import java.util.*;

public class Main_20529 {
  static int N;
  static String[] input;
  static int[] save = new int[3];
  static int result = Integer.MAX_VALUE;
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());
    StringBuilder sb = new StringBuilder();
    for (int tc = 0; tc < T; tc++) {
      result = Integer.MAX_VALUE;
      N = Integer.parseInt(br.readLine());
      input = new String[N];
      StringTokenizer st = new StringTokenizer(br.readLine(), " ");
      for (int i = 0; i < N; i++) input[i] = st.nextToken();
      if (N > 32) {
        sb.append(0).append("\n");
        continue;
      }
      comb(0, 0);
      sb.append(result).append("\n");
    }
    System.out.print(sb.toString());
  }

  static void comb(int cnt, int start) {
    if (cnt == 3) {
      // System.out.println(Arrays.toString(save));
      int dist = 0;
      dist += check(input[save[0]], input[save[1]]);
      dist += check(input[save[1]], input[save[2]]);
      dist += check(input[save[0]], input[save[2]]);
      result = Math.min(result, dist);
      return;
    }

    for (int i = start; i < N; i++) {
      save[cnt] = i;
      comb(cnt + 1, i + 1);
    }
  }

  static int check(String str1, String str2) {
    int diff_cnt = 0;
    for (int i = 0; i < 4; i++) {
      if (str1.charAt(i) != str2.charAt(i)) diff_cnt++;
    }
    return diff_cnt;
  }
}
