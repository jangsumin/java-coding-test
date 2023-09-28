package baekjoon;

import java.io.*;
import java.util.*;

public class Main_9205 {
  public static void main(String[] args) throws Exception { 
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());
    StringTokenizer st;
    
    // heap 사이즈 초과로 인한 에러는 해결 할 수 없음

    for (int tc = 0; tc < T; tc++) {
      List<int[]> list = new ArrayList<>();
      int N = Integer.parseInt(br.readLine()); // 편의점 개수
      for (int i = 0; i < N + 2; i++) {
        st = new StringTokenizer(br.readLine(), " ");
        list.add(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
      }


      // for (int[] el : list) System.out.println(Arrays.toString(el));

      boolean[][] v = new boolean[N + 2][N + 2];
      for (int i = 0; i < N + 2; i++) {
        for (int j = 0; j < N + 2; j++) {
          int[] cur = list.get(i);
          int[] next = list.get(j);
          int distance = Math.abs(cur[0] - next[0]) + Math.abs(cur[1] - next[1]);

          if (distance <= 1000) v[i][j] = true;
        }
      }

      // for (boolean[] row : v) System.out.println(Arrays.toString(row));

      for (int k = 0; k < N + 2; k++) {
        for (int i = 0; i < N + 2; i++) {
          for (int j = 0; j < N + 2; j++) {
            if (v[i][k] && v[k][j]) v[i][j] = true;
          }
        }
      }

      if (v[0][N + 1]) System.out.println("happy");
      else System.out.println("sad");
    }
  }

}
