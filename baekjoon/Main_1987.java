package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1987 {
  static int R, C;
  static int[][] board;
  static boolean[] alphabet;
  static int[] dx = {-1, 0, 1, 0};
  static int[] dy = {0, 1, 0, -1};
  static int result = 0;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    R = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());
    board = new int[R][C];
    alphabet = new boolean[26];
    for (int i = 0; i < R; i++) {
      String[] input = br.readLine().split("");
      for (int j = 0; j < C; j++) board[i][j] = input[j].charAt(0) - 'A';
    }

    // 무조건 시작은 좌측 상단
    dfs(0, 0, 1);

    System.out.println(result);
  }

  static void dfs(int x, int y, int sum) {
    alphabet[board[x][y]] = true;

    for (int i = 0; i < 4; i++) {
      int nx = x + dx[i];
      int ny = y + dy[i];
      if (nx >= 0 && nx < R && ny >= 0 && ny < C && !alphabet[board[nx][ny]]) {
        // System.out.println(list);
        dfs(nx, ny, sum + 1);
        alphabet[board[nx][ny]] = false;
      }
    }
    result = Math.max(result, sum);
  }
}
