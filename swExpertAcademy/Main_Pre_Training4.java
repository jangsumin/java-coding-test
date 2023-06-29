// 최빈수 구하기(D2)
package swExpertAcademy;

import java.io.*;
import java.util.Arrays;

public class Main_Pre_Training4 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int tc = Integer.parseInt(br.readLine());
    for (int i = 0; i < tc; i++) {
      int test_number = Integer.parseInt(br.readLine());
      int[] score_array = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
      int[] freq_array = new int[101];
      for (int j = 0; j < score_array.length; j++) {
        freq_array[score_array[j]] += 1;
      }

      int max_freq = Arrays.stream(freq_array).max().getAsInt();
      int result = -1;
      for (int j = 0; j < 101; j++) {
        if (freq_array[j] == max_freq) {
          result = j;
        }
      }

      System.out.println(String.format("%s%d %d", "#", test_number, result));
    }
  }
}