// 공 바꾸기
package baekjoon;

import java.util.Scanner;

public class Main_10813 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String[] input = sc.nextLine().split(" ");
    int n = Integer.parseInt(input[0]);
    int m = Integer.parseInt(input[1]);
    String[] buckets = new String[n];
    for (int i = 0; i < n; i++) {
      buckets[i] = Integer.toString(i + 1);
    }
    for (int i = 0; i < m; i++) {
      String[] changes = sc.nextLine().split(" ");
      String temp = buckets[Integer.parseInt(changes[0]) - 1];
      buckets[Integer.parseInt(changes[0]) - 1] = buckets[Integer.parseInt(changes[1]) - 1];
      buckets[Integer.parseInt(changes[1]) - 1] = temp;
    }
    System.out.println(String.join(" ", buckets));
    sc.close();
  }
}