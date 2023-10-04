package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1236 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    char[][] board = new char[N][M];
    for (int i = 0; i < N; i++) {
      String[] input = br.readLine().split("");
      for (int j = 0; j < M; j++) {
        board[i][j] = input[j].charAt(0);
      }
    }

    // for (char[] row : board) System.out.println(Arrays.toString(row));

    List<Integer> row_list = new ArrayList();
    List<Integer> col_list = new ArrayList();

    for (int i = 0; i < N; i++) {
      boolean isRowGuard = false;
      for (int j = 0; j < M; j++) {
        if (board[i][j] == 'X') {
          isRowGuard = true;
          break;
        }
      }
      if (!isRowGuard) row_list.add(i);
    }

    for (int i = 0; i < M; i++) {
      boolean isColGuard = false;
      for (int j = 0; j < N; j++) {
        if (board[j][i] == 'X') {
          isColGuard = true;
          break;
        }
      }
      if (!isColGuard) col_list.add(i);
    }

    System.out.println(Math.max(row_list.size(), col_list.size()));
  }
}
