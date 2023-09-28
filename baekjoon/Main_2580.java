package baekjoon;

import java.io.*;
import java.util.*;

public class Main_2580 {
  static int[][] board;
  static boolean[][] v;
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    board = new int[9][9];
    v = new boolean[9][9];
    StringTokenizer st;

    for (int i = 0; i < 9; i++) {
      st = new StringTokenizer(br.readLine(), " ");
      for (int j = 0; j < 9; j++) {
        board[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    // for (int[] row : board) System.out.println(Arrays.toString(row));

    backtrack(0, 0);
  }

  static void backtrack(int x, int y) {
    if (y == 9) {
      backtrack(x + 1, 0);
      return;
    }

    if (x == 9) { // 종료 조건
      for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
          if (j == 0) System.out.print(board[i][j]);
          else System.out.print(" " + board[i][j]);
        }
        System.out.println();
      }

      System.exit(0);
    }

    if (board[x][y] == 0) {
      for (int num = 1; num <= 9; num++) {
        if (!check(x, y, num)) continue;
        board[x][y] = num;
        backtrack(x, y + 1);
      }
      board[x][y] = 0;
      return;
    }

    backtrack(x, y + 1);
  }

  static boolean check(int x, int y, int num) {
    for (int i = 0; i < 9; i++) { // 행 검사
      if (board[x][i] == num) {
        return false;
      }
    }

    for (int i = 0; i < 9; i++) {
      if (board[i][y] == num) {
        return false;
      }
    }

    int set_x = (x / 3) * 3;
    int set_y = (y / 3) * 3;
    for (int i = set_x; i < set_x + 3; i++) {
      for (int j = set_y; j < set_y + 3; j++) {
        if (board[i][j] == num) {
          return false;
        }
      }
    }

    return true;
  }
  
}
