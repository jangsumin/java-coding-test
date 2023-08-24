package swExpertAcademy;

import java.io.*;
import java.util.*;

public class Solution_1249 {
  static int N;
  static int[][] board;
  static boolean[][] v;
  static int[][] dist;
  static final int[] dx = {-1, 0, 1, 0};
  static final int[] dy = {0, 1, 0, -1};
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());
    for (int tc = 1; tc <= T; tc++) {
      N = Integer.parseInt(br.readLine());
      board = new int[N][N];
      v = new boolean[N][N];
      dist = new int[N][N];
      for (int i = 0; i < N; i++) {
        String[] input = br.readLine().split("");
        for (int j = 0; j < N; j++) {
          board[i][j] = Integer.parseInt(input[j]);
          dist[i][j] = Integer.MAX_VALUE;
        }
      }

      // for (int[] row : board) System.out.println(Arrays.toString(row));
      dist[0][0] = 0;
      dijkstraPq(0, 0, 0);
      
      System.out.println(String.format("#%d %d", tc, dist[N - 1][N - 1]));  
    }
    br.close();
  }

  // static void dijkstra(int x, int y) {
  //   ArrayDeque<int[]> q = new ArrayDeque<>();
  //   q.offer(new int[] {x, y});
  //   while (!q.isEmpty()) {
  //     int[] q_poll = q.poll();
  //     int poll_x = q_poll[0];
  //     int poll_y = q_poll[1];
  //     if (v[poll_x][poll_y]) continue;
  //     v[poll_x][poll_y] = true;
  //     for (int i = 0; i < 4; i++) {
  //       int nx = poll_x + dx[i];
  //       int ny = poll_y + dy[i];
  //       if (nx >= 0 && nx < N && ny >= 0 && ny < N && !v[nx][ny] && dist[nx][ny] > dist[poll_x][poll_y] + board[nx][ny]) {
  //         dist[nx][ny] = dist[poll_x][poll_y] + board[nx][ny];
  //         q.offer(new int[] {nx, ny});
  //       }
  //     }
  //   }
  // }
  static void dijkstraPq(int x, int y, int weight) {
    PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
    pq.offer(new int[] {x, y, weight});
    while (!pq.isEmpty()) {
      int[] pq_poll = pq.poll();
      int poll_x = pq_poll[0];
      int poll_y = pq_poll[1];
      int poll_weight = pq_poll[2];
      for (int i = 0; i < 4; i++) {
        int nx = poll_x + dx[i];
        int ny = poll_y + dy[i];
        if (nx >= 0 && nx < N && ny >= 0 && ny < N && !v[nx][ny] && dist[nx][ny] > poll_weight + board[nx][ny]) {
          dist[nx][ny] = poll_weight + board[nx][ny];
          pq.offer(new int[] {nx, ny, dist[nx][ny]});
        }
      }
    }
  }
}
