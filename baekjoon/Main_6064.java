package baekjoon;

import java.io.*;
import java.util.*;

public class Main_6064 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());
    StringTokenizer st;
    StringBuilder sb = new StringBuilder();
    for (int tc = 0; tc < T; tc++) {
      st = new StringTokenizer(br.readLine(), " ");
      int M = Integer.parseInt(st.nextToken());
      int N = Integer.parseInt(st.nextToken());
      int x = Integer.parseInt(st.nextToken()) - 1;
      int y = Integer.parseInt(st.nextToken()) - 1;
      
      // int i = 1, j = 1;
      // int cnt = 1;
      // int result = -1;
      // while (true) {
      //   // System.out.println(i + " " + j);
      //   i += 1;
      //   j += 1;
      //   cnt++;
      //   if (i == x && j == y) {
      //     result = cnt;
      //     break;
      //   }
      //   if (i == M && j == N) break;
      //   if (i > M) i = 1;
      //   if (j > N) j = 1;
      // }

      int result = -1;
      for (int i = x; i < N * M; i += M) {
        if (i % N == y) {
          result = i + 1;
          break;
        }
      }
      sb.append(result).append("\n");
    }
    System.out.print(sb.toString());
  } 
}
