// 암호생성기
package swExpertAcademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Solution_1225 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int T = 10;
    for (int tc = 1; tc <= T; tc++) {
      // 테스트 케이스 번호
      int num = Integer.parseInt(br.readLine());
      StringTokenizer st = new StringTokenizer(br.readLine(), " ");
      ArrayDeque<Integer> q = new ArrayDeque<>(8);
      for (int i = 0; i < 8; i++) {
        q.offer(Integer.parseInt(st.nextToken()));
      }

      int minus = 1;
      while (true) {
        if (minus == 6) minus = 1;

        int first = q.pollFirst();
        if (first - minus <= 0) {
          q.offer(0);
          break;
        }
        first -= minus;
        q.offer(first);
        minus++;
      }

      StringBuilder sb = new StringBuilder();
      sb.append("#").append(tc);
      for (int n : q) {
        sb.append(" ").append(n);
      }
      System.out.println(sb.toString());
    }
  }
}