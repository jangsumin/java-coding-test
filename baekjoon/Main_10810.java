// 공 넣기
package baekjoon;

import java.util.Scanner;

public class Main_10810 {
  static int N, M;
  static int[] bucket;
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String[] input = sc.nextLine().split(" ");
    N = Integer.parseInt(input[0]);
    M = Integer.parseInt(input[1]);
    
    bucket = new int[N];
    for (int i = 0; i < M; i++) {
      String[] info = sc.nextLine().split(" ");
      for (int j = Integer.parseInt(info[0]); j <= Integer.parseInt(info[1]); j++) {
        bucket[j - 1] = Integer.parseInt(info[2]);
      }
    }

    String[] str_bucket = new String[N];
    for (int i = 0; i < N; i++) {
      str_bucket[i] = Integer.toString(bucket[i]);
    }

    System.out.println(String.join(" ", str_bucket));
  }
}