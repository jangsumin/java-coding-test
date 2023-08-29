package baekjoon;

import java.io.*;
import java.util.*;

public class Main_15486 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    List<int[]> list = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine(), " ");
      list.add(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
    }

    int[] save = new int[N + 1];
    int max = 0;
    for (int i = 0; i < list.size(); i++) {
      int days = list.get(i)[0];
      int cost = list.get(i)[1];
      max = Math.max(max, save[i]);
      if (i + days <= N) {
        save[i + days] = Math.max(save[i + days], max + cost);
        // save[i + days] = Math.max(save[i + days], save[i] + cost);
      }
      // System.out.println(Arrays.toString(save));
    }

    int result = 0;
    for (int i = 0; i <= N; i++) result = Math.max(result, save[i]);
    System.out.println(result);
  }
}
