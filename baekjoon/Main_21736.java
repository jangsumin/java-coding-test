package baekjoon;

import java.io.*;
import java.util.*;

public class Main_21736 {
  static int N, M;
  static char[][] board;
  static boolean[][] v;
  static final int[] dx = {-1, 0, 1, 0};
  static final int[] dy = {0, 1, 0, -1};
  static int cnt = 0;
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    board = new char[N][M];
    v = new boolean[N][M];
    int doyeon_x = 0;
    int doyeon_y = 0;
    for (int i = 0; i < N; i++) {
      String row = br.readLine();
      for (int j = 0; j < M; j++) {
        board[i][j] = row.charAt(j);
        if (board[i][j] == 'I') {
          doyeon_x = i;
          doyeon_y = j;
        }
      }
    }

    // for (char[] row : board) System.out.println(Arrays.toString(row));
    bfs(doyeon_x, doyeon_y);
    if (cnt != 0) System.out.println(cnt);
    else System.out.println("TT");
  }

  static void bfs(int x, int y) {
    ArrayDeque<int[]> q = new ArrayDeque<>();
    q.offer(new int[] {x, y});
    v[x][y] = true;
    while (!q.isEmpty()) {
      int[] q_poll = q.poll();
      if (board[q_poll[0]][q_poll[1]] == 'P') cnt += 1;
      for (int i = 0; i < 4; i++) {
        int nx = q_poll[0] + dx[i];
        int ny = q_poll[1] + dy[i];
        if (nx >= 0 && nx < N && ny >= 0 && ny < M && !v[nx][ny] && board[nx][ny] != 'X') {
          v[nx][ny] = true;
          q.offer(new int[] {nx, ny});
        }
      }
    }
  }
}
