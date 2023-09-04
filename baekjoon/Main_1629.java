package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1629 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    int A = Integer.parseInt(st.nextToken());
    int B = Integer.parseInt(st.nextToken());
    int C = Integer.parseInt(st.nextToken());

    // 10 1 12 -> 10
    // 10 2 12 -> 4
    // 10 3 12 -> 4
    
    // 13 1 12 -> 1
    // 13 2 12 -> 1
    // 13 3 12 -> 1

    // 21억짜리 연산
    // int num = 1;
    // for (int i = 1; i <= B; i++) {
    //   num *= A;
    //   num %= C;
    // }
    // System.out.println(num);

    System.out.println(calc(A, B, C));
  }

  static long calc(int a, int b, int c) {
    if (b == 0) return 1;

    long n = calc(a, b / 2, c);
    if (b % 2 == 0) return n * n % c;
    else return ((n * n % c) * a) % c;    
  }
}
