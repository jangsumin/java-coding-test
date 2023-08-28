package baekjoon;

import java.io.*;
import java.util.*;

public class Main_9019 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());
    for (int i = 0; i < T; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine(), " ");
      int A = Integer.parseInt(st.nextToken());
      int B = Integer.parseInt(st.nextToken());

      int[] arr = new int[10000];
      boolean[] v = new boolean[10000];
      String[] cmd = new String[10000];
      Arrays.fill(cmd, "");
      ArrayDeque<Integer> q = new ArrayDeque<>();
      q.offer(A);
      v[A] = true;
      while (!q.isEmpty()) {
        int num = q.poll();
        if (num == B) {
          System.out.println(cmd[num]);
          break;
        }

        int D = (num * 2) % 10000;
        int S = num == 0 ? 9999 : num - 1;
        int L = (num % 1000) * 10 + num / 1000;
        int R = (num % 10) * 1000 + num / 10;

        if (!v[D]) {
          q.offer(D);
          v[D] = true;
          cmd[D] = cmd[num] + "D";
        }
        if (!v[S]) {
          q.offer(S);
          v[S] = true;
          cmd[S] = cmd[num] + "S";
        }
        if (!v[L]) {
          q.offer(L);
          v[L] = true;
          cmd[L] = cmd[num] + "L";
        }
        if (!v[R]) {
          q.offer(R);
          v[R] = true;
          cmd[R] = cmd[num] + "R";
        }
      }
    }
  }
}
