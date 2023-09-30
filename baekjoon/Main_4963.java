package baekjoon;

import java.io.*;
import java.util.*;

public class Main_4963 {
  static int w, h;
  static int[][] board;
  static boolean[][] v;
  static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
  static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    while (true) {
      st = new StringTokenizer(br.readLine(), " ");
      w = Integer.parseInt(st.nextToken());
      h = Integer.parseInt(st.nextToken());
      if (w == 0 && h == 0) return;

      int cnt = 0;

      board = new int[h][w];
      v = new boolean[h][w];
      for (int i = 0; i < h; i++) {
        st = new StringTokenizer(br.readLine(), " ");
        for (int j = 0; j < w; j++) {
          board[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      // for (int[] row : board) System.out.println(Arrays.toString(row));

      for (int i = 0; i < h; i++) {
        for (int j = 0; j < w; j++) {
          if (!v[i][j] && board[i][j] == 1) {
            bfs(i, j);
            cnt++;
          }
        }
      }

      System.out.println(cnt);
    }
  }

  static void bfs(int x, int y) {
    ArrayDeque<int[]> q = new ArrayDeque<>();
    q.offer(new int[] {x, y});
    v[x][y] = true;
    while (!q.isEmpty()) {
      int[] q_poll = q.poll();
      for (int i = 0; i < 8; i++) {
        int nx = q_poll[0] + dx[i];
        int ny = q_poll[1] + dy[i];
        if (nx >= 0 && nx < h && ny >= 0 && ny < w && !v[nx][ny] && board[nx][ny] == 1) {
          v[nx][ny] = true;
          q.offer(new int[] {nx, ny});
        }
      }
    }
  }
}
