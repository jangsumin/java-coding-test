package baekjoon;

import java.io.*;
import java.util.*;

public class Main_14500 {
  static int N, M;
  static int[][] board;
  static boolean[][] v;
  static int[] dx = {-1, 0, 1, 0};
  static int[] dy = {0, 1, 0, -1};
  static int result = 0;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    board = new int[N][M];
    v = new boolean[N][M];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine(), " ");
      for (int j = 0; j < M; j++) {
        board[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    // for (int[] row : board) System.out.println(Arrays.toString(row));

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        v[i][j] = true;
        dfs(i, j, board[i][j], 1);
        v[i][j] = false;
      }
    }

    System.out.println(result);
  }

  static void dfs(int x, int y, int sum, int cnt) {
    // System.out.println(x + " " + y + " " + sum + " " + cnt);
    if (cnt == 4) {
      result = Math.max(result, sum);
      return;
    }

    for (int i = 0; i < 4; i++) {
      int nx = x + dx[i];
      int ny = y + dy[i];
      if (nx >= 0 && nx < N && ny >= 0 && ny < M && !v[nx][ny]) {
        if (cnt == 2) {
          v[nx][ny] = true;
          dfs(x, y, sum + board[nx][ny], cnt + 1);
          v[nx][ny] = false;
        }

        v[nx][ny] = true;
        dfs(nx, ny, sum + board[nx][ny], cnt + 1);
        v[nx][ny] = false;
      }
    }
  } 
}
