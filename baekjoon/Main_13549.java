package baekjoon;

import java.io.*;
import java.util.*;

public class Main_13549 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    int[] dist = new int[100001];
    Arrays.fill(dist, Integer.MAX_VALUE);
    boolean[] v = new boolean[100001];

    dist[N] = 0;
    PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
    pq.offer(new int[] {N, 0});
    while (!pq.isEmpty()) {
      int[] cur = pq.poll();
      int minVertex = cur[0];
      int min = cur[1];

      if (v[minVertex]) continue;
      v[minVertex] = true;
      if (minVertex == K) break;
      for (int i = 0; i < 3; i++) {
        if (i == 0) { // -1 지점으로 가기
          if (minVertex - 1 >= 0 && !v[minVertex - 1] && dist[minVertex - 1] > min + 1) {
            dist[minVertex - 1] = min + 1;
            pq.offer(new int[] {minVertex - 1, dist[minVertex - 1]});
          }
        } else if (i == 1) { // +1 지점으로 가기
          if (minVertex + 1 <= 100000 && !v[minVertex + 1] && dist[minVertex + 1] > min + 1) {
            dist[minVertex + 1] = min + 1;
            pq.offer(new int[] {minVertex + 1, dist[minVertex + 1]});
          }
        } else { // 2배로 가기
          if (minVertex * 2 <= 100000 && !v[minVertex * 2] && dist[minVertex * 2] > min) {
            dist[minVertex * 2] = min;
            pq.offer(new int[] {minVertex * 2, dist[minVertex * 2]});
          }
        }
      }
    }

    System.out.println(dist[K]);
  }
}
