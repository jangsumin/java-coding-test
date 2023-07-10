// 치킨 배달
package baekjoon;

import java.util.ArrayList;
import java.util.Scanner;

public class Main_15686 {
  static int n, m;
  static int[][] map;
  static boolean[] visited;
  static int[] output;
  static ArrayList<Dot> chicken;
  static ArrayList<Dot> person;
  static int result;
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    
    String[] input = sc.nextLine().split(" ");
    n = Integer.parseInt(input[0]);
    m = Integer.parseInt(input[1]);
    map = new int[n][n];
    result = Integer.MAX_VALUE;
    chicken = new ArrayList<Dot>();
    person = new ArrayList<Dot>();

    for (int i = 0; i < n; i++) {
      String[] input_map = sc.nextLine().split(" ");
      for (int j = 0; j < n; j++) {
        map[i][j] = Integer.parseInt(input_map[j]);
        if (map[i][j] == 1)
          person.add(new Dot(i, j));
        else if (map[i][j] == 2)
          chicken.add(new Dot(i, j));
      }
    }

    visited = new boolean[chicken.size()];
    output = new int[chicken.size()];

    for (int i = 0; i < chicken.size(); i++) {
      visited[i] = true;
      select(i, 0);
      visited[i] = false;
    }
    System.out.println(result);
    sc.close();
  }

  public static void select(int start, int depth) {
    output[depth] = start + 1;

    for (int i = start; i < chicken.size(); i++) {
      if (visited[i])
        continue;
      visited[i] = true;
      select(i, depth + 1);
      visited[i] = false;
    }

    if (depth == m - 1) {
      int sum = 0;
      int cur_m = 0;
      for (int i = 0; i < person.size(); i++) {
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < m; j++) {
          cur_m = distance(person.get(i), chicken.get(output[j] - 1));
          min = Math.min(min, cur_m);
        }
        sum += min;
      }
      result = Math.min(result, sum);
    }
  }

  public static int distance(Dot d1, Dot d2) {
    return Math.abs(d1.x - d2.x) + Math.abs(d1.y - d2.y);
  }
}

class Dot {
  int x;
  int y;

  Dot(int x, int y) {
    this.x = x;
    this.y = y;
  }
}