// 요세푸스 문제

package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main_1158 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    // 1 2 3 4 5 6 7
    // 3 4 5 6 7 1 2
    // 6 7 1 2 4 5
    // 2 4 5 7 1
    // 7 1 4 5
    // 5 1 4
    // 1
    // 4
    ArrayDeque<Integer> q = new ArrayDeque<>(N);
    StringBuilder sb = new StringBuilder();
    sb.append("<");
    // 일단 1부터 7까지 큐에 채우기
    for (int i = 1; i <= N; i++) {
      q.offer(i);
    }
    int idx = 1;
    while (q.size() != 1) {
      if (idx % K == 0) {
        sb.append(q.poll()).append(", ");
      } else {
        int num = q.poll();
        q.offer(num);
      }
      idx++;
    }
    sb.append(q.poll()).append(">");
    System.out.println(sb.toString());
  }
}
