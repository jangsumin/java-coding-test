package baekjoon;

import java.io.*;
import java.util.*;

public class Main_11559 {
  static int M = 12;
  static int N = 6;
  static char[][] board = new char[M][N];
  static boolean[][] v;
  static final int[] dx = {-1, 0, 1, 0};
  static final int[] dy = {0, 1, 0, -1};
  static ArrayList<int[]> list;
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    for (int i = 0; i < M; i++) {
      String[] input = br.readLine().split("");
      for (int j = 0; j < N; j++) {
        board[i][j] = input[j].charAt(0);
      }
    }

    // for (char[] row : board) System.out.println(Arrays.toString(row));

    int cnt = 0;
    while (true) {
      boolean isFinished = true;
      v = new boolean[M][N];
      for (int i = 0; i < M; i++) {
        for (int j = 0; j < N; j++) {
          if (board[i][j] != '.') {
            list = new ArrayList<>();
            bfs(i, j, board[i][j]);

            if (list.size() >= 4) {
              isFinished = false;
              for (int k = 0; k < list.size(); k++) {
                board[list.get(k)[0]][list.get(k)[1]] = '.';
              }
            }
          }
        }
      }
      if (isFinished) break;
      fall();
      cnt++;
    }
    System.out.println(cnt);
  }

  static void fall() {
    for (int j = 0; j < N; j++) {
      for (int i = M - 1; i > 0; i--) {
        if (board[i][j] == '.') {
          for (int k = i - 1; k >= 0; k--) {
            if (board[k][j] != '.') {
              board[i][j] = board[k][j];
              board[k][j] = '.';
              break;
            }
          }
        }
      }
    }
  }

  static void bfs(int x, int y, char c) {
    ArrayDeque<int[]> q = new ArrayDeque<>();
    list.add(new int[] {x, y});
    q.offer(new int[] {x, y});
    v[x][y] = true;

    while (!q.isEmpty()) {
      int[] tmp = q.poll();
      int poll_x = tmp[0];
      int poll_y = tmp[1];
      for (int i = 0; i < 4; i++) {
        int nx = poll_x + dx[i];
        int ny = poll_y + dy[i];
        if (nx >= 0 && nx < M && ny >= 0 && ny < N && !v[nx][ny] && board[nx][ny] == c) {
          v[nx][ny] = true;
          list.add(new int[] {nx, ny});
          q.offer(new int[] {nx, ny});
        }
      }
    }
  }
}
