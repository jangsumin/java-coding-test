// 별찍기 - 11
// StringBuilder 사용하는 것이 시간 초과를 피하는 방법
package baekjoon;

import java.util.Scanner;
import java.util.Arrays;

class Main_2448 {
  private static char[][] map;
  private static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = Integer.parseInt(sc.nextLine());

    // 로직
    // 입력값이 3일 때 * 로 이루어진 삼각형 한 개가 출력된다.
    
    map = new char[N][2 * N - 1];
    for (int i = 0; i < N; i++) {
      Arrays.fill(map[i], ' ');
    }

    printStar(0, N - 1, N);

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < 2 * N - 1; j++) {
        sb.append(map[i][j]);
      }
      sb.append('\n');
    }

    System.out.println(sb.toString());
    sc.close();
  }

  static void printStar(int x, int y, int n) {
    if (n == 3) {
      map[x][y] = '*';
      map[x + 1][y - 1] = map[x + 1][y + 1] = '*';
      map[x + 2][y - 2] = map[x + 2][y - 1] = map[x + 2][y] = map[x + 2][y + 1] = map[x + 2][y + 2] = '*';
      return;
    }

    printStar(x, y, n / 2);
    printStar(x + n / 2, y - n / 2, n / 2);
    printStar(x + n / 2, y + n / 2, n / 2);
  }
}