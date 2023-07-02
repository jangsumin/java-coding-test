// 소금 폭탄
package baekjoon;

import java.util.stream.Stream;
import java.util.Scanner;

public class Main_13223 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int[] current = Stream.of(sc.next().split(":")).mapToInt(Integer::parseInt).toArray();
    int[] target = Stream.of(sc.next().split(":")).mapToInt(Integer::parseInt).toArray();
    int[] result = new int[3];

    if (current[0] == target[0] && current[1] == target[1] && current[2] == target[2]) {
      System.out.println("24:00:00");
      sc.close();
      return;
    }
    
    if (target[2] >= current[2]) {
      result[2] = target[2] - current[2];
    } else {
      target[1] -= 1;
      result[2] = target[2] + 60 - current[2];
    }

    if (target[1] >= current[1]) {
      result[1] = target[1] - current[1];
    } else {
      target[0] -= 1;
      result[1] = target[1] + 60 - current[1];
    }

    if (target[0] >= current[0]) {
      result[0] = target[0] - current[0];
    } else {
      result[0] = target[0] + 24 - current[0];
    }

    System.out.println(String.format("%02d:%02d:%02d", result[0], result[1], result[2]));
    sc.close();
  }
}