// 뱀과 사다리 게임
package baekjoon;

import java.io.*;
import java.util.*;

public class Main_16928 {
  private static int[] map = new int[101];
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    for (int i = 1; i < 101; i++) map[i] = i;

    // map[12] = 98
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine(), " ");
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      map[x] = y;
    }

    // map[95] = 13
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine(), " ");
      int u = Integer.parseInt(st.nextToken());
      int v = Integer.parseInt(st.nextToken());
      map[u] = v;
    }

    System.out.println(bfs(1));
  }

  private static int bfs(int start) {
    int[] distance = new int[101];
    Queue<Integer> q = new LinkedList<>();
    q.add(start);
    distance[start] = 0;

    while (true) {
      int stage = q.remove();
      for (int i = 1; i <= 6; i++) {
        // 주사위를 던져서 갈 수 있는 순차적인 6개의 인덱스
        int num = stage + i;

        if (num > 100) continue;
        if (distance[map[num]] == 0) {
          q.add(map[num]);
          distance[map[num]] = distance[stage] + 1;
        }
        if (num == 100) return distance[100];
      }
    }
  }
}
