package baekjoon;

import java.io.*;

class Main_2444 {
  public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int N = Integer.parseInt(br.readLine());

      // char[][] map = new char[2 * N - 1][2 * N - 1];
      
      // for (int i = 0; i < 2 * N - 1; i++) {
      //     for (int j = 0; j < 2 * N - 1; j++) {
      //         map[i][j] = ' ';
      //     }
      // }

      // int len = 0;
      // int len2 = N - 2;
      // for (int i = 0; i < 2 * N - 1; i++) {
      //     if (i < N) {
      //         for (int j = N - len - 1; j < N + len; j++) {
      //             map[i][j] = '*';
      //         }
      //         len += 1;
      //     } else {
      //         for (int j = N - len2 - 1; j < N + len2; j++) {
      //             map[i][j] = '*';
      //         }
      //         len2 -= 1;
      //     }
      // }

      // for (int i = 0; i < 2 * N - 1; i++) {
      //     for (int j = 0; j < 2 * N - 1; j++) {
      //         System.out.print(map[i][j]);
      //     }
      //     System.out.println();
      // }

      for(int i = 1; i <= N ; i++) {
        for(int j = 0; j < N-i; j++)
          System.out.print(" ");
        for(int j = 0; j < i*2-1; j++)
          System.out.print("*");
        System.out.println();
      }
      
      for(int i = N-1; i >= 0 ; i--) {
        for(int j = 0; j < N-i; j++)
          System.out.print(" ");
        for(int j = 0; j < i*2-1; j++)
          System.out.print("*");
        System.out.println();
      }
  }
}
