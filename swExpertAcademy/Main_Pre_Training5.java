// 숫자 배열 회전(D2)
package swExpertAcademy;

import java.io.*;

public class Main_Pre_Training5 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int tc = Integer.parseInt(br.readLine());
    for (int i = 1; i <= tc; i++) {
      int n = Integer.parseInt(br.readLine());
      int[][] board = new int[n][n];
      for (int j = 0; j < n; j++) {
        String[] temp = br.readLine().split(" ");
        for (int k = 0; k < n; k++) {
          board[j][k] = Integer.parseInt(temp[k]);
        }
      }

      int[][] quarter_board = new int[n][n];
      int[][] half_board = new int[n][n];
      int[][] half_quarter_board = new int[n][n];
      for (int j = 0; j < n; j++) {
        for (int k = 0; k < n; k++) {
          quarter_board[j][n - k - 1] = board[k][j];
          half_board[n - j - 1][n - k - 1] = board[j][k];
          half_quarter_board[n - j - 1][k] = board[k][j];
        }
      }

      System.out.println("#" + Integer.toString(i));
      for (int j = 0; j < n; j++) {
        for (int k = 0; k < n; k++) {
          System.out.print(quarter_board[j][k]);
        }
        System.out.print(" ");
        for (int k = 0; k < n; k++) {
          System.out.print(half_board[j][k]);
        }
        System.out.print(" ");
        for (int k = 0; k < n; k++) {
          System.out.print(half_quarter_board[j][k]);
        }
        System.out.println();
      }
    }
  }
}