package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1700 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());
    int[] sequence = new int[K];
    st = new StringTokenizer(br.readLine(), " ");
    for (int i = 0; i < K; i++) sequence[i] = Integer.parseInt(st.nextToken());

    int answer = 0;
    int cur = 0;

    boolean[] p = new boolean[K + 1];

    for (int i = 0; i < K; i++) {
      if (p[sequence[i]]) continue;
      else {
        if (cur < N) {
          p[sequence[i]] = true;
          cur++;
        } else {
          int[] v = new int[K + 1];
          for (int j = 1; j <= K; j++) {
            if (p[j]) v[j] = Integer.MAX_VALUE;
          }
          for (int j = i + 1; j < K; j++) {
            if (v[sequence[j]] == Integer.MAX_VALUE) v[sequence[j]] = j;
          }
    
          int unplug = 0;
          for (int j = 1; j <= K; j++) {
            if (v[j] > v[unplug]) {
              unplug = j;
            }
          }
    
          p[unplug] = false;
          answer++;
          p[sequence[i]] = true;
        }
      }

    }

    System.out.println(answer);
  }
}
