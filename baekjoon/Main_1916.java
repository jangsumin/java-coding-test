package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1916 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int M = Integer.parseInt(br.readLine());
    StringTokenizer st;
    int start, end, d;

    int[][] g = new int[N][N];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        g[i][j] = -1;
      }
    }
    int[] dist = new int[N];
    Arrays.fill(dist, Integer.MAX_VALUE); // 전부다 정수 최대 값으로 채우기
    boolean[] v = new boolean[N];
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine(), " ");
      start = Integer.parseInt(st.nextToken()) - 1;
      end = Integer.parseInt(st.nextToken()) - 1;
      d = Integer.parseInt(st.nextToken());
      if (g[start][end] == -1) g[start][end] = d;
      if (g[start][end] != -1 && d < g[start][end]) g[start][end] = d;
    }

    // for (int[] row : g) System.out.println(Arrays.toString(row));

    st = new StringTokenizer(br.readLine(), " ");
    int start_city = Integer.parseInt(st.nextToken()) - 1;
    int end_city = Integer.parseInt(st.nextToken()) - 1;

    PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
    pq.offer(new int[] {start_city, 0});
    dist[start_city] = 0;
    while(!pq.isEmpty()) {
      // for (int[] el : pq) System.out.println(Arrays.toString(el));
      int[] cur = pq.poll();
      int minVertex = cur[0];
      int min = cur[1];

      if (v[minVertex]) continue;
      v[minVertex] = true;
      if (minVertex == end_city) break;
      for (int i = 0; i < N; i++) {
        if (!v[i] && g[minVertex][i] != -1 && dist[i] > min + g[minVertex][i]) {
          dist[i] = min + g[minVertex][i];
          pq.offer(new int[] {i, dist[i]});
        }
      }
    }

    System.out.println(dist[end_city]);
  }
}
