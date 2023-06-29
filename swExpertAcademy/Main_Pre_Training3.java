// 파리퇴치3
package swExpertAcademy;

import java.io.*;

public class Main_Pre_Training3 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int tc = Integer.parseInt(br.readLine());
    for (int i = 1; i <= tc; i++) {
      // 입력부
      String[] info = br.readLine().split(" ");
      int n = Integer.parseInt(info[0]);
      int m = Integer.parseInt(info[1]);

      int[][] board = new int[n][n];
      for (int j = 0; j < n; j++) {
        String[] temp = br.readLine().split(" ");
        for (int k = 0; k < n; k++) {
          board[j][k] = Integer.parseInt(temp[k]);
        }
      }

      int save = -1;
      // + 검사
      for (int j = 0; j < n; j++) {
        for (int k = 0; k < n; k++) {
          int sum = board[j][k];
          for (int x = 1; x < m; x++) {
            if (j - x >= 0 && j - x < n) 
              sum += board[j - x][k];
            if (j + x >= 0 && j + x < n)
              sum += board[j + x][k];
            if (k - x >= 0 && k - x < n)
              sum += board[j][k - x];
            if (k + x >= 0 && k + x < n)
              sum += board[j][k + x];
          }
          if (sum > save) {
            save = sum;
          }
        }
      }

      // X 검사
      for (int j = 0; j < n; j++) {
        for (int k = 0; k < n; k++) {
          int sum = board[j][k];
          for (int x = 1; x < m; x++) {
            if (j - x >= 0 && j - x < n && k - x >= 0 && k - x < n)
              sum += board[j - x][k - x];
            if (j - x >= 0 && j - x < n && k + x >= 0 && k + x < n)
              sum += board[j - x][k + x];
            if (j + x >= 0 && j + x < n && k - x >= 0 && k - x < n)
              sum += board[j + x][k - x];
            if (j + x >= 0 && j + x < n && k + x >= 0 && k + x < n)
              sum += board[j + x][k + x];
          }
          if (sum > save) {
            save = sum;
          }
        }
      }

      System.out.println(String.format("%s%d %d", "#", i, save));
    }
  }
}