// 스도쿠 검증
package swExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_Pre_Training2 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int tc = Integer.parseInt(br.readLine());
    for (int i = 1; i <= tc; i++) {
      int[][] sudoku = new int[9][9];
      int result = 1;

      // sudoku판 채우기
      for (int j = 0; j < 9; j++) {
        String[] temp = br.readLine().split(" ");
        for (int k = 0; k < 9; k++) {
          sudoku[j][k] = Integer.parseInt(temp[k]);
        }
      }

      // 가로, 세로 검사
      for (int j = 0; j < 9; j++) {
        int[] inspect_row = new int[10];
        int[] inspect_col = new int[10];
        for (int k = 0; k < 9; k++) {
          inspect_row[sudoku[j][k]] += 1;
          inspect_col[sudoku[k][j]] += 1;
        }
        for (int k = 1; k < 10; k++) {
          if (inspect_row[k] != 1 || inspect_col[k] != 1) {
            result = 0;
            break;
          }
        }
      }

      // 3 X 3 검사
      for (int j = 0; j < 9; j += 3) {
        for (int k = 0; k < 9; k += 3) {
          int [] inspect_rect = new int[10];
          for (int m = 0; m < 3; m++) {
            for (int n = 0; n < 3; n++) {
              inspect_rect[sudoku[j + m][k + n]] += 1;
            }
          }
          for (int m = 1; m < 10; m++) {
            if (inspect_rect[m] != 1) {
              result = 0;
              break;
            }
          }
        }
      }

      System.out.println(String.format("%s%d %d", "#", i, result));
    }
  }
}