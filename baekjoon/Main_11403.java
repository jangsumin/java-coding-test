package baekjoon;

import java.io.*;
import java.util.*;

public class Main_11403 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());

    int[][] matrix = new int[N][N];
    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine(), " ");
      for (int j = 0; j < N; j++) {
        matrix[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    for (int k = 0; k < N; k++) {
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          if (matrix[i][k] == 1 && matrix[k][j] == 1) matrix[i][j] = 1;
        }
      }
    }

    // for (int[] row : matrix) System.out.println(Arrays.toString(row));

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (j != N - 1) sb.append(matrix[i][j]).append(" ");
        else sb.append(matrix[i][j]);
      }
      if (i != N - 1) sb.append("\n");
    }

    System.out.println(sb.toString());
  }
}
